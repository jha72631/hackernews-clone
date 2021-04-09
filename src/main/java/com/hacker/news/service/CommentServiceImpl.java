package com.hacker.news.service;

import com.hacker.news.model.Comment;
import com.hacker.news.repositories.CommentRepository;
import com.hacker.news.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private UserService userService;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public void createComment(Comment comment) {
        comment.setCreatedAt(new Date().getTime());
        comment.setScore(1);
        comment.setAuthor(userService.currentUser().getUsername());
        Comment comment1 = commentRepository.saveComment(comment);
        userService.updateUserCommentSubmissions(comment1.getAuthor(), comment1.getCommentId(), Boolean.TRUE);
    }

    @Override
    public Comment fetchCommentByCommentId(String commentId) {
        return commentRepository.findCommentByCommentId(commentId);
    }

    @Override
    public List<Comment> fetchCommentByParentCommentId(String parentCommentId) {
        return commentRepository.findCommentByParentCommentId(parentCommentId);
    }

    @Override
    public List<Comment> fetchCommentByParentStoryId(String parentStoryId) {
        return commentRepository.findCommentByParentStoryId(parentStoryId);
    }

    @Override
    public List<Comment> fetchCommentByParentType(String parentType) {
        return commentRepository.fetchCommentsByParentType(parentType);
    }

    @Override
    public List<Comment> fetchCommentByParentTypeAndPostId(String parentType, String postId) {
        return commentRepository.fetchCommentsByParentTypeAndPostId(parentType, postId);
    }

    @Override
    public void updateCommentScoreAndUpvotedCommentSubmissions(String userName, String commentId, boolean isToBeAdded) {
        commentRepository.updateCommentScore(commentId, isToBeAdded);
        userService.updateUserUpvotedCommentSubmissions(userName, commentId, isToBeAdded);
    }

    @Override
    public List<Comment> fetchAllComments() {
        return commentRepository.getAllComment();
    }

    @Override
    public void deleteComments(Comment comment) {
        commentRepository.deleteComment(comment);
        return ;
    }

}
