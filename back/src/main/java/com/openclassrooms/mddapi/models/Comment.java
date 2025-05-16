package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * Entity representing a comment on a post.
 *
 * <p>This class is mapped to the <code>COMMENT</code> table and stores user-generated
 * comments associated with posts. It includes the comment content, the user who wrote it,
 * the post it belongs to, and the creation timestamp.</p>
 */
@Entity
@Table(name = "COMMENT")
@Data
@NoArgsConstructor
public class Comment {
    /**
     * Unique identifier for the comment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Textual content of the comment.
     */
    private String content;

    /**
     * The user who authored the comment.
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The post associated with the comment.
     */
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    /**
     * Timestamp indicating when the comment was created.
     * Automatically generated upon creation.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;
}
