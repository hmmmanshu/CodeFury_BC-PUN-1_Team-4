package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.dao.ProjectManagerDao;
import com.codefury.bugtracking.dao.ProjectManagerDaoImpl;
import com.codefury.bugtracking.exceptions.CouldNotAddProjectException;

import java.sql.SQLException;
import java.util.List;

public class ProjectManagerServiceImpl implements ProjectManagerService {
    private ProjectManagerDao projectManagerDao;

    @Override
    public void addNewProject(Project project) throws CouldNotAddProjectException {
        projectManagerDao = new ProjectManagerDaoImpl();
        try {
            projectManagerDao.addNewProject(project);
        }catch (SQLException e){
            throw new CouldNotAddProjectException("Unable to add project");
        }
    }

    @Override
    public List<Project> getProjectsList() {

        return null;
    }

    @Override
    public void closeBug(int bugId) {

    }
}
