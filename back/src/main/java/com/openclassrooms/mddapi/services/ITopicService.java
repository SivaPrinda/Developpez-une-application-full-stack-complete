package com.openclassrooms.mddapi.services;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.models.Topic;

import java.util.List;

public interface ITopicService {

    List<Topic> getAllTopics();

    Topic getById(Long id);
}
