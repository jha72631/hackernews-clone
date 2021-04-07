package com.hacker.news.controller;

import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Post;
import com.hacker.news.model.User;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.PostService;
import com.hacker.news.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api")
public class ApiRestController {

    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    public ApiRestController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }


    @RequestMapping(value = "/post/new", method = GET)
    public String editor(Model model) {
        Post post = new Post();
        model.addAttribute("post",post);
        return "post-editor";
    }

    @RequestMapping(value = "/post", method = POST)
    public String create(@RequestParam("new")int create) {
        if(create==1) {
            System.out.println(create);
            //Post post = new Post("News HN", "Sanjay", "news in demand", "https://learn.mountblue.io/dashboard", "latest news");
            //postService.createPost(post);
        }

        return "redirect:/post";
    }

    @RequestMapping(value = "/", method = GET)
    public Object list(Model model) {
        List<Post> listOfPost= postService.fetchListOfPosts();
        model.addAttribute("allPost",listOfPost);
        return listOfPost;
    }

    @RequestMapping(value = "/post", method = GET)
    public String read(@RequestParam("id") String id, Model model) {
        PostDto post = postService.fetchPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @RequestMapping("/user")
    public Object createUser(){
        System.out.println(userService.isLoggedIn());
        return userService.getAllUsers();
    }
}
