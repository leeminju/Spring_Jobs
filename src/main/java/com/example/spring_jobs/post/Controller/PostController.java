package com.example.spring_jobs.post.Controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.post.dto.PostListResponseDTO;
import com.example.spring_jobs.post.dto.PostRequestDTO;
import com.example.spring_jobs.post.dto.PostResponseDTO;
import com.example.spring_jobs.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post") // 채용공고 등록
    public ResponseEntity<CustomResponseEntity> createPost(@RequestBody PostRequestDTO postRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {

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
    public ResponseEntity<List<PostListResponseDTO>> getPostList() {
        List<PostListResponseDTO> response = new ArrayList<>();
        List<List<PostResponseDTO>> dtoMap = postService.getPostMapList();
        //Map<PostUserCheckDTO, List<PostResponseDTO>> dtoMap = postService.getPostMapList();

        //dtoMap.forEach((key, value) -> response.add(new PostListResponseDTO(key, value)));
        for (List<PostResponseDTO> postResponseDTOS : dtoMap) {
            response.add(new PostListResponseDTO(postResponseDTOS));
        }

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostListResponseDTO> getPost() {
        return null;
    }

}
