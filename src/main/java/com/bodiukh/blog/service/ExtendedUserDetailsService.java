package com.bodiukh.blog.service;

import java.util.EnumSet;

import com.bodiukh.blog.service.impl.user.Right;
import com.bodiukh.blog.service.impl.user.Role;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface ExtendedUserDetailsService extends UserDetailsService {

    UserDetails getUserDetails();

    EnumSet<Role> getRolesByUser();

    EnumSet<Right> getRightsByUser();
}
