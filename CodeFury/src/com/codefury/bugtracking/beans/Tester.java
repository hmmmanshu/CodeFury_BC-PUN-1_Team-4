package com.codefury.bugtracking.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Represents a Tester who is an employee and may be associated with projects.
 */
public class Tester extends Employee {
    private List<Integer> projects;

    /**
     * Constructor to create a Tester with a name.
     *
     * @param name The name of the tester.
     */
    public Tester(String name) {
        super(name);
        projects = new ArrayList<>();
    }

    /**
     * Constructor to create a Tester with a name and email.
     *
     * @param name  The name of the tester.
     * @param email The email address of the tester.
     */
    public Tester(String name, String email) {
        super(name);
        super.setEmail(email);
    }

    /**
     * Constructor to create a Tester with a name, email, and date of joining.
     *
     * @param name           The name of the tester.
     * @param email          The email address of the tester.
     * @param dateOfJoining  The date when the tester joined the organization.
     */
    public Tester(String name, String email, Date dateOfJoining) {
        super(name);
        super.setEmail(email);
        super.setDateOfJoining(dateOfJoining);
    }

    /**
     * Default constructor for Tester.
     */
    public Tester() {

    }

    /**
     * Get the list of project IDs associated with the tester.
     *
     * @return The list of project IDs.
     */
    public List<Integer> getProjects() {
        return projects;
    }

    /**
     * Set the list of project IDs associated with the tester.
     *
     * @param projects The list of project IDs to set.
     */
    public void setProjects(List<Integer> projects) {
        this.projects = projects;
    }

    /**
     * Checks if this Tester object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tester tester = (Tester) o;
        return Objects.equals(projects, tester.projects);
    }

    /**
     * Generates a hash code for this Tester object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projects);
    }

    /**
     * Returns a string representation of this Tester object.
     *
     * @return A string containing the Tester's fields and their values.
     */
    @Override
    public String toString() {
        return "Tester{" +
                "projects=" + projects +
                '}' + super.toString();
    }
}
