package org.microservice.postservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.postservice.dto.UploadMediaRequest;
import org.microservice.postservice.entity.Post;
import org.microservice.postservice.exception.RecordNotFoundException;
import org.microservice.postservice.repository.PostRepository;
import org.microservice.postservice.service.MediaService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final PostRepository postRepository;

    @Override
    public String uploadPostMedia(Long postId, UploadMediaRequest uploadMediaRequest) {
        log.info("upload this media: {}, for post with id: {}", postId, uploadMediaRequest);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException("This post with id: " + postId + " Doesn't Exist!"));
        post.setMediaUrl(uploadMediaRequest.getMediaUrl());
        log.info("Media: {} uploaded successfully,  for this post with id {}", uploadMediaRequest, postId);
        return "Media Uploaded Successfully";
    }

    @Override
    public String deletePostMedia(Long postId) {
        log.info("Deleting media for Post with id: {}", postId);
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new RecordNotFoundException("This post with id: " + postId + " Doesn't Exist!"));
        post.setMediaUrl(null);
        postRepository.save(post);
        return "Media Deleted Successfully";
    }
}
