package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;

import java.util.List;

public interface TesterService {
    List<Project> getProjectsList();
    void raiseNewBug(int bugId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel);
}
