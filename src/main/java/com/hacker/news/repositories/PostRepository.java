package com.hacker.news.repositories;

import com.hacker.news.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository {
    Post getPostById(String postId);
    List<Post> getTopPost();
    List<Post> getNewPost();
    Post savePost(Post post);
    List<Post> getAllPost();
    Page<Post> getAllPostPaginated(Pageable pageable);
    List<Post> findByName(String authorName);
    Post updateOnePost(Post post);
    void deletePost(Post post);
}
