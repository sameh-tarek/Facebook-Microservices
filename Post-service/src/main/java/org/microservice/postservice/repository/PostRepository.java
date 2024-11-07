package org.microservice.postservice.repository;

import org.microservice.postservice.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends
        JpaRepository<Post, Long> {
}
