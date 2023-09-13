package com.codefury.bugtracking.beans;

import java.util.List;
import java.util.Objects;

/**
 * Represents a Project in a bug tracking system.
 */
public class Project {
    private int projectId;
    private static int projectIdCounter = 1;
    private String projectName;
    private int projectManagerId;
    private int testerId;
    private ProjectStatus projectStatus;
    private List<Integer> bugsList;

    /**
     * Constructor to create a Project with a project name and project manager ID.
     *
     * @param projectName      The name of the project.
     * @param projectManagerId The ID of the project manager.
     */
    public Project(String projectName, int projectManagerId) {
        this.projectId = projectIdCounter++;
        this.projectName = projectName;
        this.projectManagerId = projectManagerId;
    }

    /**
     * Constructor to create a Project with various parameters.
     *
     * @param projectName      The name of the project.
     * @param projectManagerId The ID of the project manager.
     * @param developers       List of developer IDs.
     * @param testerId         The ID of the tester.
     * @param projectStatus    The status of the project.
     * @param bugsList         List of bug IDs associated with the project.
     */
    public Project(String projectName, int projectManagerId, List<Integer> developers, int testerId, ProjectStatus projectStatus, List<Integer> bugsList) {
        this.projectId = projectIdCounter++;
        this.projectName = projectName;
        this.projectManagerId = projectManagerId;
        this.testerId = testerId;
        this.projectStatus = projectStatus;
        this.bugsList = bugsList;
    }

    // Getters and setters for the Project class fields

    /**
     * Get the unique identifier of the project.
     *
     * @return The project ID.
     */
    public int getProjectId() {
        return projectId;
    }

    /**
     * Set the unique identifier of the project.
     *
     * @param projectId The project ID to set.
     */
    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    // (Repeat the above two sets of methods for other fields...)

    /**
     * Add a bug to the project's bug list.
     *
     * @param bug The Bug object to add.
     */
    public void addBug(Bug bug) {
        this.bugsList.add(bug.getBugId());
    }

    // Equals, hashCode, and toString methods

    /**
     * Checks if this Project object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId && projectManagerId == project.projectManagerId && testerId == project.testerId && Objects.equals(projectName, project.projectName) && projectStatus == project.projectStatus && Objects.equals(bugsList, project.bugsList);
    }

    /**
     * Generates a hash code for this Project object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectManagerId, testerId, projectStatus, bugsList);
    }

    /**
     * Returns a string representation of this Project object.
     *
     * @return A string containing the Project's fields and their values.
     */
    @Override
    public String toString() {
        return "Project{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectManagerId=" + projectManagerId +
                ", testerId=" + testerId +
                ", projectStatus=" + projectStatus +
                ", bugsList=" + bugsList +
                '}';
    }
}
