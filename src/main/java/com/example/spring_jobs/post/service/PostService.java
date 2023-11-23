package com.example.spring_jobs.post.service;

import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void createPost(PostRequestDto postRequestDTO, User user) {
        checkRole(user);
        Post post = new Post(postRequestDTO, user);

        postRepository.save(post);
    }

    public List<PostResponseDto> getPosts() {
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }

    public List<PostResponseDto> getMyPosts(User user) {
        User dbuser = userRepository.findById(user.getId()).orElseThrow(()
                -> new CustomException(StatusEnum.UsernameNotFoundException));

        return postRepository.findAllByCompany(dbuser.getCompany()).stream().map(PostResponseDto::new).toList();
    }

    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(StatusEnum.POST_NOT_FOUND));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto, User user) {
        checkRole(user);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(StatusEnum.POST_NOT_FOUND));

        if (!post.getCompany().getCompanyName().equals(user.getCompany().getCompanyName())) {
            throw new CustomException(StatusEnum.POST_NOT_MATCHED);
        }

        post.update(postRequestDto);
    }

    public void removePost(Long id, User user) {
        checkRole(user);
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(StatusEnum.POST_NOT_FOUND));

        if (!post.getCompany().getCompanyName().equals(user.getCompany().getCompanyName())) {
            throw new CustomException(StatusEnum.POST_NOT_MATCHED);
        }

        postRepository.delete(post);
    }

    void checkRole(User user) {
        if (!user.getRole().getAuthority().equals("COMPANY")) {
            throw new CustomException(StatusEnum.ROLE_NOT_COMPANY);
        }
    }
}
