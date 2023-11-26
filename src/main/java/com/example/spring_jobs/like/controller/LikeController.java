package com.example.spring_jobs.like.controller;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.like.dto.request.LikeRequestDto;
import com.example.spring_jobs.like.service.LikeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    @PostMapping("/like")
    public ResponseEntity<CustomResponseEntity> like(@Valid @RequestBody LikeRequestDto likeRequestDto){
        if(likeService.like(likeRequestDto) != null){
            return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_LIKE);
        }else{
            return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_LIKE_DELETE);
        }
    }
}