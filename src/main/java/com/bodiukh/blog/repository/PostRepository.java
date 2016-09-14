package com.bodiukh.blog.repository;

import java.util.List;

import com.bodiukh.blog.domain.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Integer> {

    @Query("select post from Post post where post.author.name = ?1")
    List<Post> findByAuthorName(String author);
}
