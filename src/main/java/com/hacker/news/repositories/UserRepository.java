package com.hacker.news.repositories;

import com.hacker.news.model.User;

import java.util.List;

public interface UserRepository {
    User saveUser(User user);
    List<User> getAllUsers();
    User findByUserId(String userId);
    User findOneByUserName(String userName);
    List<User> findByName(String name);
    User updateOneUser(User user);
    void deleteUser(User user);
}
