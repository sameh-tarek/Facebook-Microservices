package org.microservice.likeservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.likeservice.dto.LikeDTO;
import org.microservice.likeservice.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;
    @PostMapping("/{postId}")
    public String doLikeOnPost (@PathVariable Long postId,
                                @RequestBody LikeDTO likeDTO) {
        return likeService.doLikeOnPost(postId, likeDTO);
    }

    
}
