package com.example.spring_jobs.post.service;

import com.example.spring_jobs.common.CustomResponseEntity;
import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.company.entity.Company;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    // 채용 공고 만드는 메서드
    public void createPost(PostRequestDto postRequestDTO, User user) {

        if (!user.getRole().toString().equals("COMPANY")) {
            throw new CustomException(StatusEnum.ROLE_NOT_COMPANY);
        }

        Post post = new Post(postRequestDTO, user);

        postRepository.save(post);
    }

    // 채용 공고 리스트로 뽑아오는 메서드
    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getMyPosts(User user) {
        User dbuser = userRepository.findById(user.getId()).orElseThrow(()
                -> new CustomException(StatusEnum.UsernameNotFoundException));

        return postRepository.findAllByCompany(dbuser.getCompany()).stream().map(PostResponseDto::new).toList();
    }

//    public PostResponseDto getPost(Long id) {
//        Post post = postRepository.findById(id).orElseThrow(
//                () -> new CustomException(StatusEnum.POST_NOT_FOUND));
//        return new PostResponseDto(post);
//    }
}
