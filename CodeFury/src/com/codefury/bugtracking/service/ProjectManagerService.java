package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.exceptions.CouldNotAddProjectException;

import java.util.List;

public interface ProjectManagerService {
    void addNewProject(Project project) throws CouldNotAddProjectException;
    List<Project> getProjectsList();
    void closeBug(int bugId);
}
