package com.hacker.news.controller;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Comment;
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

import java.util.ArrayList;
import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

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

    @RequestMapping(value = "/posts", method = GET)
    public Object list(Model model) {
        List<Post> listOfPost= postService.fetchListOfPosts();
        return listOfPost;
    }

    @RequestMapping(value = "/post/comments", method = GET)
    public Object listCommentsOnPost(@RequestParam("postId") String postId, Model model) {
        PostDto postDto = postService.fetchPost(postId);
        List<CommentDto> commentDtoList = postDto.getCommentDto();
        List<Object> objects = new ArrayList<>();
        objects.add(postDto);
        objects.add(commentDtoList);
        return commentDtoList;
    }

    @RequestMapping(value = "/comments", method = GET)
    public Object listComment(Model model) {
        List<Comment> listOfComment= commentService.fetchAllComments();
        return listOfComment;
    }

    @RequestMapping("/users")
    public Object createUser(){
        return userService.getAllUsers();
    }
}
