package com.bodiukh.blog.service;

import java.util.EnumSet;
import java.util.List;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.service.impl.user.Right;
import com.bodiukh.blog.service.impl.user.Role;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

    List<User> getAllUsers();

    EnumSet<Role> getRolesByUser();

    List<String> getRoles();

    EnumSet<Right> getRights();

    User addUser(UserDTO userDTO);

    User updateUser(UserDTO userDTO);
}
