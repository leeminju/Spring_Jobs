package com.example.spring_jobs.comment.dto;

import com.example.spring_jobs.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final Long id;
    private final String comment;
    private final String nickname;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getContent();
        this.nickname = comment.getUser().getNickname();
    }
}