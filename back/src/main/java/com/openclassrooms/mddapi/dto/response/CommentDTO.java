package com.openclassrooms.mddapi.dto.response;


import java.time.LocalDateTime;

/**
 * DTO for returning comment data to the client.
 *
 * <p>This record encapsulates the comment's ID, content, author name, and creation timestamp,
 * as returned in API responses.</p>
 *
 * @param id the unique identifier of the comment
 * @param content the textual content of the comment
 * @param authorName the name of the user who authored the comment
 * @param createdAt the timestamp when the comment was created
 */
public record CommentDTO(
        Long id,
        String content,
        String authorName,
        LocalDateTime createdAt
) {
}
