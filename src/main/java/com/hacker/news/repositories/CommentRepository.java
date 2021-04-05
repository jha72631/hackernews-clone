package com.hacker.news.repositories;

import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;

import java.util.List;

public interface CommentRepository {
    Comment saveComment(Comment comment);
    List<Comment> getAllComment();
    Comment findOneByName(String name);
    List<Comment> findCommentByParentStoryId(String parentStoryId);
    List<Comment> findCommentByParentCommentId(String parentCommentId);
    List<Comment> findByAuthorName(String name);
    Comment updateOneComment(Comment comment);
    void deleteComment(Comment comment);
}
