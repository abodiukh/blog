package com.bodiukh.blog.dto;

import javax.validation.constraints.NotNull;

import com.bodiukh.blog.annotations.PasswordMatches;
import com.bodiukh.blog.annotations.ValidEmail;

import org.hibernate.validator.constraints.NotEmpty;


@PasswordMatches
public class UserDTO {

    private Integer id;

    @NotNull
    @NotEmpty
    private String name;

    private String role;

    private boolean enabled;

    @NotNull
    @NotEmpty
    @ValidEmail
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String confirmedPassword;

    public UserDTO() {
    }

    public UserDTO(final Integer id, final String email, final String name, final String role, final boolean enabled) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.role = role;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        this.enabled = enabled;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(final String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
