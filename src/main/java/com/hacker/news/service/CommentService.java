package com.hacker.news.service;

import com.hacker.news.model.Comment;

import java.util.List;

public interface CommentService {
    void createComment(Comment comment);
    Comment fetchCommentByCommentId(String commentId);
    List<Comment> fetchCommentByParentCommentId(String parentCommentId);
    List<Comment> fetchCommentByParentStoryId(String parentStoryId);
    List<Comment> fetchCommentByParentType(String parentType);
    List<Comment> fetchCommentByParentTypeAndPostId(String parentType, String postId);
    List<Comment> fetchAllComments();
    void deleteComments(Comment comment);
}
