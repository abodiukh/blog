package com.bodiukh.blog.service;

import java.util.List;

import com.bodiukh.blog.domain.Verification;
import com.bodiukh.blog.exceptions.EmailExistsException;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.UserDTO;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

    List<User> getAllUsers();

    List<String> getRoles();

    User addUser(UserDTO userDTO) throws EmailExistsException;

    User updateUser(UserDTO userDTO);

    void createVerificationToken(User user, String token);

    Verification getVerificationToken(String token);
}
