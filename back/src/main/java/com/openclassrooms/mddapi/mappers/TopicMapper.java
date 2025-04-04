package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.models.Topic;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TopicMapper{
        TopicDTO toDto(Topic topic);
        Topic toEntity(TopicDTO dto);
}
