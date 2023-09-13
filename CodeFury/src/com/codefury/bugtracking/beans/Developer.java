package com.codefury.bugtracking.beans;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a Developer who is an employee and may be associated with a project.
 */
public class Developer extends Employee {
    private Optional<Integer> projectId;

    /**
     * Constructor to create a Developer with a name and email.
     *
     * @param name  The name of the developer.
     * @param email The email address of the developer.
     */
    public Developer(String name, String email) {
        super(name);
        super.setEmail(email);
    }

    /**
     * Constructor to create a Developer with a name, email, and date of joining.
     *
     * @param name           The name of the developer.
     * @param email          The email address of the developer.
     * @param dateOfJoining  The date when the developer joined the organization.
     */
    public Developer(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    /**
     * Get the optional project ID associated with the developer.
     *
     * @return An optional containing the project ID if available, or an empty optional.
     */
    public Optional<Integer> getProjectId() {
        return projectId;
    }

    /**
     * Set the optional project ID associated with the developer.
     *
     * @param projectId An optional containing the project ID if available, or an empty optional.
     */
    public void setProjectId(Optional<Integer> projectId) {
        this.projectId = projectId;
    }

    /**
     * Checks if this Developer object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Developer developer = (Developer) o;
        return Objects.equals(projectId, developer.projectId);
    }

    /**
     * Generates a hash code for this Developer object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectId);
    }

    /**
     * Returns a string representation of this Developer object.
     *
     * @return A string containing the Developer's fields and their values.
     */
    @Override
    public String toString() {
        return "Developer{" +
                "projectId=" + projectId +
                '}' + super.toString();
    }
}
