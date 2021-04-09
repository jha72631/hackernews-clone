package com.hacker.news.repositories;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;

import java.util.List;

public interface CommentRepository {
    Comment saveComment(Comment comment);
    List<Comment> getAllComment();
    Comment findOneByName(String name);
    Comment findCommentByCommentId(String commentId);
    List<Comment> findCommentByParentStoryId(String parentStoryId);
    List<Comment> findCommentByParentCommentId(String parentCommentId);
    List<Comment> fetchCommentsByParentType(String parentType);
    List<Comment> fetchCommentsByParentTypeAndPostId(String parentType, String postId);
    List<Comment> findByAuthorName(String name);
    void updateCommentScore(String commentId, boolean isToBeAdded);
    Comment updateOneComment(Comment comment);
    void deleteComment(Comment comment);
}
