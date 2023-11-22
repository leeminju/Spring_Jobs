package com.example.spring_jobs.post;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/posts")
@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping // 채용공고 상세 조회
    public ResponseEntity<PostCommonResponseDto> getPost(@RequestBody PostRequestDTO postRequestDTO) {

        PostResponseDTO dto = postService.createPost(postRequestDTO);

        return ResponseEntity.ok().body(new PostCommonResponseDto(dto.getId(), "공고가 등록되었습니다."));

    }

    @GetMapping // 채용공고 전체 조회
    public ResponseEntity<List<PostListResponseDTO>> getPostList() {
        List<PostListResponseDTO> response = new ArrayList<>();
        List<List<PostResponseDTO>> dto = postService.getPostList();

        for (List<PostResponseDTO> postResponseDTOS : dto) {
            response.add(new PostListResponseDTO(postResponseDTOS));
        }

        return ResponseEntity.ok().body(response);
    }

}
