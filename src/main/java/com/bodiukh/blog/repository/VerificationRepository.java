package com.bodiukh.blog.repository;

import com.bodiukh.blog.domain.Verification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification, Integer> {

    Verification findByToken(String token);
}
