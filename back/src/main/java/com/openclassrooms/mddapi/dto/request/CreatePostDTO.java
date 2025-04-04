package com.openclassrooms.mddapi.dto.request;

public record CreatePostDTO (

    Long topiId,

    String title,

    String content
    ){}
