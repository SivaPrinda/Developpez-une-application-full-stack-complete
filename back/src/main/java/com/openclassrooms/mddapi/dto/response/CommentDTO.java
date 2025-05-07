package com.openclassrooms.mddapi.dto.response;

import java.time.LocalDateTime;

public record CommentDTO(
        Long id,
        String content,
        String authorName,
        LocalDateTime createdAt
) {
}
