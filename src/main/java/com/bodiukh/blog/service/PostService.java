package com.bodiukh.blog.service;

import java.util.List;

import com.bodiukh.blog.domain.Post;

/**
 * @author a.bodiukh
 */
public interface PostService {

    Post getPost(String id);

    List<Post> getPosts();

    Integer addPost(Post post);

    void updatePost(Post post);

    List<Post> getPostsOfAuthor(String author);

    void removePost(String id);

    void publish(String id);
}
