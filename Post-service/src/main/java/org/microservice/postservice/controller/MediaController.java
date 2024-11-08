package org.microservice.postservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.postservice.dto.UploadMediaRequest;
import org.microservice.postservice.service.MediaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping("{postId}/media")
    public String uploadPostMedia (@PathVariable Long postId,
                                   @RequestBody UploadMediaRequest uploadMediaRequest) {
        return mediaService.uploadPostMedia(postId, uploadMediaRequest);
    }

    @DeleteMapping("/{postId}/media")
    public String deletePostMedia (@PathVariable Long postId) {
        return mediaService.deletePostMedia(postId);
    }
}
