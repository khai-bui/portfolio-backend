package com.bmk.portfolio.service;

import com.bmk.portfolio.dto.ProjectRequest;
import com.bmk.portfolio.model.Project;
import com.bmk.portfolio.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project getProjectById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }

    public Project createProject(ProjectRequest request) {
        Project project = new Project();

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setImageUrl(request.getImageUrl());

        return projectRepository.save(project);
    }

    public Project updateProject(Long id, ProjectRequest request) {
        Project project = projectRepository.findById(id).orElse(null);

        if (project == null) {
            return null;
        }

        project.setTitle(request.getTitle());
        project.setDescription(request.getDescription());
        project.setGithubUrl(request.getGithubUrl());
        project.setDemoUrl(request.getDemoUrl());
        project.setImageUrl(request.getImageUrl());

        return projectRepository.save(project);
    }

    public boolean deleteProject(Long id) {
        if (!projectRepository.existsById(id)) {
            return false;
        }

        projectRepository.deleteById(id);
        return true;
    }
}