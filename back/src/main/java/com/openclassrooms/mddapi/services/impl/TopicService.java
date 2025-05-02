package com.openclassrooms.mddapi.services.impl;

import com.openclassrooms.mddapi.Exception.ResponseEntityException;
import com.openclassrooms.mddapi.models.User;
import com.openclassrooms.mddapi.repositories.UserRepository;
import com.openclassrooms.mddapi.services.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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

    private final UserService userService;

    private final UserRepository userRepository;


    @Override
    public List<Topic> getAllTopics() {
        return topicRepository.findAll();
    }

    @Override
    public Topic getById( Long id){
        return topicRepository.findById(id).orElse(null);
    }

    @Override
    public User subscribeTopic(Long topicId) {
        User user = userService.getConnectedUser();
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Topic not found", topicId));

        if (!user.getFollowedTopics().contains(topic)) {
            user.getFollowedTopics().add(topic);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public User unsubscribeTopic(Long topicId) {
        User user = userService.getConnectedUser();
        Topic topic = topicRepository.findById(topicId)
                .orElseThrow(() -> new ResponseEntityException(HttpStatus.NOT_FOUND, "Topic not found", topicId));

        if (user.getFollowedTopics().contains(topic)) {
            user.getFollowedTopics().remove(topic);
            user = userRepository.save(user);
        }
        return user;
    }

    @Override
    public List<Topic> getFollowedTopics() {
        User user = userService.getConnectedUser();
        return user.getFollowedTopics();
    }


}
