package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Post;

import java.util.List;

public interface IPostService{

    List<Post> getPosts();

    Post getPostById(Long id);

    Post createPost(String title, String content, long topicId);

}
