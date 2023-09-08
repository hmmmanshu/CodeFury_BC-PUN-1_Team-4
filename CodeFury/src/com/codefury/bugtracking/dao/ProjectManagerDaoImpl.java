package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.Role;

import java.sql.SQLException;
import java.util.List;

public class ProjectManagerDaoImpl implements ProjectManagerDao {
    @Override
    public void addNewProject(Project project) throws SQLException {

    }

    @Override
    public List<Project> getProjectsList() throws SQLException {

        return null;
    }

    @Override
    public void closeBug(int bugId) throws SQLException {

    }

    @Override
    public void changeProjectStatus(int projectId) throws SQLException {

    }

    @Override
    public void addEmployeeToProject(int projectId, int employeeId, Role role) throws SQLException {

    }
}
