package com.hacker.news.service;

import com.hacker.news.model.User;
import com.hacker.news.repositories.UserRepository;
import com.hacker.news.security.SecurityConfiguration;
import com.hacker.news.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public User updateUserEmail(String userName, String email) {
        return userRepository.updateUserEmail(userName, email);
    }

    @Override
    public User updateUserAbout(String userName, String about) {
        return userRepository.updateUserAbout(userName, about);
    }

    @Override
    public User updateUserSubmissions(String userName, String postId, boolean isToBeAdded) {
        return userRepository.updateUserSubmissions(userName, postId, isToBeAdded);
    }

    @Override
    public User updateUserUpvotedSubmissions(String userName, String postId, boolean isToBeAdded) {
        return userRepository.updateUserUpvotedSubmissions(userName, postId, isToBeAdded);
    }

    @Override
    public User updateUserUpvotedCommentSubmissions(String userName, String commentId, boolean isToBeAdded) {
        return userRepository.updateUserUpvotedCommentSubmissions(userName, commentId, isToBeAdded);
    }

    @Override
    public User updateUserCommentSubmissions(String userName, String commentId, boolean isToBeAdded) {
        return userRepository.updateUserCommentSubmissions(userName, commentId, isToBeAdded);
    }

    @Override
    public User updateUserFavoriteSubmissions(String userName, String postId, boolean isToBeAdded) {
        return userRepository.updateUserUpvotedSubmissions(userName, postId, isToBeAdded);
    }

    @Override
    public List<String> getListOfUsersSubmission(String userName) {
        User user = userRepository.findOneByUserName(userName);
        return user.getSubmissions();
    }

    @Override
    public List<String> getListOfUpvotedSubmission(String userName) {
        User user = userRepository.findOneByUserName(userName);
        return user.getUpvotedSubmissions();
    }

    @Override
    public List<String> getListOfFavoriteSubmission(String userName) {
        User user = userRepository.findOneByUserName(userName);
        return user.getFavoriteSubmissions();
    }

    @Override
    public boolean isAuthorised() {
        return false;
    }

    @Override
    public boolean isLoggedIn() {
        return (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof UserDetails);
    }

    @Override
    public UserPrincipal currentUser() {
        return  (UserPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
