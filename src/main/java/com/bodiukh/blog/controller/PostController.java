package com.bodiukh.blog.controller;

import com.bodiukh.blog.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class PostController {

    private PostService postService;

    @Autowired(required=true)
    @Qualifier(value="postService")
    public void setPostService(PostService ps){
        this.postService = ps;
    }

	@RequestMapping(value="posts", method = RequestMethod.GET)
    public String getPosts(Model model) {
        model.addAttribute("posts", postService.getPosts());
        return "posts";
    }

}