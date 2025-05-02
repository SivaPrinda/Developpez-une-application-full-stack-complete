package com.openclassrooms.mddapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        String content,
        String authorName,
        LocalDateTime createdAt
) {
}
