package com.bodiukh.blog.domain;

/**
 * @author a.bodiukh
 */
public class User {

    private String username;

    private String password;

    public User() {
    }

    public User(final String username, final String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
