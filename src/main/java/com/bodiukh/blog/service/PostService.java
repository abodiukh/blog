package com.bodiukh.blog.service;

import java.util.List;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * @author a.bodiukh
 */
public interface PostService {

    Post getPost(String id);

    List<Post> getPosts();

    List<Post> getPostsOfAuthor(String author);

    @PreAuthorize("hasRole('ROLE_writer')")
    Post addPost(PostDTO postDTO, User user);

    @PreAuthorize("hasRole('ROLE_writer')")
    Post updatePost(String id, PostDTO postDTO);

    @PreAuthorize("hasRole('ROLE_writer')")
    Post publishPost(String id, boolean publish);

    @PreAuthorize("hasRole('ROLE_writer')")
    void removePost(String id);
}
