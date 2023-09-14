package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.ProjectStatus;
import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.dao.ProjectManagerDao;
import com.codefury.bugtracking.dao.ProjectManagerDaoImpl;
import com.codefury.bugtracking.exceptions.*;

import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the Project Manager Service interface that provides functionality for managing projects.
 */
public class ProjectManagerServiceImpl implements ProjectManagerService {
    private ProjectManagerDao projectManagerDao;
    private final int projectManagerId;

    /**
     * Constructs a new ProjectManagerServiceImpl instance with the given project manager ID.
     *
     * @param projectManagerId The ID of the project manager.
     */
    public ProjectManagerServiceImpl(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    /**
     * Adds a new project to the system.
     *
     * @param project The project to be added.
     * @throws CouldNotAddProjectException If there is an error while adding the project.
     */
    @Override
    public void addNewProject(Project project) throws CouldNotAddProjectException {
        projectManagerDao = new ProjectManagerDaoImpl(this.projectManagerId);
        try {
            projectManagerDao.addNewProject(project);
        } catch (SQLException e) {
            throw new CouldNotAddProjectException("Unable to add project : " + e.getMessage());
        }
    }

    /**
     * Retrieves a list of all projects in the system.
     *
     * @return A list of projects.
     * @throws CouldNotGetProjectsListException If there is an error while retrieving the project list.
     */
    @Override
    public List<Project> getProjectsList() throws CouldNotGetProjectsListException {
        projectManagerDao = new ProjectManagerDaoImpl(this.projectManagerId);
        try {
            return projectManagerDao.getProjectsList();
        } catch (SQLException e) {
            throw new CouldNotGetProjectsListException("Could not get projects list : " + e.getMessage());
        }
    }

    /**
     * Closes a bug with the given bug ID.
     *
     * @param bugId The ID of the bug to be closed.
     * @throws CouldNotCloseBugException If there is an error while closing the bug.
     */
    @Override
    public void closeBug(int bugId) throws CouldNotCloseBugException {
        projectManagerDao = new ProjectManagerDaoImpl(this.projectManagerId);
        try {
            projectManagerDao.closeBug(bugId);
        } catch (SQLException e) {
            throw new CouldNotCloseBugException("Could Not Close Bug : " + e.getMessage());
        }
    }

    /**
     * Changes the status of a project with the given project ID.
     *
     * @param projectId The ID of the project for which the status is to be changed.
     * @throws CouldNotChangeProjectStatus If there is an error while changing the project status.
     */
    @Override
    public void changeProjectStatus(int projectId) throws CouldNotChangeProjectStatus {
        projectManagerDao = new ProjectManagerDaoImpl(this.projectManagerId);
        try {
            Project project = projectManagerDao.getProjectsList()
                    .stream()
                    .filter(project2 -> project2.getProjectId() == projectId)
                    .findFirst()
                    .orElseThrow(() -> new CouldNotChangeProjectStatus("Could not change project status"));
            if (project.getProjectStatus() == ProjectStatus.IN_PROGRESS) {
                projectManagerDao.changeProjectStatus(projectId, ProjectStatus.COMPLETED);
            } else {
                projectManagerDao.changeProjectStatus(projectId, ProjectStatus.IN_PROGRESS);
            }
        } catch (SQLException e) {
            throw new CouldNotChangeProjectStatus("Could not change project status : " + e.getMessage());
        }
    }

    /**
     * Adds an employee to a project with the specified role.
     *
     * @param projectId   The ID of the project to which the employee is to be added.
     * @param employeeId  The ID of the employee to be added to the project.
     * @param role        The role of the employee in the project.
     * @throws CouldNotAddEmployeeException If there is an error while adding the employee to the project.
     */
    @Override
    public void addEmployeeToProject(int projectId, int employeeId, Role role) throws CouldNotAddEmployeeException {
        projectManagerDao = new ProjectManagerDaoImpl(this.projectManagerId);
        try {
            projectManagerDao.addEmployeeToProject(projectId, employeeId, role);
        } catch (SQLException e) {
            throw new CouldNotAddEmployeeException("Could not add employee : " + e.getMessage());
        }
    }
}
