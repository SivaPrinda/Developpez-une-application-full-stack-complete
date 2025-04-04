package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.request.CreateCommentDTO;
import com.openclassrooms.mddapi.dto.response.CommentDTO;
import com.openclassrooms.mddapi.services.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;

    /**
     * Handles the creation of a new message.
     * Endpoint: POST /api/messages
     *
     * @param createCommentDTO the message data received from the request body.
     * @return a ResponseEntity containing a MessageDTO with a success message.
     */
    @PostMapping
    public ResponseEntity<CommentDTO> createMessage(@RequestBody CreateCommentDTO createCommentDTO) {
        iCommentService.createComment(createCommentDTO);
        return ResponseEntity.ok(new CommentDTO("Message send with success"));
    }

}
