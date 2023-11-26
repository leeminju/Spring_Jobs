package com.example.spring_jobs.post.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.service.PostService;
import com.example.spring_jobs.user.dto.UserResponseDto;
import com.example.spring_jobs.user.dto.UserUpdateDto;
import com.example.spring_jobs.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostPageController {

    private final PostService postService;

    @GetMapping("/post")
    public String createPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "post/postCreateForm";
    }

    @PostMapping("/post")
    public String createPost(@ModelAttribute PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        postService.createPost(requestDto, userDetails.getUser());
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}/edit")
    public String updatePostForm(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return "post/postUpdateForm";
    }

    @PostMapping("/posts/{id}/edit")
    public String updatePost(@Valid @ModelAttribute UserUpdateDto userUpdateDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return "redirect:/";
    }

    @PostMapping("/posts/{id}/delete")
    private void deletePost(UserDetailsImpl userDetails) {

    }
}
