package com.bodiukh.blog.controller;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.domain.User;
import com.bodiukh.blog.dto.PostDTO;
import com.bodiukh.blog.service.PostService;
import com.bodiukh.blog.service.UserService;
import com.bodiukh.blog.service.impl.user.UserRight;

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
        boolean showCreator = userService.getRights().contains(UserRight.CREATE);
        model.addAttribute("posts", postService.getPosts());
        model.addAttribute("permissions", UserRight.getValuesOf(userService.getRights()));
        return "common";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String getPost(@PathVariable("id") String id, Model model) {
        model.addAttribute("post", postService.getPost(id));
        model.addAttribute("permissions", UserRight.getValuesOf(userService.getRights()));
        return "post";
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addPost(@RequestBody PostDTO postDTO) {
        User user = userService.getUserByName(postDTO.getAuthor());
        Post post = postService.addPost(postDTO, user);
        return new ResponseEntity<>(post.getId().toString(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updatePost(@PathVariable("id") String id, @RequestBody PostDTO postDTO, Model model) {
        Post post = postService.updatePost(id, postDTO);
        model.addAttribute("post", post);
        model.addAttribute("permissions", UserRight.getValuesOf(userService.getRights()));
        return "post";
    }

    @RequestMapping(value = "/{id}/publish", method = RequestMethod.POST)
    public String publishPost(@PathVariable("id") String id, Model model) {
        Post post = postService.publishPost(id, true);
        model.addAttribute("post", post);
        model.addAttribute("permissions", UserRight.getValuesOf(userService.getRights()));
        return "post";
    }

    @RequestMapping(value = "/{id}/unpublish", method = RequestMethod.POST)
    public String unpublishPost(@PathVariable("id") String id, Model model) {
        Post post = postService.publishPost(id, false);
        model.addAttribute("post", post);
        model.addAttribute("permissions", UserRight.getValuesOf(userService.getRights()));
        return "post";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePost(@PathVariable("id") String id, Model model) {
        postService.removePost(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}