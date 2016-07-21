package com.bodiukh.blog.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
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

        @Override
        public boolean matches(final HttpServletRequest request) {
            String uri = request.getRequestURI();
            return !(uri.equals("/") || uri.endsWith("/post/all")
                    || uri.contains("/resources/") || uri.endsWith("user/login"));
        }
    };

}
