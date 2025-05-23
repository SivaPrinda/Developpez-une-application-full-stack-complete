package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Comment;

import java.util.List;

/**
 * Service interface for managing comments.
 *
 * <p>This interface defines the contract for comment-related operations,
 * such as creating a comment and retrieving comments associated with a post.</p>
 */
public interface ICommentService {

    /**
     * Creates a new comment for a specific post.
     *
     * @param content The content of the comment.
     * @param postId The ID of the post to which the comment is attached.
     * @return The saved Comment entity.
     */
    Comment createComment(String content, Long postId);

    /**
     * Retrieves all comments associated with a given post ID.
     *
     * @param postId The ID of the post for which to fetch comments.
     * @return A list of Comment entities related to the given post.
     */
    List<Comment> getCommentByPostId(Long postId);
}
