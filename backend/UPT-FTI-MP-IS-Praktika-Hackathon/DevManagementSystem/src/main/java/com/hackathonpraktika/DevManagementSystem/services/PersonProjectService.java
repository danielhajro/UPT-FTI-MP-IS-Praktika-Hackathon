package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.model.Person;

import java.util.*;

public interface PersonProjectService {
    List<Person> findPersonByProjectId(Long projectId);
}
