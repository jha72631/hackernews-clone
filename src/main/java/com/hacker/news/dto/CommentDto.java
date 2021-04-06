package com.hacker.news.dto;

import com.hacker.news.model.Comment;

import java.util.ArrayList;
import java.util.List;

public class CommentDto {
    private Comment comment;
    private List<CommentDto> commentDtoList = new ArrayList<>();

    public CommentDto() {
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<CommentDto> getCommentDtoList() {
        return commentDtoList;
    }

    public void setCommentDto(CommentDto commentDto) {
        this.commentDtoList.add(commentDto);
    }
}
