package com.example.spring_jobs.post.service;

import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.post.dto.PostRequestDTO;
import com.example.spring_jobs.post.dto.PostResponseDTO;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    // 채용 공고 만드는 메서드
    public void createPost(PostRequestDTO postRequestDTO, User user) {

        if(!user.getRole().toString().equals("COMPANY")) {
            throw new CustomException(StatusEnum.ROLE_NOT_MATCHED);
        }
        Post post = new Post(postRequestDTO);

        postRepository.save(post);

    }

    // 채용 공고 리스트로 뽑아오는 메서드
    public List<List<PostResponseDTO>> getPostMapList() {
        //Map<PostUserCheckDTO, List<PostResponseDTO>> getPostMap = new HashMap<>();
        List<List<PostResponseDTO>> getPostMap = new ArrayList<>();

        List<Post> postList = postRepository.findAll();
//        for (Post post : postList) {
//            getPostMap.put(new PostUserCheckDTO(post.getCompany().getUser()), new ArrayList<>(List.of(new PostResponseDTO(post))));
//        }
        postList.stream().forEach(post -> {
            var postDto = new PostResponseDTO(post);
            getPostMap.add(new ArrayList<>(List.of(postDto)));
        });


        return getPostMap;
    }
}
