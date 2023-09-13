package com.codefury.bugtracking.beans;

import java.util.Date;
import java.util.Objects;

/**
 * Represents a Bug in a bug tracking system.
 */
public class Bug {
    private int bugId;
    private static int bugIdCounter = 1;
    private String bugHeading;
    private String description;
    private int projectId;
    private int createdBy;
    private int assignedTo;
    private Date openDate;
    private String markForClosing;
    private int closedBy;
    private Date closedOn;
    private BugStatus bugStatus;
    private SeverityLevel severityLevel;

    /**
     * Constructor to initialize a Bug object.
     *
     * @param bugHeading    The heading or title of the bug.
     * @param description   A description of the bug.
     * @param projectId     The ID of the project to which the bug belongs.
     * @param createdBy     The ID of the user who created the bug.
     * @param assignedTo    The ID of the user to whom the bug is assigned.
     * @param openDate      The date when the bug was opened.
     * @param bugStatus     The status of the bug (e.g., Open, Closed, In Progress).
     * @param severityLevel The severity level of the bug (e.g., High, Medium, Low).
     */
    public Bug(String bugHeading, String description, int projectId, int createdBy, int assignedTo, Date openDate, BugStatus bugStatus, SeverityLevel severityLevel) {
        this.bugId = bugIdCounter++;
        this.bugHeading = bugHeading;
        this.description = description;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.assignedTo = assignedTo;
        this.openDate = openDate;
        this.bugStatus = bugStatus;
        this.severityLevel = severityLevel;
    }

    // Getters and setters for the Bug class fields

    /**
     * Get the unique identifier of the bug.
     *
     * @return The bug ID.
     */
    public int getBugId() {
        return bugId;
    }

    /**
     * Set the unique identifier of the bug.
     *
     * @param bugId The bug ID to set.
     */
    public void setBugId(int bugId) {
        this.bugId = bugId;
    }

    // (Repeat the above two sets of methods for other fields...)

    // Equals, hashCode, and toString methods

    /**
     * Checks if this Bug object is equal to another object.
     *
     * @param o The object to compare to.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return bugId == bug.bugId && projectId == bug.projectId && createdBy == bug.createdBy && assignedTo == bug.assignedTo && Objects.equals(bugHeading, bug.bugHeading) && Objects.equals(description, bug.description) && Objects.equals(openDate, bug.openDate) && Objects.equals(closedOn, bug.closedOn) && bugStatus == bug.bugStatus && severityLevel == bug.severityLevel;
    }

    /**
     * Generates a hash code for this Bug object.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(bugId, bugHeading, description, projectId, createdBy, assignedTo, openDate, markForClosing, closedBy, closedOn, bugStatus, severityLevel);
    }

    public static int getBugIdCounter() {
        return bugIdCounter;
    }

    public static void setBugIdCounter(int bugIdCounter) {
        Bug.bugIdCounter = bugIdCounter;
    }

    public String getBugHeading() {
        return bugHeading;
    }

    public void setBugHeading(String bugHeading) {
        this.bugHeading = bugHeading;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(int assignedTo) {
        this.assignedTo = assignedTo;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }

    public String getMarkForClosing() {
        return markForClosing;
    }

    public void setMarkForClosing(String markForClosing) {
        this.markForClosing = markForClosing;
    }

    public int getClosedBy() {
        return closedBy;
    }

    public void setClosedBy(int closedBy) {
        this.closedBy = closedBy;
    }

    public Date getClosedOn() {
        return closedOn;
    }

    public void setClosedOn(Date closedOn) {
        this.closedOn = closedOn;
    }

    public BugStatus getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    /**
     * Returns a string representation of this Bug object.
     *
     * @return A string containing the Bug's fields and their values.
     */
    @Override
    public String toString() {
        return "Bug{" +
                "bugId=" + bugId +
                ", bugHeading='" + bugHeading + '\'' +
                ", description='" + description + '\'' +
                ", projectId=" + projectId +
                ", createdBy=" + createdBy +
                ", assignedTo=" + assignedTo +
                ", openDate=" + openDate +
                ", markForClosing='" + markForClosing + '\'' +
                ", closedBy=" + closedBy +
                ", closedOn=" + closedOn +
                ", bugStatus=" + bugStatus +
                ", severityLevel=" + severityLevel +
                '}';
    }
}
