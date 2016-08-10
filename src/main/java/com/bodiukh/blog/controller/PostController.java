package com.bodiukh.blog.controller;

import java.util.Date;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;
import com.bodiukh.blog.service.PostService;
import com.bodiukh.blog.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired(required = true)
    @Qualifier(value = "postService")
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Autowired(required = true)
    @Qualifier(value = "userDetailsService")
    public void setPostService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "common";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") String id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        return "post";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") String id, @RequestBody PostDTO postDTO, Model model) {
        Post post = postService.getPost(id);
        post.setTitle(postDTO.getTitle());
        post.setText(postDTO.getText());
        postService.updatePost(post);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity addPost(@RequestBody PostDTO postDTO) {
        User user = userService.getUserByName(postDTO.getAuthor());
        Post post = new Post();
        post.setAuthor(user);
        post.setTitle(postDTO.getTitle());
        postService.addPost(post);
        return new ResponseEntity<>(post.getId().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}/publish", method = RequestMethod.POST)
    public String publishPost(@PathVariable("id") String id, Model model) {
        Post post = postService.getPost(id);
        post.setPublished(true);
        post.setDate(new Date());
        postService.updatePost(post);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/{id}/unpublish", method = RequestMethod.POST)
    public String unpublishPost(@PathVariable("id") String id, Model model) {
        Post post = postService.getPost(id);
        post.setPublished(false);
        postService.updatePost(post);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("id") String id, Model model) {
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}