package com.hackathonpraktika.DevManagementSystem.model;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name = "Projects")
@Data
public class Projects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProjectId")
    private Long projectId;

    @Column(name = "ProjectName", nullable = false)
    private String projectName;

    @Column(name = "Description")
    private String description;

}