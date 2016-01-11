package com.bodiukh.blog.controller;

import javax.ws.rs.Consumes;

import com.bodiukh.blog.domain.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    @Qualifier("userDetailsService")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("passwordEncoder")
    private PasswordEncoder encoder;

    private AuthenticationManagerImpl authenticationManager = new AuthenticationManagerImpl();

    @Consumes("application/json")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<User> login(@RequestBody User user) {
        Authentication request = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        Authentication result = authenticationManager.authenticate(request);
        SecurityContextHolder.getContext().setAuthentication(result);
        User resultUser = new User(user.getUsername(), user.getPassword());
        return new ResponseEntity<>(resultUser, HttpStatus.OK);
    }

    class AuthenticationManagerImpl implements AuthenticationManager {

        public Authentication authenticate(Authentication auth) throws AuthenticationException {
            DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
            authenticationProvider.setUserDetailsService(userDetailsService);
            authenticationProvider.setPasswordEncoder(encoder);
            return authenticationProvider.authenticate(auth);
        }
    }
}
