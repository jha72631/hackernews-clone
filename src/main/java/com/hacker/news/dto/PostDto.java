package com.hacker.news.dto;

import com.hacker.news.model.Post;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class PostDto {
    private Post post;
    private List<CommentDto> commentDtoList = new ArrayList<>();
    boolean postIsVoted;
    public PostDto() {
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public List<CommentDto> getCommentDto() {
        return commentDtoList;
    }

    public void setCommentDto(CommentDto commentDto) {
        this.commentDtoList.add(commentDto);
    }
}
