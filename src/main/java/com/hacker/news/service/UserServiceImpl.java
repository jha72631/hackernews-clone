package com.hacker.news.service;

import com.hacker.news.model.User;
import com.hacker.news.repositories.UserRepository;
import com.hacker.news.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void createUser(User user) {
        user.setPassword(SecurityConfiguration.passwordEncoder().encode(user.getPassword()));
        System.out.println("insideService");
        userRepository.saveUser(user);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public User getUserByUserId(String userId) {
        return null;
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findOneByUserName(userName);
    }

    @Override
    public void saveUserSubmissions(String postId) {

    }
}
