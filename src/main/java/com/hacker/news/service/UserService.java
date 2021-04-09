package com.hacker.news.service;

import com.hacker.news.model.User;
import com.hacker.news.security.UserPrincipal;

import java.util.List;

public interface UserService {
    void createUser(User user);
    User updateUser(User user);
    User getUserByUserId(String userId);
    User getUserByUserName(String userName);
    List<User> getAllUsers();
    User updateUserEmail(String userName, String email);
    User updateUserAbout(String userName, String about);
    User updateUserSubmissions(String userName, String postId, boolean isToBeAdded);
    User updateUserUpvotedSubmissions(String userName, String postId, boolean isToBeAdded);
    User updateUserUpvotedCommentSubmissions(String userName, String commentId, boolean isToBeAdded);
    User updateUserCommentSubmissions(String userName, String commentId, boolean isToBeAdded);
    User updateUserFavoriteSubmissions(String userName, String postId, boolean isToBeAdded);
    List<String> getListOfUsersSubmission(String userName);
    List<String> getListOfUpvotedSubmission(String userName);
    List<String> getListOfFavoriteSubmission(String userName);
    boolean isAuthorised();
    boolean isLoggedIn();
    UserPrincipal currentUser();
}
