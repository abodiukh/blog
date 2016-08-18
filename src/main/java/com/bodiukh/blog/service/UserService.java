package com.bodiukh.blog.service;

import java.util.EnumSet;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.service.impl.UserRight;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

    EnumSet<UserRight> getRightByRoles();

}
