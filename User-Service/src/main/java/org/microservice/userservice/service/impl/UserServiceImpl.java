package org.microservice.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.microservice.userservice.dtos.user.UserDTO;
import org.microservice.userservice.entity.User;
import org.microservice.userservice.exception.RecordNotFoundException;
import org.microservice.userservice.mappers.UserMapper;
import org.microservice.userservice.repository.UserRepository;
import org.microservice.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public UserDTO getUserProfileById(Long id) {
        log.info("getting user profile by id {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("This User with id "+ id + " Doesn't Exist!"));
        UserDTO userDTO = userMapper.toDTO(user);
        log.info("user with id: {}, profile details: {}", id, userDTO);
        return userDTO;
    }

    @Override
    public UserDTO updateProfile(Long id, UserDTO userDTO) {
        log.info("Updating user profile with id {}, new data: {}", id, userDTO);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException("User with id " + id + " doesn't exist!"));

        existingUser.setUserName(userDTO.getUserName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setProfilePictureUrl(userDTO.getProfilePictureUrl());

        User updatedUser = userRepository.save(existingUser);
        log.info("User with id {} updated successfully", id);

        UserDTO updatedUserDTO = userMapper.toDTO(updatedUser);

        log.trace("User with id {}: before update: {}, after update: {}", id, existingUser, updatedUser);
        return updatedUserDTO;
    }

}
