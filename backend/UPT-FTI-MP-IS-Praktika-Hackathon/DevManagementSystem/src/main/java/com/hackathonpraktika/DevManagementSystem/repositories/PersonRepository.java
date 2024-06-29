package com.hackathonpraktika.DevManagementSystem.repositories;

import com.hackathonpraktika.DevManagementSystem.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT * FROM Person WHERE LOWER(name) = LOWER(:name)", nativeQuery = true)
    List<Person> findByNameIgnoreCase(@Param("name") String name);

    @Query(value = "SELECT p.* FROM Person p JOIN DevSkillExp dse ON p.PersonId = dse.PersonId WHERE dse.YearOfExp >= :years", nativeQuery = true)
    List<Person> findByExperience(@Param("years") int years);

    @Query(value = "SELECT p.* FROM Person p JOIN PersonProject pp ON p.PersonId = pp.PersonId WHERE pp.ProjectId = :projectId", nativeQuery = true)
    List<Person> findAllDevelopersByProjectIdNativeQuery(@Param("projectId") Long projectId);
}
