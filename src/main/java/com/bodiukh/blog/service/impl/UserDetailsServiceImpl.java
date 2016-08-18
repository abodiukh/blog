package com.bodiukh.blog.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.bodiukh.blog.dao.UserDAO;
import com.bodiukh.blog.dao.UserRoleDAO;
import com.bodiukh.blog.domain.UserRole;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    @Qualifier("encoder")
    private PasswordEncoder encoder;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        com.bodiukh.blog.domain.User user = userDAO.findByUsername(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }

    private User buildUserForAuthentication(com.bodiukh.blog.domain.User user,
                                            List<GrantedAuthority> authorities) {
        String encodedPassword = encoder.encode(user.getPassword());
        return new User(user.getUsername(), encodedPassword,
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {

        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        return new ArrayList<>(setAuths);
    }

    @Override
    public com.bodiukh.blog.domain.User getUserById(final String id) {
        return userDAO.getById(id);
    }

    @Override
    public com.bodiukh.blog.domain.User getUserByName(final String name) {
        return userDAO.findByUsername(name);
    }

    @Override
    public EnumSet<UserRight> getRightByRoles() {
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            return getRightByRoles(userDetails.getAuthorities());
        }
        return EnumSet.of(UserRight.READ);
    }

    private EnumSet<UserRight> getRightByRoles(final Collection<? extends GrantedAuthority> roles) {
        Set<UserRight> rights = new HashSet<>();
        for (GrantedAuthority role : roles) {
            UserRole userRole = userRoleDAO.findByName(role.getAuthority());
            for (com.bodiukh.blog.domain.UserRight userRight : userRole.getRights()) {
                rights.add(UserRight.valueOf(userRight.getRightName().toUpperCase()));
            }
        }
        return EnumSet.copyOf(rights);
    }

    private UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
}
