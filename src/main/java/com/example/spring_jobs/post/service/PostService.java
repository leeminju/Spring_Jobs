package com.example.spring_jobs.post.service;

import com.example.spring_jobs.common.StatusEnum;
import com.example.spring_jobs.common.exception.CustomException;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.repository.PostCustomRepository;
import com.example.spring_jobs.post.repository.PostRepository;
import com.example.spring_jobs.user.entity.User;
import com.example.spring_jobs.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final PostCustomRepository postCustomRepository;

    public void createPost(PostRequestDto postRequestDTO, User user) {
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
        Post post = postCustomRepository.findPostAndCommentAndLike(id).orElseThrow(() -> new CustomException(StatusEnum.POST_NOT_FOUND));
        return new PostResponseDto(post);
    }

    @Transactional
    public void updatePost(Long id, PostRequestDto postRequestDto, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(StatusEnum.POST_NOT_FOUND));

        if (!post.getCompany().getCompanyName().equals(user.getCompany().getCompanyName())) {
            throw new CustomException(StatusEnum.POST_NOT_MATCHED);
        }

        post.update(postRequestDto);
    }

    public void removePost(Long id, User user) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new CustomException(StatusEnum.POST_NOT_FOUND));

        if (!post.getCompany().getCompanyName().equals(user.getCompany().getCompanyName())) {
            throw new CustomException(StatusEnum.POST_NOT_MATCHED);
        }

        postRepository.delete(post);
    }
}
