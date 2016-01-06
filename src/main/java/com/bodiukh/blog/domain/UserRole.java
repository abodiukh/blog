package com.bodiukh.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author a.bodiukh
 */
@Entity
@Table(name = "user_roles")
public class UserRole {

    private Integer userRoleId;
    private String role;
    private String rights;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(final Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Column(name = "role", unique = true, nullable = false)
    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    @Column(name = "rights")
    public String getRights() {
        return rights;
    }

    public void setRights(final String rights) {
        this.rights = rights;
    }
}
