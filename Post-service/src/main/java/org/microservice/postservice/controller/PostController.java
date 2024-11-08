package org.microservice.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;
import org.microservice.postservice.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public PostResponseDTO createPost (@RequestBody PostRequestDTO post) {
        return postService.createPost(post);
    }

    @GetMapping("/{id}")
    public PostResponseDTO getPostById (@PathVariable Long id) {
        return postService.getPostById(id);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @PutMapping("/{id}")
    public PostResponseDTO updatePost (@RequestBody PostRequestDTO postRequestDTO,
                                       @PathVariable Long id) {
        return postService.updatePost(id, postRequestDTO);
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public void deletePost (@PathVariable Long id) {
        postService.deletePost(id);
    }

    @GetMapping("/user/{userId}")
    public List<PostResponseDTO> getAllUserPosts (@PathVariable Long userId) {
        return postService.getAllUserPosts(userId);
    }
}
