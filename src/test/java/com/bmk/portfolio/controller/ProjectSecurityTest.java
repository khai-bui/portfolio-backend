package com.bmk.portfolio.controller;

import com.bmk.portfolio.config.SecurityConfig;
import com.bmk.portfolio.model.Project;
import com.bmk.portfolio.service.CustomUserDetailsService;
import com.bmk.portfolio.service.ProjectService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProjectController.class)
@Import(SecurityConfig.class)
@TestPropertySource(properties = {
        "jwt.secret=01234567890123456789012345678901"
})
class ProjectSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProjectService projectService;

    @MockitoBean
    private CustomUserDetailsService customUserDetailsService;


    // 1. GET public -> 200
    @Test
    void getProjects_withoutToken_shouldReturn200() throws Exception {

        Project project = new Project();
        project.setId(1L);
        project.setTitle("Portfolio API");
        project.setDescription("Spring Boot project");

        when(projectService.getAllProjects())
                .thenReturn(List.of(project));

        mockMvc.perform(
                        get("/api/projects")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title")
                        .value("Portfolio API"));
    }


    // 2. POST không token -> 401
    @Test
    void createProject_withoutToken_shouldReturn401() throws Exception {

        mockMvc.perform(
                        post("/api/projects")
                                .contentType("application/json")
                                .content("""
                                {
                                  "title": "Test Project",
                                  "description": "Test description"
                                }
                                """)
                )
                .andExpect(status().isUnauthorized());
    }


    // 3. Có JWT nhưng không phải ADMIN -> 403
    @Test
    void createProject_withoutAdminRole_shouldReturn403()
            throws Exception {

        mockMvc.perform(
                        post("/api/projects")

                                .with(jwt())

                                .contentType("application/json")

                                .content("""
                                {
                                  "title": "Test Project",
                                  "description": "Test description"
                                }
                                """)
                )
                .andExpect(status().isForbidden());
    }


    // 4. ADMIN JWT -> 201
    @Test
    void createProject_withAdminRole_shouldReturn201()
            throws Exception {

        Project savedProject = new Project();

        savedProject.setId(1L);
        savedProject.setTitle("Test Project");
        savedProject.setDescription("Test description");

        when(projectService.createProject(any()))
                .thenReturn(savedProject);


        mockMvc.perform(
                        post("/api/projects")

                                .with(
                                        jwt().authorities(
                                                new SimpleGrantedAuthority(
                                                        "ROLE_ADMIN"
                                                )
                                        )
                                )

                                .contentType("application/json")

                                .content("""
                                {
                                  "title": "Test Project",
                                  "description": "Test description"
                                }
                                """)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title")
                        .value("Test Project"));
    }


    // 5. ADMIN nhưng DTO sai -> 400
    @Test
    void createProject_withInvalidData_shouldReturn400()
            throws Exception {

        mockMvc.perform(
                        post("/api/projects")

                                .with(
                                        jwt().authorities(
                                                new SimpleGrantedAuthority(
                                                        "ROLE_ADMIN"
                                                )
                                        )
                                )

                                .contentType("application/json")

                                .content("""
                                {
                                  "title": "",
                                  "description": ""
                                }
                                """)
                )
                .andExpect(status().isBadRequest());
    }
}