package org.microservice.userservice.repository;

import org.microservice.userservice.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRepository extends
        JpaRepository<Friend, Long> {
}
