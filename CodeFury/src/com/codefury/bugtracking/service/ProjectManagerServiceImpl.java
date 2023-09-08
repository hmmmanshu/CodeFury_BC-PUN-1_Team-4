package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.ProjectStatus;
import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.dao.ProjectManagerDao;
import com.codefury.bugtracking.dao.ProjectManagerDaoImpl;
import com.codefury.bugtracking.exceptions.*;

import java.sql.SQLException;
import java.util.List;

public class ProjectManagerServiceImpl implements ProjectManagerService {
    private ProjectManagerDao projectManagerDao;

    @Override
    public void addNewProject(Project project) throws CouldNotAddProjectException {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            projectManagerDao.addNewProject(project);
        } catch (SQLException e) {
            throw new CouldNotAddProjectException("Unable to add project : " + e.getMessage());
        }
    }

    @Override
    public List<Project> getProjectsList() throws CouldNotGetProjectsListException {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            return projectManagerDao.getProjectsList();
        } catch (SQLException e) {
            throw new CouldNotGetProjectsListException("Could not get projects list : " + e.getMessage());
        }
    }

    @Override
    public void closeBug(int bugId) throws CouldNotCloseBugException {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            projectManagerDao.closeBug(bugId);
        } catch (SQLException e) {
            throw new CouldNotCloseBugException("Could Not Close Bug : " + e.getMessage());
        }
    }

    @Override
    public void changeProjectStatus(int projectId) throws CouldNotChangeProjectStatus {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            Project project = projectManagerDao.getProjectsList()
                    .stream()
                    .filter(project2 -> project2.getProjectId() == projectId)
                    .findFirst()
                    .orElseThrow(() -> new CouldNotChangeProjectStatus("Could not change project status"));
            if (project.getProjectStatus() == ProjectStatus.IN_PROGRESS) {
                projectManagerDao.changeProjectStatus(projectId, ProjectStatus.COMPLETED);
            } else
                projectManagerDao.changeProjectStatus(projectId, ProjectStatus.IN_PROGRESS);
        } catch (SQLException e) {
            throw new CouldNotChangeProjectStatus("Could not change project status : " + e.getMessage());
        }
    }

    @Override
    public void addEmployeeToProject(int projectId, int employeeId, Role role) throws CouldNotAddEmployeeException {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            projectManagerDao.addEmployeeToProject(projectId, employeeId, role);
        } catch (SQLException e) {
            throw new CouldNotAddEmployeeException("Could not add employee : " + e.getMessage());
        }
    }
}
