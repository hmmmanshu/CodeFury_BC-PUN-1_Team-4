package com.codefury.bugtracking.beans;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class Developer extends Employee {
    private Optional<Integer> projectId;

    public Developer(String name, String email) {
        super(name);
        super.setEmail(email);
    }

    public Developer(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    public Optional<Integer> getProjectId() {
        return projectId;
    }

    public void setProjectId(Optional<Integer> projectId) {
        this.projectId = projectId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return projectId == developer.projectId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    @Override
    public String toString() {
        return "Developer{" +
                "projectId=" + projectId +
                '}' + super.toString();
    }
}
