package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.CommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
/**
 * Mapper interface for converting between {@link Comment} entities and {@link CommentDTO} objects.
 *
 * <p>This interface uses MapStruct to automatically generate the mapping implementation,
 * including custom mapping for the author's name from the nested user object.</p>
 */
public interface CommentMapper {
    @Mapping(target = "authorName", source = "user.name")
    CommentDTO toDto(Comment comment);
    Comment toEntity(CommentDTO commentDTO);
}