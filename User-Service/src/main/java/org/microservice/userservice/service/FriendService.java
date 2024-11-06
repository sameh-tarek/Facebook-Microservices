package org.microservice.userservice.service;

import org.microservice.userservice.dtos.friend.FriendDTO;
import org.microservice.userservice.dtos.friend.FriendRequestDTO;

import java.util.List;
import java.util.Map;

public interface FriendService {
    List<FriendDTO> getUserFriends(Long id);

    String sendFriendRequest(Long id, FriendRequestDTO friendRequestDTO);
}
