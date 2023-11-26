package com.example.spring_jobs.comment.dto;

import com.example.spring_jobs.comment.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final String comment;
    private final String nickname;
    private final Long userId;

    public CommentResponseDto(Comment comment) {
        this.comment = comment.getContent();
        this.nickname = comment.getUser().getNickname();
        this.userId = comment.getUser().getId();
    }
}