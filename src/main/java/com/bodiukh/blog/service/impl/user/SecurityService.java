package com.bodiukh.blog.service.impl.user;

import com.bodiukh.blog.service.ExtendedUserDetailsService;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class SecurityService {

    private ExtendedUserDetailsService userService;

    @Autowired(required = true)
    public void setPostService(ExtendedUserDetailsService userService) {
        this.userService = userService;
    }

    public boolean hasPermission(String userRight) {
        return userService.getRightsByUser().contains(Right.valueOf(userRight.toUpperCase()));
    }

}
