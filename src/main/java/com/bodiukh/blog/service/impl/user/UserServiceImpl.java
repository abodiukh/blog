package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.UserRight;
import com.bodiukh.blog.domain.UserRole;
import com.bodiukh.blog.domain.Verification;
import com.bodiukh.blog.dto.RoleDTO;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.exceptions.EmailExistsException;
import com.bodiukh.blog.repository.UserRepository;
import com.bodiukh.blog.repository.UserRightRepository;
import com.bodiukh.blog.repository.UserRoleRepository;
import com.bodiukh.blog.repository.VerificationRepository;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Resource
    private UserRightRepository userRightRepository;

    @Resource
    private VerificationRepository verificationRepository;

    @Override
    public User getUserById(final String id) {
        return userRepository.findOne(new Integer(id));
    }

    @Override
    public User getUserByName(final String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> users = userRepository.findAll().stream()
                .filter(u -> !u.getName().equals("admin")).collect(Collectors.toList());
        List<UserDTO> result = new ArrayList<>();
        for (User user : users) {
            result.add(new UserDTO(user.getId(), user.getEmail(), user.getName(), user.getRole().getName(), user.isEnabled()));
        }
        return result;
    }

    @Override
    public List<RoleDTO> getRoles() {
        List<UserRole> roles = userRoleRepository.findAll();
        List<RoleDTO> result = new ArrayList<>();
        for (UserRole userRole : roles) {
            List<String> rights = userRole.getRights().stream().map(UserRight::getName).collect(Collectors.toList());
            result.add(new RoleDTO(userRole.getName(), rights));
        }
        return result;
    }

    @Override
    public List<String> getRights() {
        return userRightRepository.findAll().stream().map(UserRight::getName).collect(Collectors.toList());
    }

    @Override
    public void updateRole(final RoleDTO roleDTO) {
        UserRole userRole = userRoleRepository.findByName(roleDTO.getName());
        Set<UserRight> rightSet = new HashSet<>();
        for (String rightName : roleDTO.getRights()) {
            rightSet.add(userRightRepository.findByName(rightName));
        }
        userRole.setRights(rightSet);
        userRoleRepository.save(userRole);
    }

    @Override
    public User addUser(final UserDTO userDTO) throws EmailExistsException {
        if (emailExist(userDTO.getEmail())) {
            throw new EmailExistsException("There is an account with that email");
        }
        User user = new User(userDTO.getEmail(), userDTO.getName(), passwordEncoder.encode(userDTO.getPassword()));
        user.setEnabled(false);
        UserRole userRole = new UserRole();
        String role = Role.READER.toString().toLowerCase();
        userRole.setId(userRoleRepository.findByName(role).getId());
        user.setRole(userRole);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(final UserDTO userDTO) {
        User user = userRepository.getOne(userDTO.getId());
        user.setRole(userRoleRepository.findByName(userDTO.getRole()));
        user.setEnabled(userDTO.isEnabled());
        return userRepository.save(user);
    }

    @Override
    public void createVerificationToken(final User user, final String token) {
        verificationRepository.save(new Verification(token, user));
    }

    @Override
    public Verification getVerificationToken(final String token) {
        return verificationRepository.findByToken(token);
    }

    @Override
    public void deleteUser(final Integer id) {
        userRepository.delete(id);
    }

    private boolean emailExist(String email) {
        User user = userRepository.findByEmail(email);
        return user != null;
    }
}
