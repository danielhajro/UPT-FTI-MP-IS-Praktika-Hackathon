package com.hackathonpraktika.DevManagementSystem.repositories;

import com.hackathonpraktika.DevManagementSystem.model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectsRepository extends JpaRepository<Projects,Long> {
}
