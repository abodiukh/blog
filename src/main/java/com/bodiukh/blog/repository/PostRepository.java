package com.bodiukh.blog.repository;

import java.util.List;

import com.bodiukh.blog.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByAuthor(String author);
}
