package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.dto.request.CreateCommentDTO;
import com.openclassrooms.mddapi.models.Comment;
import com.openclassrooms.mddapi.models.Post;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.CommentRepository;

import com.openclassrooms.mddapi.services.ICommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService implements ICommentService {

    private final CommentRepository commentRepository;
    private final UserService userService;
    private final PostService postService;

    @Override
    public String createComment(CreateCommentDTO createCommentDto) {
        // Create a new Message entity.
        Comment comment = new Comment();

        // Retrieve the User associated with the message using the userId from the DTO.
        User user = userService.getUser(createCommentDto.userId());
        comment.setUser(user); // Set the user for the message.

        // Retrieve the Rental associated with the message using the rentalId from the DTO.
        Post rental = postService.getPostById(createCommentDto.postId());
        comment.setPost(rental); // Set the rental for the message.

        // Set the message content from the DTO.
        comment.setComment(createCommentDto.comment());

        // Save the message to the database and return its string representation.
        comment = commentRepository.save(comment);
        return comment.toString();
    }
}
