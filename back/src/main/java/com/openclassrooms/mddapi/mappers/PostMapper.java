package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.PostDTO;
import com.openclassrooms.mddapi.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between {@link Post} entities and {@link PostDTO} objects.
 *
 * <p>This interface uses MapStruct to generate the mapping implementation,
 * including custom field mapping for the topic name and the author's name.</p>
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {
    @Mapping(target = "topic", source = "topic.name")
    @Mapping(target = "author", source = "user.name")
    PostDTO toDto(Post post);

    @Mapping(target = "topic", ignore = true)
    Post toEntity(PostDTO dto);


}
