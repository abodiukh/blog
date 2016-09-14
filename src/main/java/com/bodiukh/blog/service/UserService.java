package com.bodiukh.blog.service;

import java.util.List;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.Verification;
import com.bodiukh.blog.dto.RoleDTO;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.exceptions.EmailExistsException;

/**
 * @author a.bodiukh
 */
public interface UserService {

    User getUserById(String id);

    User getUserByName(String name);

    List<UserDTO> getUsers();

    List<RoleDTO> getRoles();

    List<String> getRights();

    void updateRole(RoleDTO roleDTO);

    User addUser(UserDTO userDTO) throws EmailExistsException;

    User updateUser(UserDTO userDTO);

    void createVerificationToken(User user, String token);

    Verification getVerificationToken(String token);

    void deleteUser(Integer id);
}
