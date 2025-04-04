package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.services.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.models.Topic;
import com.openclassrooms.mddapi.repositories.TopicRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TopicService implements ITopicService {

    private final TopicRepository topicRepository;

    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getById( Long id){
        return topicRepository.findById(id).orElse(null);
    }

}
