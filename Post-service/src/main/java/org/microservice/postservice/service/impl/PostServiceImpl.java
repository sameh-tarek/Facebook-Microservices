package org.microservice.postservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;
import org.microservice.postservice.entity.Post;
import org.microservice.postservice.exception.UnauthenticatedException;
import org.microservice.postservice.mappers.PostMapper;
import org.microservice.postservice.proxy.UserProxy;
import org.microservice.postservice.repository.PostRepository;
import org.microservice.postservice.service.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserProxy userProxy;
    @Override
    public PostResponseDTO createPost(PostRequestDTO post) {
        Long userId = post.getUserId();
        //TODO: check user is Authenticated and Authorized to add the post
        if(userProxy.checkUserIsAuth(userId).getStatusCode().is4xxClientError()) {
            log.error("user with id: {}, Un Auth to Create this Post: {}", userId, post );
            throw new UnauthenticatedException("user with id "+ userId + " un Auth to create this post");
        }

        log.info("user with id: {}, want to add this post: {}", userId, post);
        Post newPost = postMapper.toEntity(post);
        newPost.setCreated_at(LocalDateTime.now());
        postRepository.save(newPost);
        log.info("user with id: {}, add this post successfully: {}", userId, newPost);
        return postMapper.toDTO(newPost);
    }
}
