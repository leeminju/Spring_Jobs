package com.example.spring_jobs.post.controller;

import com.example.spring_jobs.auth.security.UserDetailsImpl;
import com.example.spring_jobs.post.dto.PostRequestDto;
import com.example.spring_jobs.post.dto.PostResponseDto;
import com.example.spring_jobs.post.entity.Post;
import com.example.spring_jobs.post.service.PostService;
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
    public String createPost(@ModelAttribute PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.createPost(requestDto, userDetails.getUser());
        return "redirect:/";
    }

    @GetMapping("/posts/{postId}/edit")
    public String updatePostForm(@PathVariable Long postId, Model model) {
        PostResponseDto post = postService.getPost(postId);
        model.addAttribute("post", post);
        return "post/postUpdateForm";
    }

    @PostMapping("/posts/{postId}/edit")
    public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println("=====================================");
        System.out.println(requestDto.getTitle());
        System.out.println(requestDto.getContents());
        System.out.println(requestDto.getCareer());
        System.out.println("=====================================");

        postService.updatePost(postId, requestDto, userDetails.getUser());
        return "redirect:/post-page?{postId}";
    }

    @PostMapping("/posts/{postId}/delete")
    private String deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.removePost(postId, userDetails.getUser());
        return "redirect:/";
    }
}