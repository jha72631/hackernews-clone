package com.hacker.news.service;

import com.hacker.news.model.User;

public interface UserService {
    void createUser(User user);
    User updateUser(User user);
    User getUserByUserId(String userId);
    User getUserByUserName(String userName);
    void saveUserSubmissions(String postId);
}
