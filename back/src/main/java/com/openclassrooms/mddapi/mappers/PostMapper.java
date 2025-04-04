package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.PostDTO;
import com.openclassrooms.mddapi.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    PostDTO toDto(Post post);
    Post toEntity(PostDTO dto);
}
