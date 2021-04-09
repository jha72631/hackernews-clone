package com.hacker.news.controller;

import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Post;
import com.hacker.news.model.Comment;
import com.hacker.news.security.UserPrincipal;
import com.hacker.news.service.CommentService;
import com.hacker.news.service.PostService;
import com.hacker.news.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class PostController {

    public static final int PAGE_SIZE = 3;
    public static final int FIRST_PAGE = 1;
    private final PostService postService;
    private final CommentService commentService;
    private final UserService userService;

    public PostController(PostService postService, CommentService commentService, UserService userService) {
        this.postService = postService;
        this.commentService = commentService;
        this.userService = userService;
    }


    @RequestMapping(value = "/post/new", method = GET)
    public String editor(Model model) {
        Post post = new Post();
        isLoggedIn(model);
        model.addAttribute("post",post);
        return "post-editor";
    }

    @RequestMapping(value = "/post", method = POST)
    public String create(@ModelAttribute("post") Post post) {
        postService.createPost(post);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = GET)
    public String findAllPost(Model model) {
        String type = "All";
        return findPaginated(FIRST_PAGE, type ,model);
    }

    @RequestMapping(value = "/post/filter", method = GET)
    public String findAllByFilter(@RequestParam("type") String type, Model model) {
        return findPaginated(FIRST_PAGE, type,model);
    }

    @RequestMapping(value = "/page",method = GET)
    public String findPaginated(@RequestParam("page") int pageNo,
                                @RequestParam("type") String postType,
                                Model model) {
        String sortField =  "createdAt";
        String sortDirection= "asc";
        Page<Post> posts = postService.getAllPostPaginated(pageNo, PAGE_SIZE,sortField,sortDirection,postType);
        model.addAttribute("posts", posts)
                .addAttribute("page", pageNo)
                .addAttribute("size", 3)
                .addAttribute("type",postType);
        if (pageNo < posts.getTotalPages()) {
            model.addAttribute("nextPage", pageNo + 1);
        }
        isLoggedIn(model);
        return "index";
    }

    @RequestMapping(value = "/post/{id}", method = GET)
    public String read(@PathVariable("id") String id, Model model) {
        PostDto postDto = postService.fetchPost(id);
        isLoggedIn(model);
        model.addAttribute("post", postDto.getPost())
                .addAttribute("postIsVoted", true)
                .addAttribute("createComment",new Comment(id,"Post"))
                .addAttribute("comments",postDto.getCommentDto());
        return "viewpost";
    }

    @RequestMapping(value = "/post/downVote/page", method = GET)
    public String downVotePost(@RequestParam("page") int page, @RequestParam("type") String type,
                             @RequestParam("postId") String postId, Model model){
        postService.updatePostScoreAndUserUpvotedSubmission(userService.currentUser().getUsername(),postId,false);
        return findPaginated(page,type,model);
    }

    @RequestMapping(value = "/post/upVote/page", method = GET)
    public String upVotePost(@RequestParam("page") int page, @RequestParam("type") String type,
                             @RequestParam("postId") String postId, Model model){
        postService.updatePostScoreAndUserUpvotedSubmission(userService.currentUser().getUsername(),postId,true);
        return findPaginated(page,type,model);
    }

    void isLoggedIn(Model model){
        boolean isLoggedIn =  userService.isLoggedIn();
        model.addAttribute("isLoggedIn", isLoggedIn);
        if (isLoggedIn) {
            UserPrincipal currentUser = userService.currentUser();
            model.addAttribute("username", currentUser.getUsername())
                    .addAttribute("votedComments",userService
                            .getListOfUpvotedSubmission(currentUser.getUsername()))
                    .addAttribute("votedPosts", userService
                            .getListOfUpvotedSubmission(currentUser.getUsername()));
        }
    }
}