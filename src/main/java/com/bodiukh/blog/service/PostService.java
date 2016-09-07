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

    boolean isReadonly(Post post);

    List<Post> getPosts();

    List<Post> getPostsOfAuthor(String author);

    @PreAuthorize("@securityService.hasPermission('create')")
    Post addPost(PostDTO postDTO, User user);

    @PreAuthorize("@securityService.hasPermission('write')")
    Post updatePost(String id, PostDTO postDTO);

    @PreAuthorize("@securityService.hasPermission('write')")
    Post publishPost(String id, boolean publish);

    @PreAuthorize("@securityService.hasPermission('delete')")
    void removePost(String id);
}
