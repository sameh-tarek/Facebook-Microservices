package org.microservice.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.userservice.dtos.friend.FriendDTO;
import org.microservice.userservice.dtos.friend.FriendRequestDTO;
import org.microservice.userservice.entity.Friend;
import org.microservice.userservice.entity.User;
import org.microservice.userservice.entity.enums.FriendshipStatus;
import org.microservice.userservice.exception.ConflictException;
import org.microservice.userservice.exception.RecordNotFoundException;
import org.microservice.userservice.mappers.FriendMapper;
import org.microservice.userservice.repository.FriendRepository;
import org.microservice.userservice.repository.UserRepository;
import org.microservice.userservice.service.FriendService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    private final FriendMapper friendMapper;
    @Override
    public List<FriendDTO> getUserFriends(Long id) {
        log.info("Getting User Friends with id: {}", id);
        List<Friend> friends = friendRepository.findFriendByUserId(id)
                .stream()
                .filter(friend -> friend.getStatus().equals(FriendshipStatus.ACCEPTED))
                .collect(Collectors.toList());
        List<FriendDTO> friendDTOS = friendMapper.toDTOs(friends);
        log.trace("user with id: {}, friends: {}", id, friendDTOS);
        return friendDTOS;
    }

    @Override
    public String sendFriendRequest(Long id, FriendRequestDTO friendRequestDTO) {
        Long senderId = friendRequestDTO.getSenderId();
        log.info("user with id: {}, want to send request for user with id: {}", senderId, id);
        if (senderId.equals(id)) {
            log.error("User cannot send a friend request to themselves.");
            throw new IllegalArgumentException("User cannot send a friend request to themselves.");
        }

        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RecordNotFoundException("sender with id: "+ senderId + ", Doesn't Exist."));
        User recipient = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("recipient with id: "+ id + ", Doesn't Exist."));

        if (checkExistingFriendship(sender, recipient)) {
            log.error("Friend request already exists or users are already friends.");
            throw new ConflictException("Friend request already exists or users are already friends.");
        }

        Friend friendRequest = new Friend(sender, recipient, FriendshipStatus.PENDING);
        friendRequest.setCreatedAt(LocalDateTime.now());
        friendRepository.save(friendRequest);
        return "Friend request sent successfully.";
    }

    private boolean checkExistingFriendship(User sender, User recipient) {
        return friendRepository.existsByUserAndFriendUserAndStatusIn(
                sender,
                recipient,
                List.of(FriendshipStatus.ACCEPTED, FriendshipStatus.PENDING)
        );
    }


}
