package com.example.spring_jobs.post;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;


    // 채용 공고 만드는 메서드
    public PostResponseDTO createPost(PostRequestDTO postRequestDTO) {
        Post post = new Post(postRequestDTO);

        postRepository.save(post);

        return new PostResponseDTO(post);
    }

    // 채용 공고 리스트로 뽑아오는 메서드
    public List<List<PostResponseDTO>> getPostList() {
        List<List<PostResponseDTO>> getPostList = new ArrayList<>();

        List<Post> postList = postRepository.findAll();

        postList.stream().forEach(post -> {
            var postDto = new PostResponseDTO(post);
            getPostList.add(new ArrayList<>(List.of(postDto)));
        });

//        for (Post post : postList) {
//            getPostList.add(new ArrayList<>(List.of(new PostResponseDTO(post))));
//        }

        return getPostList;
    }
}
