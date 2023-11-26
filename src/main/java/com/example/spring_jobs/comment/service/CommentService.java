package com.example.spring_jobs.comment.service;

import com.example.spring_jobs.comment.entity.Comment;
import com.example.spring_jobs.comment.repository.CommentRepository;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public void createComment(Long userId, Long postId, String content) {
        User findUser = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(StatusEnum.UsernameNotFoundException));

        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(StatusEnum.POST_NOT_FOUND));

        Comment comment = new Comment(findUser, findPost, content);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long userId, Long postId, String updateContent) {
        Comment findComment = commentRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new CustomException(StatusEnum.COMMENT_NOT_FOUND));

        findComment.updateContent(updateContent);
    }

    @Transactional
    public void deleteComment(Long userId, Long postId) {
        Comment findComment = commentRepository.findByUserIdAndPostId(userId, postId)
                .orElseThrow(() -> new CustomException(StatusEnum.COMMENT_NOT_FOUND));

        commentRepository.delete(findComment);
    }

}
