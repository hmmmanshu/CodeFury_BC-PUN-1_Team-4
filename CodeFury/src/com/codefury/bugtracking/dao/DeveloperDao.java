package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;

public interface DeveloperDao {
    void getBugsList(int projectId);

    void markBugForClosing(int bugId, String remarks, int developerId);

    Developer getDeveloperObject(int developerId);

    Project getProjectObject(int projectId);

    Bug getBugObject(int bugId);
}
