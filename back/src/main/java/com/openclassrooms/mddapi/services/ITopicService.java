package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;

import java.util.List;

public interface ITopicService {

    /**
     * Retrieves all topics from the database.
     *
     * @return a list of Topic entities.
     */
    List<Topic> getAllTopics();

    /**
     * Retrieves a topic by its ID.
     *
     * @param id the ID of the topic to retrieve.
     * @return the Topic entity if found, or null if not found.
     */
    Topic getById(Long id);

    /**
     * Subscribes the currently authenticated user to a topic.
     * If the topic is already followed, no change is made.
     *
     * @param topicId the ID of the topic to subscribe to.
     * @return the updated User entity.
     */
    User subscribeTopic(Long topicId);

    /**
     * Unsubscribes the currently authenticated user from a topic.
     * If the topic is not followed, no change is made.
     *
     * @param topicId the ID of the topic to unsubscribe from.
     * @return the updated User entity.
     */
    User unsubscribeTopic(Long topicId);

    /**
     * Retrieves the list of topics followed by the currently authenticated user.
     *
     * @return a list of Topic entities the user follows.
     */
    List<Topic> getFollowedTopics();
}
