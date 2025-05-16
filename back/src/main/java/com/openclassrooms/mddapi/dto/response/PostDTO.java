package com.openclassrooms.mddapi.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * DTO for returning post data to the client.
 *
 * <p>This record encapsulates the post's ID, title, content, author name,
 * topic name, and timestamps for creation and last update,
 * as returned in API responses.</p>
 *
 * @param id the unique identifier of the post
 * @param title the title of the post
 * @param content the content body of the post
 * @param author the name of the post's author
 * @param topic the name of the topic to which the post belongs
 * @param createdAt the date and time the post was created
 * @param updatedAt the date and time the post was last updated
 */
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
