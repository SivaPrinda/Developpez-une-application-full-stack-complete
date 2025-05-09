package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.CreateCommentDTO;
import com.openclassrooms.mddapi.dto.request.CreatePostDTO;
import com.openclassrooms.mddapi.dto.response.CommentDTO;
import com.openclassrooms.mddapi.dto.response.PostDTO;
import com.openclassrooms.mddapi.mappers.CommentMapper;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.services.ICommentService;
import com.openclassrooms.mddapi.services.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing posts and their comments.
 * Provides endpoints for creating and retrieving posts and comments.
 */
@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;
    private final ICommentService commentService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    /**
     * Retrieves all posts.
     * @return a list of all PostDTOs.
     */
    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getPosts().stream().map(postMapper::toDto).toList();
    }

    /**
     * Retrieves a specific post by its ID.
     * @param id the ID of the post.
     * @return the corresponding PostDTO wrapped in a ResponseEntity.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    /**
     * Creates a new post.
     * @param createPostDTO the data needed to create a post (title, content, topic ID).
     * @return the created PostDTO wrapped in a ResponseEntity.
     */
    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO createPostDTO) {
        Post createdPost = postService.createPost(createPostDTO.title(), createPostDTO.content(), createPostDTO.topicId());
        return ResponseEntity.ok(postMapper.toDto(createdPost));
    }

    /**
     * Retrieves all comments associated with a specific post.
     * @param id the ID of the post.
     * @return a list of CommentDTOs wrapped in a ResponseEntity.
     */
    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long id) {
        List<Comment> comments = commentService.getCommentByPostId(id);
        List<CommentDTO> dtos = comments.stream().map(commentMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    /**
     * Creates a comment for a specific post.
     * @param id the ID of the post to comment on.
     * @param content the DTO containing the content of the comment.
     * @return the created CommentDTO wrapped in a ResponseEntity.
     */
    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long id, @RequestBody CreateCommentDTO content) {
        Comment comment = commentService.createComment(content.content(), id);
        return ResponseEntity.ok(commentMapper.toDto(comment));
    }
}
