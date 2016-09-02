package com.bodiukh.blog.service;

import java.util.EnumSet;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.service.impl.user.UserRight;
import com.bodiukh.blog.service.impl.user.UserRole;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

    EnumSet<UserRole> getRoles();

    EnumSet<UserRight> getRights();

}
