package com.hacker.news.controller;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;
import com.hacker.news.security.UserPrincipal;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.PostService;
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
    private PostService postService;

    public CommentController(CommentService commentService, UserService userService, PostService postService) {
        this.commentService = commentService;
        this.userService = userService;
        this.postService = postService;
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

    @RequestMapping(value = "/threads", method = GET)
    public String getThreads(Model model){
        List<Comment> comments = commentService.fetchAllCommentsByAuthor(userService.currentUser().getUsername());
        isLoggedIn(model);
        model.addAttribute("comments",comments);
        return "comment-list";
    }

    @RequestMapping(value = "/comment/{commentId}",method = GET)
    public String commentOnComment(@PathVariable("commentId") String commentId, Model model){
        Comment comment = commentService.fetchCommentByCommentId(commentId);
        List<CommentDto> childComments = postService.fetchComments(commentId).getChildDto();
        isLoggedIn(model);
        Comment createComment = new Comment(comment.getParentStoryId(),commentId,"Comment");
        model.addAttribute("comment",comment)
                .addAttribute("commentIsVoted", false)
                .addAttribute("createComment", createComment)
                .addAttribute("childComments", childComments);
        return "comment";
    }

    @RequestMapping(value = "/commentOnComment",method = POST)
    public String saveCommentOnComment(@ModelAttribute("createComment") Comment comment){
       String postId = comment.getParentStoryId();
       commentService.createComment(comment);
        return "redirect:/post/"+postId;
    }

    void isLoggedIn(Model model){
        boolean isLoggedIn =  userService.isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            UserPrincipal currentUser = userService.currentUser();
            model.addAttribute("username", currentUser.getUsername())
                    .addAttribute("votedComments",userService.getListOfUpVotedCommentSubmission(currentUser.getUsername()));
        }
    }
}
