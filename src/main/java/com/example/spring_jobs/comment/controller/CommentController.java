package com.example.spring_jobs.comment.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.comment.dto.CommentRequestDto;
import com.example.spring_jobs.comment.service.CommentService;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.user.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @Secured(UserRoleEnum.Authority.USER)
    public ResponseEntity<CustomResponseEntity> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody CommentRequestDto commentRequestDto) {
        Long userId = userDetails.getUser().getId();
        commentService.createComment(userId, postId, commentRequestDto.getContent());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_COMMENT_CREATE);
    }

    @PatchMapping
    @Secured(UserRoleEnum.Authority.USER)
    public ResponseEntity<CustomResponseEntity> updateComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody  CommentRequestDto commentRequestDto) {
        Long userId = userDetails.getUser().getId();
        commentService.updateComment(userId, postId, commentRequestDto.getContent());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_COMMENT_UPDATE);
    }

    @DeleteMapping
    @Secured(UserRoleEnum.Authority.USER)
    public ResponseEntity<CustomResponseEntity> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        Long userId = userDetails.getUser().getId();
        commentService.deleteComment(userId, postId);
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_COMMENT_DELETE);
    }
}
