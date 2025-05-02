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

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final IPostService postService;
    private final ICommentService commentService;
    private final PostMapper postMapper;
    private final CommentMapper commentMapper;

    @GetMapping
    public List<PostDTO> getAllPosts() {
        return postService.getPosts().stream().map(postMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(postMapper.toDto(post));
    }

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@RequestBody CreatePostDTO createPostDTO) {
        Post createdPost = postService.createPost(createPostDTO.title(), createPostDTO.content(), createPostDTO.topicId());
        return ResponseEntity.ok(postMapper.toDto(createdPost));
    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentDTO>> getCommentsByPost(@PathVariable Long id) {
        List<Comment> comments = commentService.getCommentByPostId(id);
        List<CommentDTO> dtos = comments.stream().map(commentMapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> createComment(@PathVariable Long id, @RequestBody CreateCommentDTO content) {
        Comment comment = commentService.createComment(content.content(), id);
        return ResponseEntity.ok(commentMapper.toDto(comment));
    }
}
