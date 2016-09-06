package com.bodiukh.blog.dto;

public class UserDTO {

    private Integer id;
    private String name;
    private String role;
    private boolean enabled;
    private String password;

    public UserDTO() {
    }

    public UserDTO(final Integer id, final String name, final String role, final boolean enabled) {
        this.id = id;
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
}
