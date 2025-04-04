package com.openclassrooms.mddapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record CommentDTO(
        @Schema(description = "Comment")
        String message
) {
}
