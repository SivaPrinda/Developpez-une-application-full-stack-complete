package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.CreatePostDTO;
import com.openclassrooms.mddapi.dto.response.PostDTO;
import com.openclassrooms.mddapi.mappers.PostMapper;
import com.openclassrooms.mddapi.models.Post;
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
    private final PostMapper postMapper;

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
        Post createdPost = postService.createPost(createPostDTO.title(), createPostDTO.content(), createPostDTO.topiId());
        return ResponseEntity.ok(postMapper.toDto(createdPost));
    }
}
