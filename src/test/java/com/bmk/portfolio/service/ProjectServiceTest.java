package com.bmk.portfolio.service;

import com.bmk.portfolio.dto.ProjectRequest;
import com.bmk.portfolio.model.Project;
import com.bmk.portfolio.repository.ProjectRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

    @Mock
    private ProjectRepository projectRepository;

    @InjectMocks
    private ProjectService projectService;

    @Test
    void getAllProjects_shouldReturnProjects() {

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Portfolio API");
        project.setDescription("Spring Boot project");

        when(projectRepository.findAll())
                .thenReturn(List.of(project));

        List<Project> result = projectService.getAllProjects();

        assertEquals(1, result.size());
        assertEquals("Portfolio API", result.get(0).getTitle());

        verify(projectRepository).findAll();
    }

    @Test
    void getProjectById_shouldReturnProject_whenProjectExists() {

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Portfolio API");

        when(projectRepository.findById(1L))
                .thenReturn(Optional.of(project));

        Project result = projectService.getProjectById(1L);

        assertNotNull(result);
        assertEquals("Portfolio API", result.getTitle());

        verify(projectRepository).findById(1L);
    }

    @Test
    void getProjectById_shouldReturnNull_whenProjectDoesNotExist() {

        when(projectRepository.findById(99L))
                .thenReturn(Optional.empty());

        Project result = projectService.getProjectById(99L);

        assertNull(result);

        verify(projectRepository).findById(99L);
    }

    @Test
    void createProject_shouldSaveAndReturnProject() {

        ProjectRequest request = new ProjectRequest();
        request.setTitle("Portfolio Backend API");
        request.setDescription("Spring Boot REST API");
        request.setGithubUrl("https://github.com/example/project");
        request.setDemoUrl("");
        request.setImageUrl("");

        Project savedProject = new Project();
        savedProject.setId(1L);
        savedProject.setTitle(request.getTitle());
        savedProject.setDescription(request.getDescription());
        savedProject.setGithubUrl(request.getGithubUrl());

        when(projectRepository.save(any(Project.class)))
                .thenReturn(savedProject);

        Project result = projectService.createProject(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(
                "Portfolio Backend API",
                result.getTitle()
        );

        verify(projectRepository).save(any(Project.class));
    }

    @Test
    void deleteProject_shouldReturnTrue_whenProjectExists() {

        when(projectRepository.existsById(1L))
                .thenReturn(true);

        boolean result = projectService.deleteProject(1L);

        assertTrue(result);

        verify(projectRepository).deleteById(1L);
    }

    @Test
    void deleteProject_shouldReturnFalse_whenProjectDoesNotExist() {

        when(projectRepository.existsById(99L))
                .thenReturn(false);

        boolean result = projectService.deleteProject(99L);

        assertFalse(result);

        verify(projectRepository, never()).deleteById(99L);
    }
}