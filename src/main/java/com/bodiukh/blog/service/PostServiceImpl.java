package com.bodiukh.blog.service;

import java.util.List;

import com.bodiukh.blog.dao.PostDAO;
import com.bodiukh.blog.domain.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a.bodiukh
 */
@Service("postService")
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private PostDAO postDAO;

    @Override
    @Transactional
    public Post getPost(final String id) {
        return postDAO.getPost(id);
    }

    @Override
    @Transactional
    public List<Post> getPosts() {
        return postDAO.getPosts();
    }

    @Override
    @Transactional
    public Integer addPost(final Post post) {
        return postDAO.addPost(post);
    }

    @Override
    @Transactional
    public void updatePost(final Post post) {
        postDAO.updatePost(post);
    }

    @Override
    @Transactional
    public List<Post> getPostsOfAuthor(final String author) {
        return postDAO.getPostsOfAuthor(author);
    }

    @Override
    @Transactional
    public void removePost(final String id) {
        postDAO.removePost(id);
    }
}
