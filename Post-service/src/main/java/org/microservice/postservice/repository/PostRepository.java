package org.microservice.postservice.repository;

import org.microservice.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends
        JpaRepository<Post, Long> {
    List<Post> findByUserId(Long userId);
    boolean existsPostsByPostId(Long postId);
}
