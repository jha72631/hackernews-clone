package com.hacker.news.service;

import com.hacker.news.dto.CommentDto;
import com.hacker.news.dto.PostDto;
import com.hacker.news.model.Comment;
import com.hacker.news.model.Post;
import com.hacker.news.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;
    private CommentService commentService;
    private UserService userService;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, CommentService commentService, UserService userService) {
        this.postRepository = postRepository;
        this.commentService = commentService;
        this.userService = userService;
    }

    @Override
    public void createPost(Post post) {
        post.setCreatedAt(new Date().getTime());
        post.setAuthor(userService.currentUser().getUsername());
        post.setScore(1);
        post.setChildCount(0);
        Post post1 = postRepository.savePost(post);
        userService.updateUserSubmissions(post1.getAuthor(), post1.getPostId(), Boolean.TRUE);
        userService.updateKarmaByOne(userService.currentUser().getUsername());
    }

    @Override
    public List<Post> fetchListOfPosts() {
        return postRepository.getAllPost();
    }

    @Override
    public PostDto fetchPost(String postId) {
        PostDto postDto = new PostDto();
        postDto.setPost(postRepository.getPostById(postId));

        List<Comment> commentList = commentService.fetchCommentByParentTypeAndPostId("Post", postId);
        if(Objects.nonNull(commentList)) {
            for (Comment comment : commentList) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment);
                postDto.getCommentDto().add(commentDto);
                //calling recursive function to traverse through depth  of hierarchy of comments
                populateHierarchicalCommentDto(comment.getCommentId(), commentDto.getChildDto());
            }
        }

        return postDto;
    }

    @Override
    public CommentDto fetchComments(String parentCommentID) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(commentService.fetchCommentByCommentId(parentCommentID));

        List<Comment> commentList = commentService.fetchCommentByParentCommentId(parentCommentID);
        if(Objects.nonNull(commentList)) {
            for(int i=0; i<commentList.size(); i++) {
                Comment comment = commentList.get(i);
                CommentDto childrenCommentDto = new CommentDto();
                childrenCommentDto.setComment(comment);
                commentDto.getChildDto().add(childrenCommentDto);
                //calling recursive function to traverse through depth  of hierarchy of comments
                populateHierarchicalCommentDto(comment.getCommentId(), childrenCommentDto.getChildDto());
            }
        }

        return commentDto;
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.getPostById(postId);
        postRepository.deletePost(post);
    }

    @Override
    public void updatePostScoreAndUserUpvotedSubmission(String userName ,String postId, boolean isToBeAdded) {
        postRepository.updatePostScore(postId, isToBeAdded);
        userService.updateUserUpvotedSubmissions(userName, postId, isToBeAdded);
    }



    public void populateHierarchicalCommentDto(String parentCommentId, List<CommentDto> commentDtoList) {
        List<Comment> commentList = commentService.fetchCommentByParentCommentId(parentCommentId);
        if(Objects.nonNull(commentList)) {
            for (Comment comment : commentList) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment);
                commentDtoList.add(commentDto);
                //calling function recursively
                populateHierarchicalCommentDto(comment.getCommentId(), commentDto.getChildDto());
            }
        }
    }

    public Page<Post> getAllPostPaginated(int pageNo, int pageSize, String sortField, String sortDirection,String postType) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.getAllPostPaginated(postType,pageable);
    }

}
