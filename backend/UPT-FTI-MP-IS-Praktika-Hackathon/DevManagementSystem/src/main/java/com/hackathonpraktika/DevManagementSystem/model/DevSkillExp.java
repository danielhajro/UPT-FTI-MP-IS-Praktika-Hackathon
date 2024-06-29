package com.hackathonpraktika.DevManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "DevSkillExp")
@IdClass(DevSkillExpId.class)
@Data
public class DevSkillExp {

    @Id
    @Column(name = "PersonId")
    private Long personId;

    @Id
    @Column(name = "DevSkillId")
    private Long devSkillId;

    @Column(name = "YearOfExp", nullable = false)
    private int yearOfExp;

    @ManyToOne
    @JoinColumn(name = "PersonId", insertable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "devSkillId", insertable = false, updatable = false)
    private DevSkill devSkill;

}
