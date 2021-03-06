package com.bodiukh.blog.repository;

import com.bodiukh.blog.domain.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByName(String username);

    User findByEmail(String email);
}
