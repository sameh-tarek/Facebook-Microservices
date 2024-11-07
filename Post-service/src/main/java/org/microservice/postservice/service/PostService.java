package org.microservice.postservice.service;

import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;

public interface PostService {
    PostResponseDTO createPost(PostRequestDTO post);
}
