package org.microservice.userservice.service;

import org.microservice.userservice.dtos.user.UserDTO;

public interface UserService {
    UserDTO getUserProfileById(Long id);

    UserDTO updateProfile(Long id, UserDTO userDTO);
}
