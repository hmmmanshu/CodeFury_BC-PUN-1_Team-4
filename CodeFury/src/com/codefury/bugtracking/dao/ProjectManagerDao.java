package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.ProjectStatus;

import java.util.List;
import java.sql.SQLException;

/**
 * DAO class to interact with JDBC for ProjectManager objects, used to add or modify rows in the database that relate <br>
 * to the ProjectManager
 */
public interface ProjectManagerDao {
    /**
     * Add a new project to the database
     *
     * @param project project object to be added to the database
     * @throws SQLException can not save object to database
     */
    void addNewProject(Project project) throws SQLException;

    /**
     * Get a list of all the projects that the project manager is managing
     *
     * @return list of projects that the manager is handling
     * @throws SQLException could not fetch projects list from database
     */
    List<Project> getProjectsList() throws SQLException;

    /**
     * Close a bug that has been assigned to the project manager
     *
     * @param bugId the bug id of the bug to be closed
     * @throws SQLException could not change the bug status
     */
    void closeBug(int bugId) throws SQLException;

    /**
     * Change project status to required status
     *
     * @param projectId     project id of the project to be modified
     * @param projectStatus new status of the project
     * @throws SQLException could not modify the project status
     */
    void changeProjectStatus(int projectId, ProjectStatus projectStatus) throws SQLException;

    /**
     * Add a new employee to a project as a developer or tester
     *
     * @param projectId  project id of the project to be modified
     * @param employeeId employee id of the employee to be added
     * @param role       role to be assigned to the new employee
     * @throws SQLException could not modify the project status
     */
    void addEmployeeToProject(int projectId, int employeeId, Role role) throws SQLException;
}
