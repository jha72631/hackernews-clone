package com.hacker.news.service;

import com.hacker.news.model.User;
import com.hacker.news.repositories.UserRepository;
import com.hacker.news.security.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void createUser(User user) {

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
        return null;
    }

    @Override
    public void saveUserSubmissions(String postId) {

    }

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findOneByUserName(username);
    }

    @Override
    public void save(User user) {
        user.setUserPassword(SecurityConfiguration.passwordEncoder().encode(user.getUserPassword()));
        userRepository.saveUser(user);
    }
}
