package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.models.User;

import java.util.List;

public interface ITopicService {

    List<Topic> getAllTopics();

    Topic getById(Long id);

    User subscribeTopic(Long topicId);

    User unsubscribeTopic(Long topicId);

    List<Topic> getFollowedTopics();
}
