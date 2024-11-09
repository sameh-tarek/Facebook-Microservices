package org.microservice.postservice.service;

import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;

import java.util.List;

public interface PostService {
    PostResponseDTO createPost(PostRequestDTO post);

    PostResponseDTO getPostById(Long id);

    PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO);

    void deletePost(Long id);

    List<PostResponseDTO> getAllUserPosts(Long userId);

    boolean checkPostIsExist(Long id);
}
