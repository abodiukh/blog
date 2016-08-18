package com.bodiukh.blog.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author a.bodiukh
 */
@Entity
@Table(name = "user_rights")
public class UserRight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "right_id", unique = true, nullable = false)
    private Integer userRightId;

    @Column(name = "right_name", unique = true, nullable = false)
    private String rightName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rights")
    private Set<UserRole> userRoles;

    public UserRight() {
    }

    public Integer getUserRightId() {
        return userRightId;
    }

    public void setUserRightId(final Integer userRightId) {
        this.userRightId = userRightId;
    }

    public String getRightName() {
        return rightName;
    }

    public void setRightName(final String rightName) {
        this.rightName = rightName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(final Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
