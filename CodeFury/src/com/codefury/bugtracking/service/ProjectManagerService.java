package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.exceptions.*;

import java.util.List;

/**
 * This interface defines the operations that can be performed by the Project Manager Service.
 */
public interface ProjectManagerService {
    /**
     * Adds a new project to the system.
     *
     * @param project The project to be added.
     * @throws CouldNotAddProjectException If there is an error while adding the project.
     */
    void addNewProject(Project project) throws CouldNotAddProjectException;

    /**
     * Retrieves a list of all projects in the system.
     *
     * @return A list of projects.
     * @throws CouldNotGetProjectsListException If there is an error while retrieving the project list.
     */
    List<Project> getProjectsList() throws CouldNotGetProjectsListException;

    /**
     * Closes a bug with the given bug ID.
     *
     * @param bugId The ID of the bug to be closed.
     * @throws CouldNotCloseBugException If there is an error while closing the bug.
     */
    void closeBug(int bugId) throws CouldNotCloseBugException;

    /**
     * Changes the status of a project with the given project ID.
     *
     * @param projectId The ID of the project for which the status is to be changed.
     * @throws CouldNotChangeProjectStatus If there is an error while changing the project status.
     */
    void changeProjectStatus(int projectId) throws CouldNotChangeProjectStatus;

    /**
     * Adds an employee to a project with the specified role.
     *
     * @param projectId   The ID of the project to which the employee is to be added.
     * @param employeeId  The ID of the employee to be added to the project.
     * @param role        The role of the employee in the project.
     * @throws CouldNotAddEmployeeException If there is an error while adding the employee to the project.
     */
    void addEmployeeToProject(int projectId, int employeeId, Role role) throws CouldNotAddEmployeeException;
}
