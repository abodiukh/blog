package com.bodiukh.blog.controller;

import com.bodiukh.blog.service.ExtendedUserDetailsService;
import com.bodiukh.blog.service.impl.user.Right;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice(assignableTypes = {PostController.class})
public class GlobalController {

    private ExtendedUserDetailsService userService;

    @Autowired(required = true)
    public void setUserService(ExtendedUserDetailsService userService) {
        this.userService = userService;
    }

    @ModelAttribute
    public void addPermissions(Model model) {
        model.addAttribute("permissions", Right.getValuesOf(userService.getRightsByUser()));
    }
}
