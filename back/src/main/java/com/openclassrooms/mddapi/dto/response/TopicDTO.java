package com.openclassrooms.mddapi.dto.response;

/**
 * DTO for returning topic data to the client.
 *
 * <p>This record encapsulates the topic's ID, name, and description
 * as provided in API responses.</p>
 *
 * @param id the unique identifier of the topic
 * @param name the name of the topic
 * @param description a detailed description of the topic
 */
public record TopicDTO(
        Long id,
        String name,
        String description
) {
}
