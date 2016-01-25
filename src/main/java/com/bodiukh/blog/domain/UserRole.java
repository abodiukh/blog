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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_role_id", unique = true, nullable = false)
    private Integer userRoleId;

    @Column(name = "role", unique = true, nullable = false)
    private String role;

    @Column(name = "rights")
    private String rights;

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(final Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(final String role) {
        this.role = role;
    }

    public String getRights() {
        return rights;
    }

    public void setRights(final String rights) {
        this.rights = rights;
    }
}
