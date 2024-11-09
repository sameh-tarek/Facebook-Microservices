package org.microservice.likeservice.repository;

import org.microservice.likeservice.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends
        JpaRepository<Like, Long> {
}
