package org.microservice.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.microservice.userservice.dtos.user.UserDTO;
import org.microservice.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public UserDTO getUserProfileById (@PathVariable Long id) {
        return userService.getUserProfileById(id);
    }

    @PutMapping("/{id}")
    public UserDTO updateProfile (@PathVariable Long id, @RequestBody UserDTO userDTO) {
        return userService.updateProfile(id, userDTO);
    }

}
