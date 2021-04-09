package com.hacker.news.model;

import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@ToString
@Document(collection = "department")
public class Comment {

    @Id
    private String commentId;
    private String author;
    private String parentStoryId;
    private String parentCommentId;
    private String parentType;
    private String text;
    private Long createdAt;
    private Integer score;

    public Comment() {
    }

    public Comment(String parentId, String parentType) {
        this.parentStoryId = parentId;
        this.parentType = parentType;
    }

    public Comment(String parentStoryId, String parentCommentId, String parentType) {
        this.parentStoryId = parentStoryId;
        this.parentCommentId = parentCommentId;
        this.parentType = parentType;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getParentStoryId() {
        return parentStoryId;
    }

    public void setParentStoryId(String parentStoryId) {
        this.parentStoryId = parentStoryId;
    }

    public String getParentCommentId() {
        return parentCommentId;
    }

    public void setParentCommentId(String parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public String getParentType() {
        return parentType;
    }

    public void setParentType(String parentType) {
        this.parentType = parentType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
