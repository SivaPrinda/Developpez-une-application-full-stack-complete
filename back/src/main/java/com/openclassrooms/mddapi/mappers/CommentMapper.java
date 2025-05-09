package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.CommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {
    @Mapping(target = "authorName", source = "user.name")
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}