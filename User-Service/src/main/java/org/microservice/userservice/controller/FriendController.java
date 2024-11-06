package org.microservice.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.userservice.dtos.friend.FriendDTO;
import org.microservice.userservice.dtos.friend.FriendRequestDTO;
import org.microservice.userservice.service.FriendService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friends")
public class FriendController {
    private final FriendService friendService;

    @GetMapping("/{id}")
    public List<FriendDTO> getUserFriends (@PathVariable Long id) {
        return friendService.getUserFriends(id);
    }

    @PostMapping("/{id}/friend-request")
    public String sendFriendRequest (@PathVariable Long id,
                                     @RequestBody FriendRequestDTO friendRequestDTO) {
        return friendService.sendFriendRequest(id, friendRequestDTO);
    }

}
