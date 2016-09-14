package com.bodiukh.blog.controller;


import com.bodiukh.blog.dto.RoleDTO;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class UserController {

    private UserService userService;

    @Autowired(required = true)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public ResponseEntity getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @RequestMapping(path = "/roles", method = RequestMethod.GET)
    public ResponseEntity getRoles() {
        return new ResponseEntity<>(userService.getRoles(), HttpStatus.OK);
    }

    @RequestMapping(path = "/rights", method = RequestMethod.GET)
    public ResponseEntity getRights() {
        return new ResponseEntity<>(userService.getRights(), HttpStatus.OK);
    }

    @RequestMapping(path = "/role", method = RequestMethod.PUT)
    public ResponseEntity updateRole(@RequestBody RoleDTO roleDTO) {
        userService.updateRole(roleDTO);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/user", method = RequestMethod.PUT)
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO) {
        userService.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
