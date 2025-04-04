package com.openclassrooms.mddapi.mappers;

import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO userDTO);

}
