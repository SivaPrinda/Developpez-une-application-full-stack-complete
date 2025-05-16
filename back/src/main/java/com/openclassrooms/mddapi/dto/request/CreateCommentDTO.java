package com.openclassrooms.mddapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO for creating a comment.
 *
 * <p>This record is used in HTTP requests to submit a new comment associated
 * with a post. It contains the comment's textual content.</p>
 *
 * @param content the textual content of the comment to be created
 */
public record CreateCommentDTO(
        @Schema(description = "Comment")
        String content
) {
}
