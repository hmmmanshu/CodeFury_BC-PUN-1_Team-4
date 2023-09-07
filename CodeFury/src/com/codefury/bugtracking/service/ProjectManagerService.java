package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;

import java.util.List;

public interface ProjectManagerService {
    void addNewProject(Project project);
    List<Project> getProjectsList();
    void closeBug(int bugId);
}
