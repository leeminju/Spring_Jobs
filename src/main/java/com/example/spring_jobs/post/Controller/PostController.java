package com.example.spring_jobs.post.Controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post") // 채용공고 등록
    public ResponseEntity<CustomResponseEntity> createPost(@RequestBody PostRequestDto postRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        postService.createPost(postRequestDTO, userDetails.getUser());

        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_ADD_POST);
    }

    @PatchMapping("/post") // 채용공고 수정
    public ResponseEntity<CustomResponseEntity> updatePost() {
        return null;
    }

    @DeleteMapping("/post") // 채용공고 삭제
    public ResponseEntity<CustomResponseEntity> deletePost() {
        return null;
    }

    @GetMapping("/posts") // 채용공고 전체 조회
    public List<PostResponseDto> getPosts() {
        List<PostResponseDto> posts=postService.getPosts();

        return posts;
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> getPost() {
        return null;
    }

}
