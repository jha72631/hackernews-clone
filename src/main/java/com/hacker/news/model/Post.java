package com.hacker.news.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "post")
public class Post {

    @Id
    private String postId;
    private String postType;
    private String author;
    private Long createdAt;
    private String title;
    private String url;
    private String textContent;
    private Integer score;
    private Integer childCount;

    public Post() {
    }

    public Post(String postType, String author, String title, String url, String textContent) {
        this.postType = postType;
        this.author = author;
        this.title = title;
        this.url = url;
        this.textContent = textContent;
        this.score = 1;
        this.childCount=0;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
        System.out.println("createdAt"+this.createdAt);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}

