package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Entity representing a post published by a user.
 *
 * <p>This class is mapped to the <code>POST</code> table and includes fields for
 * title, content, associated topic, author (user), and timestamps for creation and update.
 * It also manages the relationship with related comments.</p>
 */

@Entity
@Table(name = "POST")
@Data
@NoArgsConstructor
public class Post{
    /**
     * Unique identifier for the post.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Title of the post.
     */
    @Column(nullable = false)
    private String title;

    /**
     * Main textual content of the post.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    /**
     * The topic associated with the post.
     */
    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;

    /**
     * The user who authored the post.
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Timestamp when the post was created.
     * Automatically generated on creation.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * Timestamp when the post was last updated.
     * Automatically updated on modification.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    /**
     * List of comments associated with the post.
     */
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
