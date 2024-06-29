package com.hackathonpraktika.DevManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DevSkill")
@Data
public class DevSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DevSkillId")
    private Long devSkillId;

    @Column(name = "SkillName", nullable = false)
    private String skillName;

    @Column(name = "SkillDescription")
    private String skillDescription;

}
