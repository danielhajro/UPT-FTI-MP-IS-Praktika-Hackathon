package com.hackathonpraktika.DevManagementSystem.model;

import java.io.Serializable;
import java.util.Objects;

public class DevSkillExpId implements Serializable {

    private Long personId;
    private Long devSkillId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DevSkillExpId that = (DevSkillExpId) o;
        return Objects.equals(personId, that.personId) && Objects.equals(devSkillId, that.devSkillId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(personId, devSkillId);
    }
}
