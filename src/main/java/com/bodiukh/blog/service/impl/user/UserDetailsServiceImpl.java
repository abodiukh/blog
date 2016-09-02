package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

        com.bodiukh.blog.domain.User user = userRepository.findByUsername(username);
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

    private List<GrantedAuthority> buildUserAuthority(com.bodiukh.blog.domain.UserRole userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(addDefaultRolePrefix(userRole.getRole())));
        return new ArrayList<>(setAuths);
    }

    @Override
    public com.bodiukh.blog.domain.User getUserById(final String id) {
        return userRepository.findOne(new Integer(id));
    }

    @Override
    public com.bodiukh.blog.domain.User getUserByName(final String name) {
        return userRepository.findByUsername(name);
    }

    @Override
    public EnumSet<UserRole> getRoles() {
        Set<UserRole> userRoles = new HashSet<>();
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            for (GrantedAuthority role : userDetails.getAuthorities()) {
                userRoles.add(UserRole.valueOf(removeDefaultRolePrefix(role.getAuthority()).toUpperCase()));
            }
            return EnumSet.copyOf(userRoles);
        }
        return EnumSet.of(UserRole.READER);
    }

    @Override
    public EnumSet<UserRight> getRights() {
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            return getRightByRoles(userDetails.getAuthorities());
        }
        return EnumSet.of(UserRight.READ);
    }

    private EnumSet<UserRight> getRightByRoles(final Collection<? extends GrantedAuthority> roles) {
        Set<UserRight> userRights = new HashSet<>();
        for (GrantedAuthority role : roles) {
            com.bodiukh.blog.domain.UserRole userRole = userRoleRepository.findByRole(removeDefaultRolePrefix(role.getAuthority()));
            for (com.bodiukh.blog.domain.UserRight userRight : userRole.getRights()) {
                userRights.add(UserRight.valueOf(userRight.getRightName().toUpperCase()));
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
