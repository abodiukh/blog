package com.bodiukh.blog.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.Verification;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.exceptions.EmailExistsException;
import com.bodiukh.blog.listeners.events.OnRegistrationCompleteEvent;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

/**
 * @author a.bodiukh
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity login(@RequestBody User user, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword());
        token.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/isAuthorized", method = RequestMethod.POST)
    public ResponseEntity isAuthorized(HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/registration", method = RequestMethod.POST)
    public ResponseEntity addUser(@RequestBody @Valid UserDTO userDTO, WebRequest request, BindingResult result, Errors errors) {
        List<String> invalidMessages = new ArrayList<>();
        if (!result.hasErrors()) {
            try {
                User registered = userService.addUser(userDTO);
                String appUrl = request.getContextPath();
                eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
            } catch (EmailExistsException e) {
                invalidMessages.add(e.getMessage());
            } catch (Exception e) {
                invalidMessages.add("Invalid email");
            }

        } else {
            for (ObjectError error : errors.getAllErrors()) {
                invalidMessages.add(error.getDefaultMessage());
            }
        }
        if (!invalidMessages.isEmpty()) {
            return new ResponseEntity<>(invalidMessages, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/registration/confirm", method = RequestMethod.GET)
    public ResponseEntity confirmRegistration(WebRequest request, @RequestParam("token") String token) {
        //TODO: add localization
        Locale locale = request.getLocale();

        Verification verification = userService.getVerificationToken(token);
        if (verification == null) {
            return new ResponseEntity<>("Invalid token", HttpStatus.BAD_REQUEST);
        }

        User user = verification.getUser();
        Calendar cal = Calendar.getInstance();
        if ((verification.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return new ResponseEntity<>("Token was expired", HttpStatus.BAD_REQUEST);
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEnabled(true);
        userService.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
