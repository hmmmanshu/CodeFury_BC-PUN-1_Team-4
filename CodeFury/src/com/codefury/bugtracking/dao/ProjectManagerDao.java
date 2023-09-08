package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;

public interface ProjectManagerDao {
    void addNewProject(Project project) throws SQLException;
    void getProjectsList();
    void closeBug();
}
