package com.hacker.news.dto;

import com.hacker.news.model.Comment;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class CommentDto {
    private Comment comment;
    private List<CommentDto> childDto = new ArrayList<>();

    public CommentDto() {
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public List<CommentDto> getChildDto() {
        return childDto;
    }

    public void setCommentDto(CommentDto commentDto) {
        this.childDto.add(commentDto);
    }
}
