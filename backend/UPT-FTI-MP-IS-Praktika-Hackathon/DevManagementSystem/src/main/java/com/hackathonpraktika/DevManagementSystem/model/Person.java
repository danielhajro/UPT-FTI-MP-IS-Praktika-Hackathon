package com.hackathonpraktika.DevManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.*;


@Entity
@Table(name = "Person")
@Data
public class Person {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "PersonId")
        private Long personId;

        @NotBlank
        @Size(max = 100)
        @Column(name = "Name")
        private String name;

        @NotBlank
        @Size(max = 100)
        @Column(name = "Surname")
        private String surname;

        @NotBlank
        @Size(max = 50)
        @Column(name = "Role")
        private String role;

        @NotBlank
        @Email
        @Size(max = 100)
        @Column(name = "Email")
        private String email;

        @Column(name = "profilePicture")
        private String profilePicture;
}


