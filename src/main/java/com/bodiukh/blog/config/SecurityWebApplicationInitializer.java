package com.bodiukh.blog.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * @author a.bodiukh
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    public SecurityWebApplicationInitializer() {
        super(SecurityConfig.class);

    }
}
