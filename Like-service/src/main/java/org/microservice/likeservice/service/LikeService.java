package org.microservice.likeservice.service;

import org.microservice.likeservice.dto.LikeDTO;

public interface LikeService {
    String doLikeOnPost(Long postId, LikeDTO likeDTO);
}
