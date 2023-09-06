package com.codefury.bugtracking.beans;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class ProjectManager extends Employee {

    private List<Integer> projects;

    public ProjectManager(int employeeId, String name) {
        super(name);
        projects = new ArrayList<>();
    }

    public ProjectManager(String name, String email) {
        super(name);
        super.setEmail(email);
        LocalDateTime currentDateTime = LocalDateTime.now();
        super.setDateOfJoining(java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant()));
    }

    public ProjectManager(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    public List<Integer> getProjects() {
        return projects;
    }

    public void setProjects(List<Integer> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectManager that = (ProjectManager) o;
        return Objects.equals(projects, that.projects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projects);
    }

    @Override
    public String toString() {
        return "ProjectManager{" +
                "projects=" + projects +
                '}' + super.toString();
    }
}
