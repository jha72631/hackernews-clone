package com.hacker.news.repositories;

import com.hacker.news.model.Post;

import java.util.List;

public interface PostRepository {
    Post getPostById(String postId);
    List<Post> getTopPost();
    List<Post> getNewPost();
    Post savePost(Post post);
    List<Post> getAllPost();
    List<Post> getAllPostPaginated(int pageNumber, int pageSize);
    List<Post> findByName(String authorName);
    Post updateOnePost(Post post);
    void deletePost(Post post);
}
