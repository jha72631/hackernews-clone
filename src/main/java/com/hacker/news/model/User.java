package com.hacker.news.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

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
    private String role;
    private List<String> submissions;
    private List<String> upvotedSubmissions;
    private List<String> commentSubmissions;
    private List<String> upvotedCommentSubmissions;
    private List<String> favoriteSubmissions;
    private String permissions = "";

    public User(String userName, String password,String email) {
        this.userName = userName;
        this.password = password;
        this.userKarma = "1";
        this.createdAt = new Date().getTime();
        this.email = email;
        this.commentSubmissions = new ArrayList<>();
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

    public List<String> getRolesList() {
        if (this.role.length() > 0) {
            return Arrays.asList(this.role.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getPermissionsList() {
        if (this.permissions.length() > 0) {
            return Arrays.asList(this.permissions.split(","));
        }
        return new ArrayList<>();
    }
}
