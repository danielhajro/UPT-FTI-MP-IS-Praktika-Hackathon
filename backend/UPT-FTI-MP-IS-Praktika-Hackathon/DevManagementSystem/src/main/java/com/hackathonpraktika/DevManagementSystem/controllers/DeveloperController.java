package com.hackathonpraktika.DevManagementSystem.controllers;

import com.hackathonpraktika.DevManagementSystem.dto.PersonDto;
import com.hackathonpraktika.DevManagementSystem.model.Person;
import com.hackathonpraktika.DevManagementSystem.services.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/developers")
public class DeveloperController {
    @Autowired
    private PersonService personService;

    @PostMapping("/addDeveloper")
    public ResponseEntity<String> addDeveloper(@Valid @RequestBody PersonDto personDto) {
        personService.addDeveloper(personDto);
        return ResponseEntity.ok("Developer profile added successfully");
    }

    @PutMapping("/updateDeveloper/{id}")
    public ResponseEntity<String> updateDeveloper(@PathVariable Long id, @Valid @RequestBody PersonDto personDto) {
        personService.updateDeveloper(id, personDto);
        return ResponseEntity.ok("Developer profile updated successfully");
    }

    @GetMapping("/search")
    public List<Person> searchPersons(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String skill,
            @RequestParam(required = false) Integer experience) {
        if (name != null) {
            return personService.searchPersonsByName(name);
        } else if (skill != null && experience != null) {
            return personService.searchPersonsBySkillAndExperience(skill, experience);
        } else if (skill != null) {
            return personService.searchPersonsBySkill(skill);
        } else if (experience != null) {
            return personService.searchPersonsByExperience(experience);
        } else {
            return personService.getAllPersons();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable Long id){
        personService.deletePerson(id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}

