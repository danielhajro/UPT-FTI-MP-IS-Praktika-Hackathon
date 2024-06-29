package com.hackathonpraktika.DevManagementSystem.repositories;

import com.hackathonpraktika.DevManagementSystem.model.DevSkillExp;
import com.hackathonpraktika.DevManagementSystem.model.DevSkillExpId;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DevSkillExpRepository extends JpaRepository<DevSkillExp, DevSkillExpId> {

    @Query(value = "SELECT dse.* FROM dev_skill_exp dse " +
            "JOIN dev_skill ds ON dse.dev_skill_id = ds.dev_skill_id " +
            "WHERE LOWER(ds.skill_name) = LOWER(:skillName)", nativeQuery = true)
    List<DevSkillExp> findBySkillName(@Param("skillName") String skillName);

    @Query(value = "SELECT dse.* FROM dev_skill_exp dse " +
            "JOIN dev_skill ds ON dse.dev_skill_id = ds.dev_skill_id " +
            "WHERE LOWER(ds.skill_name) = LOWER(:skillName) " +
            "AND dse.year_of_exp >= :experience", nativeQuery = true)
    List<DevSkillExp> findBySkillAndExperience(@Param("skillName") String skillName, @Param("experience") int experience);
    @Transactional
    @Modifying
    void deleteByPersonId(Long personId);
}
