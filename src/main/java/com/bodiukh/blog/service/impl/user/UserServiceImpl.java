package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.List;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.UserRole;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.repository.UserRepository;
import com.bodiukh.blog.repository.UserRoleRepository;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public User getUserById(final String id) {
        return userRepository.findOne(new Integer(id));
    }

    @Override
    public User getUserByName(final String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<String> getRoles() {
        List<UserRole> userRoles = userRoleRepository.findAll();
        List<String> result = new ArrayList<>();
        for (UserRole userRole : userRoles) {
            result.add(userRole.getRole());
        }
        return result;
    }

    @Override
    public User addUser(final UserDTO userDTO) {
        User user = new User(userDTO.getName(), userDTO.getPassword());
        user.setEnabled(false);
        UserRole userRole = new UserRole();
        String role = Role.WRITER.toString().toLowerCase();
        userRole.setUserRoleId(userRoleRepository.findByRole(role).getUserRoleId());
        user.setUserRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setUserRole(userRoleRepository.findByRole(userDTO.getRole()));
        user.setEnabled(userDTO.isEnabled());
        return userRepository.save(user);
    }
}
