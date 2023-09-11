package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.exceptions.*;

import java.util.List;

public interface ProjectManagerService {
    void addNewProject(Project project) throws CouldNotAddProjectException;

    List<Project> getProjectsList() throws CouldNotGetProjectsListException;

    void closeBug(int bugId) throws CouldNotCloseBugException;

    void changeProjectStatus(int projectId) throws CouldNotChangeProjectStatus;

    void addEmployeeToProject(int projectId, int employeeId, Role role) throws CouldNotAddEmployeeException;
}
