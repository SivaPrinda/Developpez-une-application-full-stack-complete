package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "TOPIC")
@Data
@NoArgsConstructor
/**
 * Entity representing a discussion topic.
 *
 * <p>This class is mapped to the <code>TOPIC</code> table and holds information such as
 * a unique name and a detailed description for each topic available in the application.</p>
 */
public class Topic {
    /**
     * Unique identifier for the topic.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The unique name of the topic.
     */
    @Column(nullable = false, unique = true)
    private String name;

    /**
     * A detailed description of the topic.
     */
    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;
}
