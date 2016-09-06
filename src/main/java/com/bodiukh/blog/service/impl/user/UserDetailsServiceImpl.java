package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.UserRight;
import com.bodiukh.blog.domain.UserRole;
import com.bodiukh.blog.dto.UserDTO;
import com.bodiukh.blog.repository.UserRepository;
import com.bodiukh.blog.repository.UserRoleRepository;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a.bodiukh
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    private String defaultRolePrefix = "ROLE_";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    @Qualifier("encoder")
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                            List<GrantedAuthority> authorities) {
        String encodedPassword = encoder.encode(user.getPassword());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), encodedPassword,
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(addDefaultRolePrefix(userRole.getRole())));
        return new ArrayList<>(setAuths);
    }

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
    public EnumSet<Role> getRolesByUser() {
        Set<Role> userRoles = new HashSet<>();
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            for (GrantedAuthority role : userDetails.getAuthorities()) {
                userRoles.add(Role.valueOf(removeDefaultRolePrefix(role.getAuthority()).toUpperCase()));
            }
            return EnumSet.copyOf(userRoles);
        }
        return EnumSet.of(Role.READER);
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
    public EnumSet<Right> getRights() {
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            return getRightsByRoles(userDetails.getAuthorities());
        }
        return EnumSet.of(Right.READ);
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

    private EnumSet<Right> getRightsByRoles(final Collection<? extends GrantedAuthority> roles) {
        Set<Right> userRights = new HashSet<>();
        for (GrantedAuthority role : roles) {
            UserRole userRole = userRoleRepository.findByRole(removeDefaultRolePrefix(role.getAuthority()));
            for (UserRight userRight : userRole.getRights()) {
                userRights.add(Right.valueOf(userRight.getRightName().toUpperCase()));
            }
        }
        return EnumSet.copyOf(userRights);
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    private String addDefaultRolePrefix(String role) {
        return defaultRolePrefix + role;
    }

    private String removeDefaultRolePrefix(String role) {
        return role.substring(defaultRolePrefix.length());
    }
}
