package com.bodiukh.blog.dao;

import java.util.List;

import com.bodiukh.blog.domain.Post;

/**
 * @author a.bodiukh
 */
public interface PostDAO {

    Post getPost(String id);

    List<Post> getPosts();

    void addPost(Post post);

    void updatePost(Post post);

    List<Post> getPostsOfAuthor(String author);

    void removePost(String id);

    void publish(String id);
}
