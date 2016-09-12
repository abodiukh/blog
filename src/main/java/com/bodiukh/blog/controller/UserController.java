package com.bodiukh.blog.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.UserRight;
import com.bodiukh.blog.domain.UserRole;
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
        List<User> users = userService.getAllUsers();
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getRole().getName(), user.isEnabled()));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(path = "/roles", method = RequestMethod.GET)
    public ResponseEntity getRoles() {
        List<RoleDTO> result = new ArrayList<>();
        for (UserRole userRole : userService.getRoles()) {
            List<String> rights = userRole.getRights().stream().map(UserRight::getName).collect(Collectors.toList());
            result.add(new RoleDTO(userRole.getName(), rights));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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
