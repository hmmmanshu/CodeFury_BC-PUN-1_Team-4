package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;

import java.util.List;

public class TesterServiceImpl implements TesterService{
    @Override
    public List<Project> getProjectsList() {

        return null;
    }

    @Override
    public void raiseNewBug(int bugId, int testerId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel) {

    }
}
