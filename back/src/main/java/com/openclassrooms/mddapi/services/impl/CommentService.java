package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.CommentRepository;

import com.openclassrooms.mddapi.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service implementation for managing comments.
 * Handles creation of comments and retrieval of comments by post ID.
 */
@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Override
    public Comment createComment(String content, Long postId) {
        User user = userService.getConnectedUser();
        Post post = postService.getPostById(postId);
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(user);
        comment.setPost(post);
        comment.setCreatedAt(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> getCommentByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
}
