package com.codefury.bugtracking.beans;

import java.util.Date;
import java.util.Objects;

public class Bug {
    private int bugId;
    private static int bugIdCounter = 1;
    private String bugHeading;
    private String description;
    private int projectId;
    private int createdBy;
    private int assignedTo;
    private Date openDate;
    private int markForClosing;
    private int closedBy;
    private Date closedOn;
    private BugStatus bugStatus;
    private SeverityLevel severityLevel;

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

    public int getBugId() {
        return bugId;
    }

    public void setBugId(int bugId) {
        this.bugId = bugId;
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

    public int getMarkForClosing() {
        return markForClosing;
    }

    public void setMarkForClosing(int markForClosing) {
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

    public BugStatus getStatus() {
        return bugStatus;
    }

    public void setStatus(BugStatus bugStatus) {
        this.bugStatus = bugStatus;
    }

    public SeverityLevel getSeverityLevel() {
        return severityLevel;
    }

    public void setSeverityLevel(SeverityLevel severityLevel) {
        this.severityLevel = severityLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bug bug = (Bug) o;
        return bugId == bug.bugId && projectId == bug.projectId && createdBy == bug.createdBy && assignedTo == bug.assignedTo && markForClosing == bug.markForClosing && closedBy == bug.closedBy && Objects.equals(bugHeading, bug.bugHeading) && Objects.equals(description, bug.description) && Objects.equals(openDate, bug.openDate) && Objects.equals(closedOn, bug.closedOn) && bugStatus == bug.bugStatus && severityLevel == bug.severityLevel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bugId, bugHeading, description, projectId, createdBy, assignedTo, openDate, markForClosing, closedBy, closedOn, bugStatus, severityLevel);
    }

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
                ", markForClosing=" + markForClosing +
                ", closedBy=" + closedBy +
                ", closedOn=" + closedOn +
                ", bugStatus=" + bugStatus +
                ", severityLevel=" + severityLevel +
                '}';
    }
}
