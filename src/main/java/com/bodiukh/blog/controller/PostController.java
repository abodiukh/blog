package com.bodiukh.blog.controller;

import com.bodiukh.blog.domain.Post;
import com.bodiukh.blog.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Autowired(required = true)
    @Qualifier(value = "postService")
    public void setPostService(PostService ps) {
        this.postService = ps;
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
    public String updatePost(@PathVariable("id") String id, @RequestBody Post post, Model model) {
        postService.updatePost(post);
        model.addAttribute("post", postService.getPost(id));
        return "post";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addPost(@RequestBody Post post, Model model) {
        postService.addPost(post);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping(value = "/{id}/publish", method = RequestMethod.POST)
    public String publishPost(@PathVariable("id") String id, Model model) {
        return "post";
    }

}