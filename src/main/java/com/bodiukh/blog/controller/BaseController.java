package com.bodiukh.blog.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class BaseController {

    @RequestMapping(method = RequestMethod.GET)
    public String home() {
        return "redirect:/post/all";
    }

    @PreAuthorize("@securityService.hasPermission('administer')")
    @RequestMapping(path = "admin", method = RequestMethod.GET)
    public String admin() {
        return "admin";
    }

}
