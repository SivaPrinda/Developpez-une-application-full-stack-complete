package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.models.Topic;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between {@link Topic} entities and {@link TopicDTO} objects.
 *
 * <p>This interface uses MapStruct to automatically generate the mapping implementation.</p>
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper{
        TopicDTO toDto(Topic topic);
        Topic toEntity(TopicDTO dto);
}
