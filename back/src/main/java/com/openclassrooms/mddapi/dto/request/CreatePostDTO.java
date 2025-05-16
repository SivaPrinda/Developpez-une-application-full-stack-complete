package com.openclassrooms.mddapi.dto.request;

/**
 * DTO for creating a post.
 *
 * <p>This record is used in HTTP requests to submit a new post. It includes
 * the topic ID the post belongs to, a title, and the content body.</p>
 *
 * @param topicId the ID of the topic to which the post is related
 * @param title the title of the post
 * @param content the main content of the post
 */
public record CreatePostDTO (

    Long topicId,

    String title,

    String content
    ){}
