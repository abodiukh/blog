package com.bodiukh.blog.config;

import java.io.IOException;
import java.text.MessageFormat;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

public class RestAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String FILTER_APPLIED = "applied";

    public static RequestMatcher requestMatcher = new RequestMatcher() {

        @Override
        public boolean matches(final HttpServletRequest request) {
            String uri = request.getRequestURI();
            return !(uri.endsWith("/post/all") || uri.contains("/resources/") || uri.endsWith("user/login"));
        }
    };

    public RestAuthenticationFilter() throws Exception {
        super(requestMatcher);
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (!requiresAuthentication(request, response)) {
            chain.doFilter(request, response);
            return;
        }

        //If we have already applied this filter - not sure how that would happen? - then just continue chain
        if (request.getAttribute(FILTER_APPLIED) != null) {
            chain.doFilter(request, response);
            return;
        }

        //Now mark request as completing this filter
        request.setAttribute(FILTER_APPLIED, Boolean.TRUE);

        //Attempt to authenticate
        Authentication authResult = attemptAuthentication(request, response);
        if (authResult == null) {
            unsuccessfulAuthentication(request, response, new LockedException("Forbidden"));
        } else {
            successfulAuthentication(request, response, chain, authResult);
        }
    }

    /**
     * Attempt to authenticate request - basically just pass over to another method to authenticate request headers
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication userAuthenticationToken = SecurityContextHolder.getContext().getAuthentication();
        if(userAuthenticationToken == null) throw new AuthenticationServiceException(MessageFormat.format("Error | {0}", "Bad Token"));
        return userAuthenticationToken;
    }

}
