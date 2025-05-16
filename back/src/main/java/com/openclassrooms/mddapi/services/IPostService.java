package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Post;

import java.util.List;

/**
 * Service interface for managing posts.
 *
 * <p>This interface defines the contract for operations related to retrieving,
 * accessing by ID, and creating posts in the application.</p>
 */
public interface IPostService{

    /**
     * Retrieves all posts from the database.
     *
     * @return a list of all Post entities.
     */
    List<Post> getPosts();

    /**
     * Retrieves a specific post by its ID.
     * If the post does not exist, a custom exception may be thrown by the implementation.
     *
     * @param id the unique identifier of the post.
     * @return the Post entity with the given ID.
     */
    Post getPostById(Long id);

    /**
     * Creates and saves a new post.
     * The post will be associated with the currently authenticated user and a specific topic.
     *
     * @param title the title of the post.
     * @param content the body/content of the post.
     * @param topicId the ID of the topic to associate the post with.
     * @return the newly created Post entity.
     */
    Post createPost(String title, String content, long topicId);

}
