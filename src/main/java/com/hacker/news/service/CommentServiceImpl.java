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

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    public void createComment(Comment comment, String parentPostId, String parentCommentID, String parentType) {
        comment.setParentStoryId(parentPostId);
        comment.setParentCommentId(parentCommentID);
        comment.setCreatedAt(new Date().getTime());
        comment.setParentType(parentType);

        commentRepository.saveComment(comment);
    }

    public Comment fetchCommentByCommentId(String commentId) {
        return commentRepository.findCommentByCommentId(commentId);
    }

    public List<Comment> fetchCommentByParentCommentId(String parentCommentId) {
        return commentRepository.findCommentByParentCommentId(parentCommentId);
    }

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
    public List<Comment> fetchAllComments() {
        return commentRepository.getAllComment();
    }

    @Override
    public void deleteComments(Comment comment) {
        commentRepository.deleteComment(comment);
        return;
    }

}
