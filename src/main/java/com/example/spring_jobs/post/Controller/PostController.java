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

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping // 채용공고 등록
    public ResponseEntity<CustomResponseEntity> getPost(@RequestBody PostRequestDTO postRequestDTO, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        postService.createPost(postRequestDTO, userDetails.getUser());

        return CustomResponseEntity.toResponseEntity(StatusEnum.SUCCESS_ADD_POST);
    }

    @GetMapping // 채용공고 전체 조회
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

}
