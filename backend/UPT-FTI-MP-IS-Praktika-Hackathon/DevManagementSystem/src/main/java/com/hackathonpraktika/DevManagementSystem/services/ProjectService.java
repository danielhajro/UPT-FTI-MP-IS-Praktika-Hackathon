package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.model.Projects;
import org.springframework.stereotype.Service;
import java.util.*;
public interface ProjectService {
    List<Projects> getAllProjects();
    Projects getAllProjectsById(Long projectId);
    Projects createProject(Projects projects);
    Projects updateProject(Long projectId, Projects projectDetail);
    public void deleteProjects(Long projectId);

}
