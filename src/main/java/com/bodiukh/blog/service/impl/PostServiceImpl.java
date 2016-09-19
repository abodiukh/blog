package com.bodiukh.blog.service.impl;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;
import com.bodiukh.blog.repository.PostRepository;
import com.bodiukh.blog.service.ExtendedUserDetailsService;
import com.bodiukh.blog.service.PostService;
import com.bodiukh.blog.service.impl.user.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author a.bodiukh
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    @Autowired
    private ExtendedUserDetailsService userDetailsService;

    @Resource
    private PostRepository postRepository;

    @Override
    public Post getPost(final String id) {
        return postRepository.findOne(new Integer(id));
    }

    @Override
    public boolean isReadonly(final String postId) {
        EnumSet<Role> rolesByUser = userDetailsService.getRolesByUser();
        UserDetails userDetails = userDetailsService.getUserDetails();
        return userDetails == null || EnumSet.of(Role.READER).equals(rolesByUser)
                || (EnumSet.of(Role.READER, Role.WRITER).containsAll(rolesByUser)
                && !getPost(postId).getAuthor().getName().equals(userDetails.getUsername()));

    }

    @Override
    public List<Post> getPosts() {
        List<Post> all = postRepository.findAll();
        EnumSet<Role> rolesByUser = userDetailsService.getRolesByUser();
        List<Post> result = all.stream().filter(Post::isPublished).collect(Collectors.toList());
        if (EnumSet.of(Role.READER).equals(rolesByUser)) {
            return result;
        }
        if (EnumSet.of(Role.READER, Role.WRITER).containsAll(rolesByUser)) {
            UserDetails userDetails = userDetailsService.getUserDetails();
            if (userDetails != null) {
                List<Post> unpublishedUserPosts =
                        getPostsOfAuthor(userDetails.getUsername()).stream().filter(p -> !p.isPublished()).collect(Collectors.toList());
                result.addAll(unpublishedUserPosts);
                return result;
            }
        }
        return all;
    }

    @Override
    public List<Post> getPostsOfAuthor(final String author) {
        return postRepository.findByAuthorName(author);
    }

    @Override
    public Post addPost(final PostDTO postDTO, final User user) {
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.getTitle());
        postRepository.save(post);
        return post;
    }

    @Override
    public Post updatePost(final String id, PostDTO postDTO) {
        Post post = getPost(id);
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        postRepository.save(post);
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
    public void removePost(final String id) {
        postRepository.delete(new Integer(id));
    }
}
