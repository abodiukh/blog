package com.bodiukh.blog.service.impl;

import java.util.Date;
import java.util.List;

import com.bodiukh.blog.dao.PostDAO;
import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;
import com.bodiukh.blog.service.PostService;

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
    public List<Post> getPostsOfAuthor(final String author) {
        return postDAO.getPostsOfAuthor(author);
    }

    @Override
    @Transactional
    public Post addPost(final PostDTO postDTO, final User user) {
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.getTitle());
        postDAO.addPost(post);
        return post;
    }

    @Override
    @Transactional
    public Post updatePost(final String id, PostDTO postDTO) {
        Post post = getPost(id);
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        postDAO.updatePost(post);
        return post;
    }

    @Override
    public Post publishPost(final String id, final boolean publish) {
        Post post = getPost(id);
        post.setPublished(publish);
        if (publish) {
            post.setDate(new Date());
        }
        return post;
    }

    @Override
    @Transactional
    public void removePost(final String id) {
        postDAO.removePost(id);
    }
}
