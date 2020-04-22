package com.collapporation.projectservice.service;

import com.collapporation.projectservice.event.ProjectCreatedEvent;
import com.collapporation.projectservice.kafka.dispatcher.IDispatcher;
import com.collapporation.projectservice.models.Project;
import com.collapporation.projectservice.models.ProjectStatus;
import com.collapporation.projectservice.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    public ProjectRepo projectRepo;

    @Autowired
    private IDispatcher dispatcher;

    public Project getProject(String id){
        return projectRepo.findById(id).orElse(null);
    }

    public void createProject(Project project)
    {
        dispatcher.dispatch("project", new ProjectCreatedEvent(project));
    }

    public void updateStatus(String id, ProjectStatus status)
    {

    }
}
