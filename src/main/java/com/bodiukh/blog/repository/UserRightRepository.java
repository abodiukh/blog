package com.bodiukh.blog.repository;

import com.bodiukh.blog.domain.UserRight;
import com.bodiukh.blog.domain.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRightRepository extends JpaRepository<UserRight, Integer> {

    UserRight findByName(String name);
}
