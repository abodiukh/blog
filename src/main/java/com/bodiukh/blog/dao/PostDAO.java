package com.bodiukh.blog.dao;

import java.util.List;

import com.bodiukh.blog.domain.Post;

/**
 * @author a.bodiukh
 */
public interface PostDAO {

    Post getPost(String id);

    void addPost(Post post);

    void updatePost(Post post);

    List<Post> getPostsOfAuthor(String author);

}
