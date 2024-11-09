package org.microservice.postservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;
import org.microservice.postservice.entity.Post;
import org.microservice.postservice.exception.RecordNotFoundException;
import org.microservice.postservice.exception.UnauthenticatedException;
import org.microservice.postservice.mappers.PostMapper;
import org.microservice.postservice.proxy.UserProxy;
import org.microservice.postservice.repository.PostRepository;
import org.microservice.postservice.service.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public PostResponseDTO getPostById(Long id) {
        log.info("Getting post By Id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This post with id: " + id + " Doesn't Exist!"));
        log.info("Post with id: {}, details: {}", id, post);
        return postMapper.toDTO(post);
    }

    @Override
    public PostResponseDTO updatePost(Long id, PostRequestDTO postRequestDTO) {
        log.info("You want to update post by id {}, to : {}", id, postRequestDTO);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This post with id: " + id + " Doesn't Exist!"));
        log.info("Post with id: {}, before update: {}", id, post);

        post.setContent(postRequestDTO.getContent());
        post.setMediaUrl(postRequestDTO.getMediaUrl());

        postRepository.save(post);
        log.info("post with id {}, updated Successfully!, Details: {}", id, post);
        return postMapper.toDTO(post);
    }

    @Override
    public void deletePost(Long id) {
        log.info("Deleting post by id: {}", id);
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This post with id: " + id + " Doesn't Exist!"));

        postRepository.delete(post);
        log.info("Post with id: {}, deleted successfully!", id);
        return ;
    }

    @Override
    public List<PostResponseDTO> getAllUserPosts(Long userId) {
        log.info("Getting All user posts by id: {}", userId);
        List<Post> userPosts = postRepository.findByUserId(userId);
        log.trace("All user with id: {} posts : {}", userId, userPosts);
        return postMapper.toDTOs(userPosts);
    }

    @Override
    public boolean checkPostIsExist(Long id) {
        return postRepository.existsPostsByPostId(id);
    }
}
