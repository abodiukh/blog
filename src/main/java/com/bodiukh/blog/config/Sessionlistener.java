package com.bodiukh.blog.config;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

@Component
public class Sessionlistener implements HttpSessionListener, ApplicationContextAware {

    @Override
    public void sessionCreated(final HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().setMaxInactiveInterval(120*60);
    }

    @Override
    public void sessionDestroyed(final HttpSessionEvent sessionEvent) {

    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        if (applicationContext instanceof WebApplicationContext) {
            WebApplicationContext webApplicationContext = (WebApplicationContext) applicationContext;
            webApplicationContext.getServletContext().addListener(this);
        }
    }
}
