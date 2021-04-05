package com.hacker.news.controller;

import com.hacker.news.model.Comment;
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

    public PostController(PostService postService, CommentService commentService,) {
        this.postService = postService;
        this.commentService = commentService;
    }


    @RequestMapping("/post/new", method = GET)
    public String editor(Model model) {
        Post post = new Post();
        model.addAttribute("post",post);
        return "post-editor";
    }

    @RequestMapping("/post", method = POST)
    public String create(@ModelAttribute("post") Post post) {
        postService.save(post);
        return "redirect:/post";
    }

    @RequestMapping("/", method = GET)
    public String list(Model model) {
        List<Post> listOfPost= postService.findAll();
        model.addAttribute("allPost",listOfPost);
        return "post-list";
    }

    @RequestMapping("/post", method = GET)
    public String read(@RequestParam("id") Long id, Model model) {
        Post post = postService.getPostById(id);
        model.addAttribute("post", post);
        return "post";
    }

}