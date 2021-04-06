package com.hacker.news.repositories;

import com.hacker.news.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public class PostRepositoryImpl implements PostRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Post getPostById(String postId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("postId").is(postId));
        return mongoTemplate.findOne(query, Post.class);
    }

    @Override
    public List<Post> getTopPost() {
        return null;
    }

    @Override
    public List<Post> getNewPost() {
        return null;
    }

    @Override
    public Post savePost(Post post) {
        System.out.println("inside repo");
        mongoTemplate.save(post);
        return post;
    }

    @Override
    public List<Post> getAllPost() {
        return mongoTemplate.findAll(Post.class);
    }

    @Override
    public Page<Post> getAllPostPaginated(Pageable pageable) {
        Query query = new Query();
        long count = this.mongoTemplate.count(query, Post.class);

        query.with(pageable);
        List<Post> posts = mongoTemplate.find(query, Post.class);
        return PageableExecutionUtils.getPage(posts, pageable, () -> count);
    }

    @Override
    public List<Post> findByName(String authorName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("author").is(authorName));
        return mongoTemplate.find(query, Post.class);
    }

    @Override
    public Post updateOnePost(Post post) {
        return null;
    }

    @Override
    public void deletePost(Post post) {
        mongoTemplate.remove(post);
    }
}
