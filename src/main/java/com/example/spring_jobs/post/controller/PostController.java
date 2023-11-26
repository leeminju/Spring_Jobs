package com.example.spring_jobs.post.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.service.PostService;
import com.example.spring_jobs.user.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @Secured(UserRoleEnum.Authority.COMPANY)
    @PostMapping("/post") // 채용공고 등록
    public ResponseEntity<CustomResponseEntity> createPost(@RequestBody PostRequestDto postRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(postRequestDTO, userDetails.getUser());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_ADD_POST);
    }

    @GetMapping("/posts") // 채용공고 전체 조회
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @Secured(UserRoleEnum.Authority.COMPANY)
    @GetMapping("/myposts") // 내가 작성한 채용공고
    public List<PostResponseDto> getMyPosts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.getMyPosts(userDetails.getUser());
    }

    @GetMapping("/posts/{id}")//비회원도 조회 가능하게 구현했기 때문에 경로가 다르다!
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @Secured(UserRoleEnum.Authority.COMPANY)
    @PatchMapping("/post/{id}") // 채용공고 수정
    public ResponseEntity<CustomResponseEntity> updatePost(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.updatePost(id, postRequestDto, userDetails.getUser());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_UPDATE_POST);
    }

    @Secured(UserRoleEnum.Authority.COMPANY)
    @DeleteMapping("/post/{id}") // 채용공고 삭제
    public ResponseEntity<CustomResponseEntity> deletePost(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.removePost(id, userDetails.getUser());
        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_DELETE_POST);
    }

}
