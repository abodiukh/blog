package com.bodiukh.blog.dto;

import java.util.List;

public class RoleDTO {

    private String name;

    private List<String> rights;

    public RoleDTO() {
    }

    public RoleDTO(final String name, final List<String> rights) {
        this.name = name;
        this.rights = rights;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public List<String> getRights() {
        return rights;
    }

    public void setRights(final List<String> rights) {
        this.rights = rights;
    }
}
