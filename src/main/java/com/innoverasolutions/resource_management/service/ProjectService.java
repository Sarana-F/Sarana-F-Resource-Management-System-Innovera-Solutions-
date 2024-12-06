package com.innoverasolutions.resource_management.service;

import com.innoverasolutions.resource_management.model.Project;

import java.util.List;
import java.util.Optional;

public interface ProjectService {
    Project saveProject(Project project);
    List<Project> findProjectsByUserId(Long userId);
    Optional<Project> findProjectById(Long id);
    void deleteProject(Long id);
    List<Project> findAllProjects();  // Add this method
}
