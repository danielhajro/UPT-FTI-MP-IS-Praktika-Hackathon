package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.model.Projects;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.ProjectsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectsRepository projectsRepository;

    @Override
    public List<Projects> getAllProjects() {
        return projectsRepository.findAll();
    }

    @Override
    public Projects getAllProjectsById(Long projectId) {
        return projectsRepository.findById(projectId).orElseThrow( () -> new RuntimeException("Project not found with id " + projectId) );
    }

    @Override
    public Projects createProject(Projects projects) {
        return projectsRepository.save(projects);
    }

    @Override
    public Projects updateProject(Long projectId, Projects projectDetail) {
        Optional<Projects> optionalProject = projectsRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Projects existingProject = optionalProject.get();
            existingProject.setProjectName(projectDetail.getProjectName());
            existingProject.setDescription(projectDetail.getDescription());
            return projectsRepository.save(existingProject);
        } else {
            throw new RuntimeException("Project not found with id " + projectId);
        }
    }

    public void deleteProjects(Long projectId){
        if (projectsRepository.existsById(projectId)){
            projectsRepository.deleteById(projectId);
        }else{
            throw new RuntimeException("Project not found with id: " + projectId);
        }
    }
}
