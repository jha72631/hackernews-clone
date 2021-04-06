package com.hacker.news.service;

import com.hacker.news.model.User;

public interface UserService {

    public User findByUsername(String username);

    void save(User user);
}
