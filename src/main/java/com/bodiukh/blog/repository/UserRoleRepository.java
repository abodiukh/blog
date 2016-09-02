package com.bodiukh.blog.repository;

import com.bodiukh.blog.domain.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {

    UserRole findByRole(String userRole);
}
