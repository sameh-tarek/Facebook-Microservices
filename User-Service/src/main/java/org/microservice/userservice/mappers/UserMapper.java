package org.microservice.userservice.mappers;

import org.mapstruct.Mapper;
import org.microservice.userservice.dtos.user.UserDTO;
import org.microservice.userservice.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    public UserDTO toDTO (User user);
    public User toEntity (UserDTO userDTO);
}
