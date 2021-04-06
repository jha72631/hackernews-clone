package com.hacker.news.controller;

import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Post;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {

    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }


    @RequestMapping(value = "/post/new", method = GET)
    public String editor(Model model) {
        Post post = new Post();
        model.addAttribute("post",post);
        return "post-editor";
    }

    @RequestMapping(value = "/post", method = POST)
    public String create(@ModelAttribute("post") Post post) {
        postService.createPost(post);
        return "redirect:/post";
    }

    @RequestMapping("/")
    public String list(Model model) {
        List<Post> listOfPost= postService.fetchListOfPosts();
        model.addAttribute("allPost",listOfPost);
        return "index";
    }

    @RequestMapping(value = "/post", method = GET)
    public String read(@RequestParam("id") String id, Model model) {
        PostDto post = postService.fetchPost(id);
        model.addAttribute("post", post);
        return "post";
    }

}