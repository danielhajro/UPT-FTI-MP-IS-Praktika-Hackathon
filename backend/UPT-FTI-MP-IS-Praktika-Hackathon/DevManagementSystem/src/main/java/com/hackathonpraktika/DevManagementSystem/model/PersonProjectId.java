package com.hackathonpraktika.DevManagementSystem.model;

import java.io.Serializable;
import java.util.Objects;

public class PersonProjectId implements Serializable {

    private Long personId;
    private Long projectId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonProjectId that = (PersonProjectId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(projectId, that.projectId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, projectId);
    }
}
