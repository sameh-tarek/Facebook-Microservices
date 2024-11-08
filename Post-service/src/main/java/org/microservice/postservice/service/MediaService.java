package org.microservice.postservice.service;

import org.microservice.postservice.dto.UploadMediaRequest;

public interface MediaService {
    String uploadPostMedia(Long postId, UploadMediaRequest uploadMediaRequest);

    String deletePostMedia(Long postId);
}
