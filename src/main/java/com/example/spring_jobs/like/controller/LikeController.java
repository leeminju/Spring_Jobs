package com.example.spring_jobs.like.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.like.dto.request.LikeRequestDto;
import com.example.spring_jobs.like.service.LikeService;
import com.example.spring_jobs.user.UserRoleEnum;
import com.example.spring_jobs.user.UserRoleEnum.Authority;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LikeController {

    private final LikeService likeService;

    @Secured(Authority.USER)
    @PostMapping("/like/{id}")
    public ResponseEntity<CustomResponseEntity> like(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        if(likeService.like(id, userDetails.getUser().getId()) != null){
            return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_LIKE);
        }else{
            return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_LIKE_DELETE);
        }
    }

    @GetMapping("/getMyLike/{id}")
    public boolean getMyLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails){
        return likeService.getMyLike(id, userDetails.getUser().getId());
    }
}