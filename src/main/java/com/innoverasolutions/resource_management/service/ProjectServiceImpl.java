package com.innoverasolutions.resource_management.service;

import com.innoverasolutions.resource_management.model.Project;
import com.innoverasolutions.resource_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    @Override
    public List<Project> findProjectsByUserId(Long userId) {
        return projectRepository.findByUserId(userId);
    }

    @Override
    public Optional<Project> findProjectById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }
}
