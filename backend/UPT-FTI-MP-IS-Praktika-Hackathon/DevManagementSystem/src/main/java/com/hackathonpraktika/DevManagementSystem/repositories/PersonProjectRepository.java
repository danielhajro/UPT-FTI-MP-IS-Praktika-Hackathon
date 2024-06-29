package com.hackathonpraktika.DevManagementSystem.repositories;

import com.hackathonpraktika.DevManagementSystem.model.PersonProject;
import com.hackathonpraktika.DevManagementSystem.model.PersonProjectId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PersonProjectRepository extends JpaRepository<PersonProject, PersonProjectId> {
    List<PersonProject> findByProjectId(Long projectId);


}