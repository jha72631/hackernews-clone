package com.hacker.news.repositories;

import com.hacker.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private MongoTemplate mongoTemplate;

    @Autowired
    public UserRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public User saveUser(User user) {
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return mongoTemplate.findAll(User.class);
    }

    @Override
    public User findByUserId(String userId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userId").is(userId));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findOneByUserName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public List<User> findByName(String userName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        return mongoTemplate.find(query, User.class);
    }

    @Override
    public User updateUserEmail(String userName, String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        user.setEmail(email);
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserAbout(String userName, String about) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        user.setAbout(about);
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserSubmissions(String userName, String postId, boolean isToBeAdded) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(isToBeAdded) {
            user.getSubmissions().add(postId);
        } else {
            List<String> listOfSubmissions = user.getSubmissions();
            Iterator itr = listOfSubmissions.iterator();
            while(itr.hasNext()) {
                String submittedPostId = (String) itr.next();
                if(submittedPostId.equals(postId)) {
                    itr.remove();
                }
            }
        }
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserUpvotedSubmissions(String userName, String postId, boolean isToBeAdded) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(isToBeAdded) {
            user.getUpvotedSubmissions().add(postId);
        } else {
            List<String> listOfUpvotedSubmissions = user.getUpvotedSubmissions();
            Iterator itr = listOfUpvotedSubmissions.iterator();
            while(itr.hasNext()) {
                String upvotedPostId = (String) itr.next();
                if(upvotedPostId.equals(postId)) {
                    itr.remove();
                }
            }
        }
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserUpvotedCommentSubmissions(String userName, String commentId, boolean isToBeAdded) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(isToBeAdded) {
            user.getUpvotedCommentSubmissions().add(commentId);
        } else {
            List<String> listOfUpvotedCommentSubmissions = user.getUpvotedCommentSubmissions();
            Iterator itr = listOfUpvotedCommentSubmissions.iterator();
            while(itr.hasNext()) {
                String upvotedCommentId = (String) itr.next();
                if(upvotedCommentId.equals(commentId)) {
                    itr.remove();
                }
            }
        }
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserCommentSubmissions(String userName, String commentId, boolean isToBeAdded) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(isToBeAdded) {
            if (user.getCommentSubmissions()==null){
                user.setCommentSubmissions(new ArrayList<String>());
            }
            user.getCommentSubmissions().add(commentId);
        } else {
            List<String> listOfCommentSubmissions = user.getCommentSubmissions();
            Iterator itr = listOfCommentSubmissions.iterator();
            while(itr.hasNext()) {
                String submittedCommentId = (String) itr.next();
                if(submittedCommentId.equals(commentId)) {
                    itr.remove();
                }
            }
        }
        mongoTemplate.save(user);
        return user;
    }

    @Override
    public User updateUserFavoriteSubmissions(String userName, String postId, boolean isToBeAdded) {
        Query query = new Query();
        query.addCriteria(Criteria.where("userName").is(userName));
        User user = mongoTemplate.findOne(query, User.class);
        if(isToBeAdded) {
            user.getFavoriteSubmissions().add(postId);
        } else {
            List<String> listOfFavoriteSubmissions = user.getFavoriteSubmissions();
            Iterator itr = listOfFavoriteSubmissions.iterator();
            while(itr.hasNext()) {
                String favoritePostId = (String) itr.next();
                if(favoritePostId.equals(postId)) {
                    itr.remove();
                }
            }
        }
        mongoTemplate.save(user);
        return null;
    }


    @Override
    public void deleteUser(User user) {
        mongoTemplate.remove(user);
    }
}
