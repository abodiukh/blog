package com.bodiukh.blog.service.impl.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.domain.UserRight;
import com.bodiukh.blog.domain.UserRole;
import com.bodiukh.blog.repository.UserRepository;
import com.bodiukh.blog.repository.UserRoleRepository;
import com.bodiukh.blog.service.ExtendedUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a.bodiukh
 */
@Service("userDetailsService")
@Transactional
public class UserDetailsServiceImpl implements ExtendedUserDetailsService {

    private String defaultRolePrefix = "ROLE_";

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserRoleRepository userRoleRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        User user = userRepository.findByName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);

    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user,
                                                                                          List<GrantedAuthority> authorities) {
        String encodedPassword = user.getPassword();
        return new org.springframework.security.core.userdetails.User(user.getName(), encodedPassword,
                user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(UserRole userRole) {
        Set<GrantedAuthority> setAuths = new HashSet<>();
        setAuths.add(new SimpleGrantedAuthority(addDefaultRolePrefix(userRole.getName())));
        return new ArrayList<>(setAuths);
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
    public EnumSet<Right> getRightsByUser() {
        UserDetails userDetails = getUserDetails();
        if (userDetails != null) {
            return getRightsByRoles(userDetails.getAuthorities());
        }
        return EnumSet.of(Right.READ);
    }

    private EnumSet<Right> getRightsByRoles(final Collection<? extends GrantedAuthority> roles) {
        Set<Right> userRights = new HashSet<>();
        for (GrantedAuthority role : roles) {
            UserRole userRole = userRoleRepository.findByName(removeDefaultRolePrefix(role.getAuthority()));
            for (UserRight userRight : userRole.getRights()) {
                userRights.add(Right.valueOf(userRight.getName().toUpperCase()));
            }
        }
        return EnumSet.copyOf(userRights);
    }

    @Override
    public UserDetails getUserDetails() {
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
