package com.hacker.news.controller;

import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;
import com.hacker.news.security.UserPrincipal;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
public class CommentController {

    private CommentService commentService;
    private UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @RequestMapping(value = "/commentOnPost", method = POST)
    public String create(@ModelAttribute("createComment") Comment comment) {
        String postId = comment.getParentStoryId();
        commentService.createComment(comment);
        return "redirect:/post/"+postId;
    }

    @RequestMapping(value = "/comment", method = GET)
    public String getAllComments(Model model) {
        List<Comment> comments = commentService.fetchAllComments();
        isLoggedIn(model);
        model.addAttribute("comments",comments);
        return "comment-list";
    }

    @RequestMapping(value = "/comment/{commentId}",method = GET)
    public String commentOnComment(@PathVariable("commentId") String commentId, Model model){
        Comment comment = commentService.fetchCommentByCommentId(commentId);
        model.addAttribute("comment",comment);
        return "add-comment";
    }

    void isLoggedIn(Model model){
        boolean isLoggedIn =  userService.isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            UserPrincipal currentUser = userService.currentUser();
            model.addAttribute("username", currentUser.getUsername())
                    .addAttribute("votedComments", new ArrayList<Post>());
        }
    }
}
