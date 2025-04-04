package com.openclassrooms.mddapi.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

public record PostDTO(
    Long id,
    String title,
    String content,
    UserDTO user,

    TopicDTO topic
    ) {}
