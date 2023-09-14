package com.codefury.bugtracking.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Project Manager, which is a type of Employee with a list of assigned projects.
 */
public class ProjectManager extends Employee {

    private List<Integer> projects;

    /**
     * Constructs a Project Manager with the given name.
     *
     * @param name The name of the Project Manager.
     */
    public ProjectManager(String name) {
        super(name);
        projects = new ArrayList<>();
    }

    /**
     * Constructs a Project Manager with the given name and email.
     *
     * @param name  The name of the Project Manager.
     * @param email The email of the Project Manager.
     */
    public ProjectManager(String name, String email) {
        super(name);
        super.setEmail(email);
    }

    /**
     * Constructs a Project Manager with the given name, email, and date of joining.
     *
     * @param name           The name of the Project Manager.
     * @param email          The email of the Project Manager.
     * @param dateOfJoining  The date of joining of the Project Manager.
     */
    public ProjectManager(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    /**
     * Gets the list of projects assigned to the Project Manager.
     *
     * @return A list of project IDs.
     */
    public List<Integer> getProjects() {
        return projects;
    }

    /**
     * Sets the list of projects assigned to the Project Manager.
     *
     * @param projects The list of project IDs.
     */
    public void setProjects(List<Integer> projects) {
        this.projects = projects;
    }

    /**
     * Checks if two ProjectManager objects are equal based on their projects.
     *
     * @param o The object to compare with.
     * @return True if the objects are equal, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProjectManager that = (ProjectManager) o;
        return Objects.equals(projects, that.projects);
    }

    /**
     * Generates a hash code for the ProjectManager object based on its projects.
     *
     * @return The generated hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projects);
    }

    /**
     * Converts the ProjectManager object to a string representation.
     *
     * @return A string representation of the ProjectManager.
     */
    @Override
    public String toString() {
        return "ProjectManager{" +
                "projects=" + projects +
                '}' + super.toString();
    }
}
