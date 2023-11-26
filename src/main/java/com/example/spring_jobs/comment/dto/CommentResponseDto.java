package com.example.spring_jobs.comment.dto;

import com.example.spring_jobs.comment.entity.Comment;
import java.util.List;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class CommentResponseDto {
    private final List<Comment> commentList;
    public CommentResponseDto(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public List<String> getContentsList() {
        return commentList.stream()
                .map(Comment::getContent)
                .toList();
    }
}