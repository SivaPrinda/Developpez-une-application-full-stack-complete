package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.Exception.ResponseEntityException;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.repositories.PostRepository;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.IPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService implements IPostService {

    private final PostRepository postRepository;
    private final UserService userService;
    private final TopicService topicService;

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post getPostById(Long postId) {
    return postRepository.findById(postId).orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND,"Post not found"));
    }

    @Override
    public Post createPost(String title, String content, long topicId) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setTopic(topicService.getById(topicId));
        post.setUser(userService.getConnectedUser());
        return postRepository.save(post);
    }
}
