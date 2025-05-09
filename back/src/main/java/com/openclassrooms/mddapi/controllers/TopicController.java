package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.dto.response.UserDTO;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.mappers.UserMapper;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.services.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for handling topic-related operations.
 * Provides endpoints to retrieve all topics, follow/unfollow topics, and get followed topics for the authenticated user.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
public class TopicController {

    private final ITopicService iTopicService;
    private final TopicMapper topicMapper;
    private final UserMapper userMapper;

    /**
     * Retrieves the list of all available topics.
     * @return a list of TopicDTO objects.
     */
    @GetMapping
    public List<TopicDTO> getAllTopics() {
        return iTopicService.getAllTopics().stream().map(topicMapper::toDto).toList();
    }

    /**
     * Subscribes the currently authenticated user to a specific topic.
     * Endpoint: PUT /api/topic/subscribe/{topicId}
     *
     * @param topicId the ID of the topic to subscribe to.
     * @return a ResponseEntity containing the updated UserDTO.
     */
    @PutMapping("/subscribe/{topicId}")
    public ResponseEntity<UserDTO> followTopic(@PathVariable Long topicId) {
        User user = iTopicService.subscribeTopic(topicId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    /**
     * Unsubscribes the currently authenticated user from a specific topic.
     * Endpoint: DELETE /api/topic/unsubscribe/{topicId}
     *
     * @param topicId the ID of the topic to unsubscribe from.
     * @return a ResponseEntity containing the updated UserDTO.
     */
    @DeleteMapping("/unsubscribe/{topicId}")
    public ResponseEntity<UserDTO> unfollowTopic(@PathVariable Long topicId) {
        User user = iTopicService.unsubscribeTopic(topicId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    /**
     * Retrieves the list of topics followed by the currently authenticated user.
     * @return a ResponseEntity containing a list of followed TopicDTO objects.
     */
    @GetMapping("/subscriptions")
    public ResponseEntity<List<TopicDTO>> getFollowedTopics() {
        List<TopicDTO> followedTopics = iTopicService.getFollowedTopics().stream()
                .map(topicMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(followedTopics);
    }

}
