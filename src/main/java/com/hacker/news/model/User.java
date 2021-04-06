package com.hacker.news.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Iterator;
import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String userName;
    private String userKarma;
    private Long createdAt;
    private String about;
    private String email;
    private List<String> submissions;
    private List<String> upvotedSubmissions;
    private List<String> favoriteSubmissions;

    public User() {
    }

    public User(String userKarma, String about, List<String> submissions) {
        this.userKarma = userKarma;
        this.about = about;
        this.submissions = submissions;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserKarma() {
        return userKarma;
    }

    public void setUserKarma(String userKarma) {
        this.userKarma = userKarma;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<String> submissions) {
        this.submissions = submissions;
    }

    public List<String> getUpvotedSubmissions() {
        return upvotedSubmissions;
    }

    public void setUpvotedSubmissions(List<String> upvotedSubmissions) {
        this.upvotedSubmissions = upvotedSubmissions;
    }

    public List<String> getFavoriteSubmissions() {
        return favoriteSubmissions;
    }

    public void setFavoriteSubmissions(List<String> favoriteSubmissions) {
        this.favoriteSubmissions = favoriteSubmissions;
    }


    public void addToUpvotedSubmissions(String postId) {
        this.upvotedSubmissions.add(postId);
    }

    public void removeFromUpvotedSubmissions(String postId) {
        Iterator itr = this.upvotedSubmissions.iterator();
        while(itr.hasNext()) {
            String upvotedPostId = (String) itr.next();
            if(upvotedPostId.equals(postId)) {
                itr.remove();
            }
        }
    }

    public void addToFavoriteSubmissions(String postId) {
        this.favoriteSubmissions.add(postId);
    }

    public void removeFromFavoriteSubmissions(String postId) {
        Iterator itr = this.upvotedSubmissions.iterator();
        while(itr.hasNext()) {
            String favoritePostId = (String) itr.next();
            if(favoritePostId.equals(postId)) {
                itr.remove();
            }
        }
    }

}
