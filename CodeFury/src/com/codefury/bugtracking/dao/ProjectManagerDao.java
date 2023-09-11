package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.ProjectStatus;
import com.codefury.bugtracking.beans.Role;

import java.sql.SQLException;
import java.util.List;

public interface ProjectManagerDao {
    void addNewProject(Project project) throws SQLException;

    List<Project> getProjectsList() throws SQLException;

    void closeBug(int bugId) throws SQLException;

    void changeProjectStatus(int projectId, ProjectStatus completed) throws SQLException;

    void addEmployeeToProject(int projectId, int employeeId, Role role) throws SQLException;
}
