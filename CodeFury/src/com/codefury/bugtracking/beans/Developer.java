package com.codefury.bugtracking.beans;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Developer extends Employee {
    private int projectId;

    public Developer(String name) {
        super(name);
    }

    public Developer(String name, String email) {
        super(name);
        super.setEmail(email);
        LocalDateTime currentDateTime = LocalDateTime.now();
        super.setDateOfJoining(java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    public Developer(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
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
                '}'+super.toString();
    }
}
