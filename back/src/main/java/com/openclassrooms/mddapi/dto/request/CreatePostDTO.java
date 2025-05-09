package com.openclassrooms.mddapi.dto.request;

public record CreatePostDTO (

    Long topicId,

    String title,

    String content
    ){}
