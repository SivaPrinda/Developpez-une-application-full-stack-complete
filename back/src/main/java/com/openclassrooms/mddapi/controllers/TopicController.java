package com.openclassrooms.mddapi.controllers;

import com.openclassrooms.mddapi.dto.response.TopicDTO;
import com.openclassrooms.mddapi.mappers.TopicMapper;
import com.openclassrooms.mddapi.services.ITopicService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/topic")
public class TopicController {

    private final ITopicService itopicService;
    private final TopicMapper topicMapper;

    @GetMapping
    public List<TopicDTO> getAllTopics() {
        return itopicService.getAllTopics().stream().map(topicMapper::toDto).toList();
    }

}
