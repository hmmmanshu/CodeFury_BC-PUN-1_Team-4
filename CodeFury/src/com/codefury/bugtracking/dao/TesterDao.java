package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;
import java.util.List;

public interface TesterDao {
    List<Project> getProjectsList(int testerId) throws SQLException;

    void addNewBug(Bug bug) throws SQLException;

    List<Bug> getBugsList(int projectId) throws SQLException;
}
