package org.microservice.likeservice.mappers;

import org.mapstruct.Mapper;
import org.microservice.likeservice.dto.LikeDTO;
import org.microservice.likeservice.entity.Like;

@Mapper(componentModel = "spring")
public interface LikeMapper {
    public Like toEntity (LikeDTO likeDTO);
    public LikeDTO toDTO (Like like);
}
