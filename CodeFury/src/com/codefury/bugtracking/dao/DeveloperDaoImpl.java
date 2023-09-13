package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;

public class DeveloperDaoImpl implements DeveloperDao {

    @Override
    public void getBugsList(int projectId) {

    }

    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) {

    }

    @Override
    public Developer getDeveloperObject(int developerId) {
        return null;
    }

    @Override
    public Project getProjectObject(int projectId) {
        return null;
    }

    @Override
    public Bug getBugObject(int bugId) {
        return null;
    }
}
