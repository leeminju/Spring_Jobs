package com.example.spring_jobs.like.service;

import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.like.dto.request.LikeRequestDto;
import com.example.spring_jobs.like.entity.Like;
import com.example.spring_jobs.like.repository.LikeRepository;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public Long like(LikeRequestDto likeRequestDto){

        Post post = postRepository.findById(likeRequestDto.getPostId()).orElseThrow(() ->new CustomException(StatusEnum.POST_NOT_FOUND));
        User user = userRepository.findById(likeRequestDto.getUserId()).orElseThrow(() -> new CustomException(StatusEnum.USER_NOT_FOUND));

        Optional<Like> like = likeRepository.findByUserAndPost(user, post);

        if(like.isPresent()) {
            likeRepository.deleteById(like.get().getId());
        }else{
            return likeRepository.save(Like.builder().post(post).user(user).build()).getId();
        }
        return null;
    }
}