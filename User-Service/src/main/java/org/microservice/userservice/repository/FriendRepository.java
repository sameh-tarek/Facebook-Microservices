package org.microservice.userservice.repository;

import org.microservice.userservice.entity.Friend;
import org.microservice.userservice.entity.User;
import org.microservice.userservice.entity.enums.FriendshipStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends
        JpaRepository<Friend, Long> {
    List<Friend> findFriendByUserId (Long id);
    boolean existsByUserAndFriendUserAndStatusIn (User user, User friendUser, List<FriendshipStatus> statuses) ;
}
