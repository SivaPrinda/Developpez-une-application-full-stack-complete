package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Comment;

import java.util.List;

public interface ICommentService {

    Comment createComment(String content, Long postId);

    List<Comment> getCommentByPostId(Long postId);
}
