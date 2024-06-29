package com.hackathonpraktika.DevManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "PersonProject")
@IdClass(PersonProjectId.class)
@Data
public class PersonProject {

    @Id
    @Column(name = "PersonId")
    private Long personId;

    @Id
    @Column(name = "ProjectId")
    private Long projectId;

    @ManyToOne
    @JoinColumn(name = "PersonId", insertable = false, updatable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "ProjectId", insertable = false, updatable = false)
    private Projects projects;

}
