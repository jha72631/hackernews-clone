package com.hacker.news.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Iterator;
import java.util.List;

@Getter
@Setter
@Document(collection = "user")
public class User {

    @Id
    private String userId;
    private String userName;
    private String userKarma;
    private String userPassword;
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
