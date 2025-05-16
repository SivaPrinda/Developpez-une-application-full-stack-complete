package com.openclassrooms.mddapi.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

/**
 * Entity representing an application user.
 *
 * <p>This class is mapped to the <code>USERS</code> table and implements Spring Security's {@link UserDetails}
 * to integrate with the authentication system. It contains fields such as email, name, password, followed topics,
 * and timestamps for creation and updates.</p>
 */
@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
public class User implements UserDetails {

    /**
     * Unique identifier for the user.
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Email address used as the user's login identifier.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Display name of the user.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Encrypted password used for authentication.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The list of topics followed by the user.
     *
     * <p>Represents a many-to-many relationship between users and topics,
     * stored in the <code>user_followed_topics</code> join table.</p>
     */
    @ManyToMany
    @JoinTable(
            name = "user_followed_topics",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "topic_id"})
    )
    private List<Topic> followedTopics;

    /**
     * Timestamp when the user was created.
     * Automatically set at creation time.
     */
    @Column(name = "created_at")
    @CreationTimestamp
    private Instant createdAt;

    /**
     * Timestamp of the last update to the user.
     * Automatically updated on modification.
     */
    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}