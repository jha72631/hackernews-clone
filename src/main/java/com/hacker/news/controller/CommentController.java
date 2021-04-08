package com.hacker.news.controller;

import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;
import com.hacker.news.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CommentController {

    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @RequestMapping(value = "/commentOnPost", method = POST)
    public String create(@ModelAttribute("createComment") Comment comment) {
        String postId = comment.getParentStoryId();
        commentService.createComment(comment);
        return "redirect:/post/"+postId;
    }

}
