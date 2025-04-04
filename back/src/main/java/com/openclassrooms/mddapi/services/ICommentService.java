package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.request.CreateCommentDTO;

public interface ICommentService {

    String createComment(CreateCommentDTO createCommentDto);
}
