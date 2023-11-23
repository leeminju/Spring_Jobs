package com.example.spring_jobs.post.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostListResponseDTO {

    //private PostUserCheckDTO postUserCheckDTO;
    private List<PostResponseDTO> postList;

    public PostListResponseDTO(List<PostResponseDTO> postList) {
        //this.postUserCheckDTO = postUserCheckDTO;
        this.postList = postList;
    }

}
