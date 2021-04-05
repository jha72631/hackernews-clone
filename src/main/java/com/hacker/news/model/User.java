package com.hacker.news.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String userKarma;
    private String about;
    private List<String> submissions;

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

    public String getUserKarma() {
        return userKarma;
    }

    public void setUserKarma(String userKarma) {
        this.userKarma = userKarma;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public List<String> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<String> submissions) {
        this.submissions = submissions;
    }
}
