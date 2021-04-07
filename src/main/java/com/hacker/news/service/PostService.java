package com.hacker.news.service;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Post;

import java.util.List;

public interface PostService {
    void createPost(Post post);
    List<Post> fetchListOfPosts();
    PostDto fetchPost(String postId);
    CommentDto fetchComments(String parentCommentID);
    void deletePost(String postId);
}