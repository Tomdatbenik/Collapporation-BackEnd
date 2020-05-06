package com.collapporation.projectservice.service;

import com.collapporation.projectservice.config.RestConfig;
import com.collapporation.projectservice.models.Project;
import com.collapporation.projectservice.models.Projection.IProjectFeed;
import com.collapporation.projectservice.models.dto.ProjectDTO;
import com.collapporation.projectservice.models.dto.ProjectFeedDTO;
import com.collapporation.projectservice.repo.ProjectRepo;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
@AllArgsConstructor
public class FeedService {
    private final ProjectRepo projectRepo;

    private final RestTemplate restTemplate;

    public List<ProjectFeedDTO> getProjectFeed(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        List<Project> projectList = projectRepo.findAllByOrderByCreatedDesc(pageable);

        List<ProjectFeedDTO> projectFeedList = new ArrayList<>();

        projectList.stream().forEach(p -> {
            ProjectFeedDTO projectFeedDTO = new ProjectFeedDTO(p);
            projectFeedDTO.setOwner(restTemplate.getForObject("http://user-service/user/" + p.getOwnerId(), String.class));
            projectFeedList.add(projectFeedDTO);
        });

        projectFeedList.removeIf(Objects::isNull);
        return projectFeedList;
    }
}
