package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;

public interface DeveloperDao {
    void markBugForClosing(int bugId, String remarks, int developerId);

    Developer getDeveloperObject(int developerId) throws SQLException;

    Project getProjectObject(int projectId) throws SQLException;

    Bug getBugObject(int bugId) throws SQLException;
}
