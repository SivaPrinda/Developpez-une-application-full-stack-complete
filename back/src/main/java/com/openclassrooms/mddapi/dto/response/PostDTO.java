package com.openclassrooms.mddapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record PostDTO(
    Long id,
    String title,
    String content,
    String author,
    String topic,
    LocalDateTime createdAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - hh:mm a")
    LocalDateTime updatedAt
    ) {}
