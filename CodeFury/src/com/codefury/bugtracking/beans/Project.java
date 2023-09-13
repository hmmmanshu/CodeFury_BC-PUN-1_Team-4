package com.codefury.bugtracking.beans;

import java.util.List;
import java.util.Objects;

public class Project {
    private int projectId;
    private static int projectIdCounter = 1;
    private String projectName;
    private int projectManagerId;
    private int testerId;
    private ProjectStatus projectStatus;
    private List<Integer> bugsList;

    public Project(String projectName, int projectManagerId) {
        this.projectId = projectIdCounter++;
        this.projectName = projectName;
        this.projectManagerId = projectManagerId;
    }

    public Project(String projectName, int projectManagerId, List<Integer> developers, int testerId, ProjectStatus projectStatus, List<Integer> bugsList) {
        this.projectId = projectIdCounter++;
        this.projectName = projectName;
        this.projectManagerId = projectManagerId;
        this.testerId = testerId;
        this.projectStatus = projectStatus;
        this.bugsList = bugsList;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public int getTesterId() {
        return testerId;
    }

    public void setTesterId(int testerId) {
        this.testerId = testerId;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    public List<Integer> getBugsList() {
        return bugsList;
    }

    public void setBugsList(List<Integer> bugsList) {
        this.bugsList = bugsList;
    }

    public void addBug(Bug bug){
        this.bugsList.add(bug.getBugId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return projectId == project.projectId && projectManagerId == project.projectManagerId && testerId == project.testerId && Objects.equals(projectName, project.projectName) && projectStatus == project.projectStatus && Objects.equals(bugsList, project.bugsList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName, projectManagerId, testerId, projectStatus, bugsList);
    }

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
