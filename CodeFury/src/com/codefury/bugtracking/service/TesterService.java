package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.exceptions.CantRaiseBugInCurrentProjectException;
import com.codefury.bugtracking.exceptions.CouldNotGetBugsList;
import com.codefury.bugtracking.exceptions.CouldNotGetProjectsListException;

import java.util.List;

public interface TesterService {
    List<Project> getProjectsList() throws CouldNotGetProjectsListException;

    void raiseNewBug(int projectId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel) throws CantRaiseBugInCurrentProjectException;

    List<Bug> getBugsList(int projectId) throws CouldNotGetBugsList;
}
