package com.hacker.news.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String userName;
    private String password;
    private String userKarma;
    private Long createdAt;
    private String about;
    private String email;
    private List<String> submissions;
    private List<String> upvotedSubmissions;
    private List<String> favoriteSubmissions;

    public User(String userName, String password,String email) {
        this.userName = userName;
        this.password = password;
        this.userKarma = "1";
        this.createdAt = new Date().getTime();
        this.email = email;
        this.submissions = new ArrayList<>();
        this.upvotedSubmissions = new ArrayList<>();
        this.favoriteSubmissions = new ArrayList<>();
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
