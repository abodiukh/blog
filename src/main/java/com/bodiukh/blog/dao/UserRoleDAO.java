package com.bodiukh.blog.dao;

import com.bodiukh.blog.domain.UserRole;

public interface UserRoleDAO {

    UserRole findByName(String name);

    UserRole getById(String id);
}
