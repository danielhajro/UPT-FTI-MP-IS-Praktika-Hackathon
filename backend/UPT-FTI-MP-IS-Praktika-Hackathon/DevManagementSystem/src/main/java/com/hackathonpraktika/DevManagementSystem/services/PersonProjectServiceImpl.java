package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.model.Person;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonProjectRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PersonProjectServiceImpl implements PersonProjectService{
    @Autowired
    private PersonProjectRepository personProjectRepository;

    @Autowired
    private PersonRepository personRepository;
    @Override
    public List<Person> findPersonByProjectId(Long projectId) {
        return personRepository.findAllDevelopersByProjectIdNativeQuery(projectId);
    }
}
