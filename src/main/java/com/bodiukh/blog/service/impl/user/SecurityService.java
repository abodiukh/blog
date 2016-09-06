package com.bodiukh.blog.service.impl.user;

import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private UserService userService;

    @Autowired(required = true)
    @Qualifier(value = "userDetailsService")
    public void setPostService(UserService userService) {
        this.userService = userService;
    }

    public boolean hasPermission(String userRight) {
        return userService.getRights().contains(Right.valueOf(userRight.toUpperCase()));
    }

}
