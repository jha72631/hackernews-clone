package com.hacker.news.repositories;

import com.hacker.news.model.Post;
import com.hacker.news.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

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
    public User updateOneUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

    }
}
