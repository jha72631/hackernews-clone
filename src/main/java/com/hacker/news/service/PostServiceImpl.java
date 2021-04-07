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
        post.setAuthor(userService.currentUser().getUsername());
        post.setScore(1);
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
    public CommentDto fetchComments(String parentCommentID) {
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(commentService.fetchCommentByCommentId(parentCommentID));

        List<Comment> commentList = commentService.fetchCommentByParentCommentId(parentCommentID);
        if(Objects.nonNull(commentList)) {
            for(int i=0; i<commentList.size(); i++) {
                Comment comment = commentList.get(i);
                CommentDto childrenCommentDto = new CommentDto();
                childrenCommentDto.setComment(comment);
                commentDto.getCommentDtoList().add(childrenCommentDto);
                //calling recursive function to traverse through depth  of hierarchy of comments
                populateHierarchicalCommentDto(comment.getCommentId(), childrenCommentDto.getCommentDtoList());
            }
        }

        return commentDto;
    }

    @Override
    public void deletePost(String postId) {
        Post post = postRepository.getPostById(postId);
        postRepository.deletePost(post);
    }

    public void populateHierarchicalCommentDto(String parentCommentId, List<CommentDto> commentDtoList) {
        List<Comment> commentList = commentService.fetchCommentByParentCommentId(parentCommentId);
        if(Objects.nonNull(commentList)) {
            for (Comment comment : commentList) {
                CommentDto commentDto = new CommentDto();
                commentDto.setComment(comment);
                commentDtoList.add(commentDto);
                //calling function recursively
                populateHierarchicalCommentDto(comment.getCommentId(), commentDto.getCommentDtoList());
            }
        }
    }

    public Page<Post> getAllPostPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return postRepository.getAllPostPaginated(pageable);
    }

}
