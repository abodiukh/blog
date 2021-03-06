package com.bodiukh.blog.config;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

@Component
public class CustomRequestMatcher implements RequestMatcher {

    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private Pattern notAllowedMethods = Pattern.compile("^(POST|PUT|DELETE)$");

    public boolean matches(final HttpServletRequest request) {
        String uri = request.getRequestURI();
        String method = request.getMethod();
        return !(isPublicInfo(method, uri) || isLogin(method, uri) || readPost(method, uri) || isRegistration(method, uri));
    }

    private boolean isPublicInfo(String method, String uri) {
        return allowedMethods.matcher(method).matches() &&
                (uri.equals("/") || uri.equals("/post/all") || uri.contains("/resources/"));
    }

    private boolean isLogin(String method, String uri) {
        return notAllowedMethods.matcher(method).matches() &&
                (uri.equals("/user/login") || uri.equals("/user/logout") || uri.equals("/user/isAuthorized")
                        || uri.equals("/user/registration") || uri.startsWith("/user/registration/confirm"));
    }

    private boolean isRegistration(String method, String uri) {
        return (method.equals(HttpMethod.POST.toString()) && uri.equals("/user/registration"))
                || (method.equals(HttpMethod.GET.toString()) && uri.startsWith("/user/registration/confirm"));
    }

    private boolean readPost(String method, String uri) {
        Pattern allowedUrlPattern = Pattern.compile("^/post/.[^/]*$");
        return allowedMethods.matcher(method).matches() && allowedUrlPattern.matcher(uri).matches();
    }

}
