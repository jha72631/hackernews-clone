package com.hacker.news.repositories;

import com.hacker.news.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreatedAt(new Date().getTime());
        mongoTemplate.save(comment);
        return comment;
    }

    @Override
    public List<Comment> getAllComment() {
        return mongoTemplate.findAll(Comment.class);
    }

    @Override
    public Comment findOneByName(String name) {
        return null;
    }

    @Override
    public Comment findCommentByCommentId(String commentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("commentId").is(commentId));
        return mongoTemplate.findOne(query, Comment.class);
    }

    @Override
    public List<Comment> findCommentByParentStoryId(String parentStoryId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("parentStoryId").is(parentStoryId));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public List<Comment> findCommentByParentCommentId(String parentCommentId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("parentCommentId").is(parentCommentId));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public List<Comment> fetchCommentsByParentType(String parentType) {
        Query query = new Query();
        query.addCriteria(Criteria.where("parentType").is(parentType));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public List<Comment> findByAuthorName(String authorName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(authorName));
        return mongoTemplate.find(query, Comment.class);
    }

    @Override
    public Comment updateOneComment(Comment comment) {
        return null;
    }

    @Override
    public void deleteComment(Comment comment) {
        mongoTemplate.remove(comment);
    }
}
