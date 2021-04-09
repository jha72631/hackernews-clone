package com.hacker.news.repositories;

import com.hacker.news.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User saveUser(User user);
    List<User> getAllUsers();
    User findByUserId(String userId);
    User findOneByUserName(String userName);
    List<User> findByName(String name);
    User updateUserEmail(String userName, String email);
    User updateUserAbout(String userName, String about);
    User updateUserSubmissions(String userName, String postId, boolean isToBeAdded);
    User updateUserUpvotedSubmissions(String userName, String postId, boolean isToBeAdded);
    User updateUserUpvotedCommentSubmissions(String userName, String commentId, boolean isToBeAdded);
    User updateUserCommentSubmissions(String userName, String commentId, boolean isToBeAdded);
    User updateUserFavoriteSubmissions(String userName, String postId, boolean isToBeAdded);
    void deleteUser(User user);
    void updateUserKarma(String username);
}
