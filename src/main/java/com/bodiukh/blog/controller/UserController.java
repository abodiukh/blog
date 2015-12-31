package com.bodiukh.blog.controller;

import javax.ws.rs.Consumes;

import com.bodiukh.blog.domain.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author a.bodiukh
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Consumes("application/json")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) {
        User resultUser = new User(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }
}
