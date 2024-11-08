package org.microservice.postservice.mappers;

import org.mapstruct.Mapper;
import org.microservice.postservice.dto.PostRequestDTO;
import org.microservice.postservice.dto.PostResponseDTO;
import org.microservice.postservice.entity.Post;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PostMapper {
    public Post toEntity (PostRequestDTO postRequestDTO);
    public PostResponseDTO toDTO (Post post);

    List<PostResponseDTO> toDTOs (List<Post> posts);
}
