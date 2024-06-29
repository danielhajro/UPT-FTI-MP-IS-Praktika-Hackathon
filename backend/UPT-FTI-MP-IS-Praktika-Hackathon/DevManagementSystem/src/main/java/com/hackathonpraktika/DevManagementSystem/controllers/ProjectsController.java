package com.hackathonpraktika.DevManagementSystem.controllers;

import com.hackathonpraktika.DevManagementSystem.model.Projects;
import com.hackathonpraktika.DevManagementSystem.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Projects> createProject(@RequestBody Projects project) {
        Projects createdProject = projectService.createProject(project);
        return ResponseEntity.ok(createdProject);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Projects> updateProject(@PathVariable(value = "id") Long projectId, @RequestBody Projects projectDetails) {
        Projects updatedProject = projectService.updateProject(projectId,projectDetails);
        return ResponseEntity.ok(updatedProject);
    }

    @GetMapping
    public ResponseEntity<List<Projects>> getAllProjects() {
        List<Projects> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> getProjectById(@PathVariable(value = "id") Long projectId) {
        Projects project = projectService.getAllProjectsById(projectId);
        return ResponseEntity.ok(project);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjects(@PathVariable Long id) {
        projectService.deleteProjects(id);
        return ResponseEntity.noContent().build();
    }

}
