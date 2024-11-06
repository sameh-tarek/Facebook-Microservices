package org.microservice.userservice.mappers;

import org.mapstruct.Mapper;
import org.microservice.userservice.dtos.friend.FriendDTO;
import org.microservice.userservice.entity.Friend;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FriendMapper {
    public FriendDTO toDTO (Friend friend);
    public Friend toEntity (FriendDTO friendDTO);
    public List<FriendDTO> toDTOs (List<Friend> friends);
    public List<Friend> toEntities (List<FriendDTO> friendDTOS);
}
