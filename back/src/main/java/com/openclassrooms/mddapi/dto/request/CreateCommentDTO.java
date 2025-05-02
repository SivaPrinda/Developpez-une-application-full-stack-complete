package com.openclassrooms.mddapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCommentDTO(
        @Schema(description = "Comment")
        String content
) {
}
