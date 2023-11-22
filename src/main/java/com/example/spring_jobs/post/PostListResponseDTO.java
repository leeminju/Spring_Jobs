package com.example.spring_jobs.post;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostListResponseDTO {

    private List<PostResponseDTO> postList;

    public PostListResponseDTO(List<PostResponseDTO> postList) {
        this.postList = postList;
    }

}
