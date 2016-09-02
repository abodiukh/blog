package com.bodiukh.blog.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author a.bodiukh
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RestAuthenticationFilter filter = new RestAuthenticationFilter();
        filter.setRequiresAuthenticationRequestMatcher(requestMatcher);
        filter.setAuthenticationManager(createAuthenticationManager());
        filter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler());
        filter.setAuthenticationFailureHandler(new AuthenticationFailrureHandler());
        http.csrf().disable().addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().authenticationEntryPoint(new FailureAuthenticationEntryPoint());
    }

    @Bean
    @Scope("singleton")
    @Qualifier("encoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager createAuthenticationManager() {
        return new AuthenticationManager() {

            public Authentication authenticate(Authentication auth) throws AuthenticationException {
                DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
                authenticationProvider.setUserDetailsService(userDetailsService);
                authenticationProvider.setPasswordEncoder(passwordEncoder());
                return authenticationProvider.authenticate(auth);
            }
        };
    }

    public static RequestMatcher requestMatcher = new RequestMatcher() {
        private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
        private Pattern notAllowedMethods = Pattern.compile("^(POST|PUT|DELETE)$");

        public boolean matches(final HttpServletRequest request) {
            String uri = request.getRequestURI();
            String method = request.getMethod();
            return !(isPublicInfo(method, uri) || isLogin(method, uri) || readPost(method, uri));
        }

        private boolean isPublicInfo(String method, String uri) {
            return allowedMethods.matcher(method).matches() &&
                    (uri.equals("/") || uri.equals("/post/all") || uri.contains("/resources/"));
        }

        private boolean isLogin(String method, String uri) {
            return notAllowedMethods.matcher(method).matches() && (uri.equals("/user/login") || uri.equals("user/logout"));
        }

        private boolean readPost(String method, String uri) {
            Pattern allowedUrlPattern = Pattern.compile("^/post/.[^/]*$");
            return allowedMethods.matcher(method).matches() && allowedUrlPattern.matcher(uri).matches();
        }

    };

}
