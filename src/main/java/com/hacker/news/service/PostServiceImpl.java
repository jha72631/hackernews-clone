package com.hacker.news.service;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;
import com.hacker.news.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Override
    public void createPost(Post post) {
        post.setCreatedAt(new Date().getTime());
        postRepository.savePost(post);
    }

    @Override
    public List<Post> fetchListOfPosts() {
        return postRepository.getAllPost();
    }

    @Override
    public PostDto fetchPost(String postId) {
        PostDto postDto = new PostDto();
        postDto.setPost(postRepository.getPostById(postId));

        List<Comment> commentList = commentService.fetchCommentByParentTypeAndPostId("post", postId);
        if(Objects.nonNull(commentList)) {
            for (int i=0;i<commentList.size();i++) {
                Comment comment = commentList.get(i);
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment);
                postDto.getCommentDto().add(commentDto);
                //calling recursive function to traverse through depth  of hierarchy of comments
                populateHierarchicalCommentDto(comment.getCommentId(), commentDto.getCommentDtoList());
            }
        }

        return postDto;
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.getPostById(postId);
        postRepository.deletePost(post);
    }

    public void populateHierarchicalCommentDto(String parentCommentId, List<CommentDto> commentDtoList) {
        List<Comment> commentList = commentService.fetchCommentByParentCommentId(parentCommentId);
        if(Objects.nonNull(commentList)) {
            for (int i=0;i<commentList.size();i++) {
                Comment comment = commentList.get(i);
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment);
                commentDtoList.add(commentDto);
                //calling function recursively
                populateHierarchicalCommentDto(comment.getCommentId(), commentDto.getCommentDtoList());
            }
        }
        return;
    }




}
