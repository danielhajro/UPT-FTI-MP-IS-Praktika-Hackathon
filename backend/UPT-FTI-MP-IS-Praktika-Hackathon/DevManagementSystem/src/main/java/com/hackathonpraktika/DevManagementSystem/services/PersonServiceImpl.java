package com.hackathonpraktika.DevManagementSystem.services;

import com.hackathonpraktika.DevManagementSystem.dto.DevSkillExpDTO;
import com.hackathonpraktika.DevManagementSystem.dto.PersonDto;
import com.hackathonpraktika.DevManagementSystem.model.DevSkill;
import com.hackathonpraktika.DevManagementSystem.model.DevSkillExp;
import com.hackathonpraktika.DevManagementSystem.model.Person;
import com.hackathonpraktika.DevManagementSystem.repositories.DevSkillExpRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.DevSkillRepository;
import com.hackathonpraktika.DevManagementSystem.repositories.PersonRepository;
import com.hackathonpraktika.DevManagementSystem.utilities.GlobalConstants;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DevSkillExpRepository devSkillExpRepository;

    @Autowired
    private DevSkillRepository devSkillRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository, DevSkillExpRepository devSkillExpRepository, DevSkillRepository devSkillRepository) {
        this.personRepository = personRepository;
        this.devSkillExpRepository = devSkillExpRepository;
        this.devSkillRepository = devSkillRepository;
    }


    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
    @Override
    public List<Person> searchPersonsByName(String name) {
         return personRepository.findByNameIgnoreCase(name);
    }

    @Override
    public List<Person> searchPersonsBySkill(String skillName) {
        List<DevSkillExp> devSkillExps = devSkillExpRepository.findBySkillName(skillName);
        return devSkillExps.stream()
                .map(DevSkillExp::getPerson)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> searchPersonsByExperience(int experience) {
        return personRepository.findByExperience(experience);
    }
    @Override
    public List<Person> searchPersonsBySkillAndExperience(String skillName, int experience) {
        List<DevSkillExp> devSkillExps = devSkillExpRepository.findBySkillAndExperience(skillName, experience);
        return devSkillExps.stream()
                .map(DevSkillExp::getPerson)
                .collect(Collectors.toList());
    }
    @Override
    @Transactional
    public void addDeveloper(PersonDto personDto) {

        Person person = new Person();
        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        person.setRole(GlobalConstants.DEVELOPER);
        person.setEmail(personDto.getEmail());
        person.setProfilePicture(personDto.getBase64ProfilePicture());
        personRepository.save(person);

        processSkillsAndExperience(person.getPersonId(), personDto.getDevSkillExpList());
    }

    @Override
    @Transactional
    public void updateDeveloper(Long id, PersonDto personDto) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Developer not found"));

        person.setName(personDto.getName());
        person.setSurname(personDto.getSurname());
        person.setEmail(personDto.getEmail());
        person.setProfilePicture(personDto.getBase64ProfilePicture());
        personRepository.save(person);

        processSkillsAndExperience(person.getPersonId(), personDto.getDevSkillExpList());
    }

    private void processSkillsAndExperience(Long personId, List<DevSkillExpDTO> devSkillExpList) {
        if (devSkillExpList != null) {
            for (DevSkillExpDTO devSkillExp : devSkillExpList) {
                String skillName = devSkillExp.getSkill();
                Integer experience = devSkillExp.getExperience();

                DevSkill devSkill = devSkillRepository.findBySkillNameIgnoreCase(skillName);
                if (devSkill == null) {
                    devSkill = new DevSkill();
                    devSkill.setSkillName(skillName);
                    devSkillRepository.save(devSkill);
                }

                DevSkillExp devSkillExpItem = new DevSkillExp();
                devSkillExpItem.setPersonId(personId);
                devSkillExpItem.setDevSkillId(devSkill.getDevSkillId());
                devSkillExpItem.setYearOfExp(experience);
                devSkillExpRepository.save(devSkillExpItem);
            }
        }
    }
    @Override
    public void deletePerson(Long personId){
        if(personRepository.existsById(personId)){
            devSkillExpRepository.deleteByPersonId(personId);
            personRepository.deleteById(personId);
        }else{
            throw new RuntimeException("Person not found with id: " + personId );
        }
    }
}



