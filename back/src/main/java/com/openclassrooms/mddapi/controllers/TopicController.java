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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
public class TopicController {

    private final ITopicService iTopicService;
    private final TopicMapper topicMapper;
    private final UserMapper userMapper;

    @GetMapping
    public List<TopicDTO> getAllTopics() {
        return iTopicService.getAllTopics().stream().map(topicMapper::toDto).toList();
    }

    /**
     * Follows a topic for the connected user.
     * Endpoint: PUT /api/user/subscribe/{topicId}
     *
     * @param topicId the ID of the topic to follow.
     * @return a ResponseEntity with the updated UserDTO.
     */
    @PutMapping("/subscribe/{topicId}")
    public ResponseEntity<UserDTO> followTopic(@PathVariable Long topicId) {
        User user = iTopicService.subscribeTopic(topicId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    /**
     * Unfollows a topic for the connected user.
     * Endpoint: DELETE /api/user/unsubscribe/{topicId}
     *
     * @param topicId the ID of the topic to unfollow.
     * @return a ResponseEntity with the updated UserDTO.
     */
    @DeleteMapping("/unsubscribe/{topicId}")
    public ResponseEntity<UserDTO> unfollowTopic(@PathVariable Long topicId) {
        User user = iTopicService.unsubscribeTopic(topicId);
        return ResponseEntity.ok(userMapper.toDto(user));
    }

    @GetMapping("/subscriptions")
    public ResponseEntity<List<TopicDTO>> getFollowedTopics() {
        List<TopicDTO> followedTopics = iTopicService.getFollowedTopics().stream()
                .map(topicMapper::toDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(followedTopics);
    }

}
