package com.hacker.news.service;

import com.hacker.news.model.User;
import com.hacker.news.security.UserPrincipal;

import java.util.List;

public interface UserService {
    void createUser(User user);
    User updateUser(User user);
    User getUserByUserId(String userId);
    User getUserByUserName(String userName);
    void saveUserSubmissions(String postId);
    List<User> getAllUsers();
    boolean isAuthorised();
    boolean isLoggedIn();
    UserPrincipal currentUser();
}
