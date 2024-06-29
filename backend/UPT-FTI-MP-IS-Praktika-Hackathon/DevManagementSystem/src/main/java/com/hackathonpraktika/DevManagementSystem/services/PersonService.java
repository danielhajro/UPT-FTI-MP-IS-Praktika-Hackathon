package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.dto.PersonDto;
import com.hackathonpraktika.DevManagementSystem.model.Person;
import jakarta.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PersonService{
    List<Person> getAllPersons();
    List<Person> searchPersonsByName(String name);
    List<Person> searchPersonsBySkill(String skillName);
    List<Person> searchPersonsByExperience(int experience);
    List<Person> searchPersonsBySkillAndExperience(String skillName, int experience);
    void addDeveloper(PersonDto personDto);
    void updateDeveloper(Long id, PersonDto personDto);
    void deletePerson(Long personId);
}
