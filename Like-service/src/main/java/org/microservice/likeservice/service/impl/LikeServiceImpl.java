package org.microservice.likeservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.likeservice.dto.LikeDTO;
import org.microservice.likeservice.entity.Like;
import org.microservice.likeservice.exception.RecordNotFoundException;
import org.microservice.likeservice.exception.UnauthenticatedException;
import org.microservice.likeservice.mappers.LikeMapper;
import org.microservice.likeservice.proxy.PostProxy;
import org.microservice.likeservice.proxy.UserProxy;
import org.microservice.likeservice.repository.LikeRepository;
import org.microservice.likeservice.service.LikeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    private final UserProxy userProxy;
    private final PostProxy postProxy;
    private final LikeMapper likeMapper;

    @Override
    public String doLikeOnPost(Long postId, LikeDTO likeDTO) {
        Long userId = likeDTO.getUserId();
        log.info("user with id: {}, want to like post with id: {}", userId, postId);
        if (userProxy.checkUserIsAuth(userId).getStatusCode().is4xxClientError()) {
            log.error("user with id: {}, Un Auth to like this Post with id: {}", userId, postId);
            throw new UnauthenticatedException("user with id " + userId + " un Auth to like this post");
        }

        if (!postProxy.checkPostIsExist(postId)) {
            log.error("post with id {}, doesn't exist!", postId);
            throw new RecordNotFoundException("post with id " + postId + " doesn't exist!");
        }

        Like like = likeMapper.toEntity(likeDTO);
        like.setLikedAt(LocalDateTime.now());
        likeRepository.save(like);

        log.info("Like Done Successfully!");
        return "Like Done Successfully!";
    }
}
