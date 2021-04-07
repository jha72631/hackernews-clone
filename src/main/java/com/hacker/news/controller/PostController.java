package com.hacker.news.controller;

import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Post;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class PostController {

    public static final int PAGE_SIZE = 3;
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

    @RequestMapping(value = "/", method = GET)
    public String list(Model model) {
        List<Post> posts = postService.fetchListOfPosts();
        return findPaginated(1, model);
    }

    @RequestMapping(value = "/page",method = GET)
    public String findPaginated(@RequestParam("page") int pageNo,
                                Model model) {
        String sortField =  "createdAt";
        String sortDirection= "asc";
        Page<Post> posts = postService.getAllPostPaginated(pageNo, PAGE_SIZE,sortField,sortDirection);
        model.addAttribute("posts", posts)
                .addAttribute("page", pageNo)
                .addAttribute("size", 3);
        if (pageNo < posts.getTotalPages()) {
            model.addAttribute("nextPage", pageNo + 1);
        }
        boolean isLoggedIn =  isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            model.addAttribute("username", "Sanjay@gmail.com")
                    .addAttribute("votedPosts", new ArrayList<Post>());
        }
        return "index";
    }

    @RequestMapping(value = "/post", method = GET)
    public String read(@RequestParam("id") String id, Model model) {
        PostDto post = postService.fetchPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    private boolean isLoggedIn() {
        return false;
    }
}