package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper interface for converting between {@link User} entities and {@link UserDTO} objects.
 *
 * <p>This interface uses MapStruct to automatically generate the mapping implementation
 * used for transforming user data between persistence and response layers.</p>
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

}
