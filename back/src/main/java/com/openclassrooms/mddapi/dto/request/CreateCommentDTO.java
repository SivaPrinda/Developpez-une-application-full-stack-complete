package com.openclassrooms.mddapi.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCommentDTO(
        @Schema(description = "Comment")
        String comment,

        // Maps the 'userId' field to 'user_id' in JSON for consistency
        @Schema(description = "User ID")
        @JsonProperty("user_id")
        Long userId,

        // Maps the 'rentalId' field to 'rental_id' in JSON for consistency
        @Schema(description = "Post ID")
        @JsonProperty("post_id")
        Long postId
) {
}
