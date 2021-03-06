package com.bodiukh.blog.controller;

import javax.validation.Valid;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;
import com.bodiukh.blog.service.ExtendedUserDetailsService;
import com.bodiukh.blog.service.PostService;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/post")
public class PostController {

    private PostService postService;
    private UserService userService;
    private ExtendedUserDetailsService userDetailsService;

    @Autowired(required = true)
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired(required = true)
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired(required = true)
    public void setUserServiceDetails(ExtendedUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "common";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") String id, Model model) {
        Post post = postService.getPost(id);
        model.addAttribute("post", post);
        model.addAttribute("readonly", postService.isReadonly(id));
        return "post";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addPost(@RequestBody @Valid PostDTO postDTO) {
        String username = userDetailsService.getUserDetails().getUsername();
        User user = userService.getUserByName(username);
        Post post = postService.addPost(postDTO, user);
        return new ResponseEntity<>(post.getId().toString(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity updatePost(@PathVariable("id") String id, @RequestBody @Valid PostDTO postDTO) {
        postService.updatePost(id, postDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/publish", method = RequestMethod.POST)
    public ResponseEntity publishPost(@PathVariable("id") String id) {
        postService.publishPost(id, true);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}/unpublish", method = RequestMethod.POST)
    public ResponseEntity unpublishPost(@PathVariable("id") String id) {
        postService.publishPost(id, false);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("id") String id) {
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}