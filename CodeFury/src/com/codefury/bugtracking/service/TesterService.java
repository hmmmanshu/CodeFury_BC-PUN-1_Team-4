package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.exceptions.CantRaiseBugInCurrentProjectException;
import com.codefury.bugtracking.exceptions.CouldNotGetBugsList;
import com.codefury.bugtracking.exceptions.CouldNotGetProjectsListException;

import java.util.List;

/**
 * Interface for the TesterService class.
 */
public interface TesterService {

    /**
     * Returns a list of all projects.
     *
     * @return A list of all projects.
     * @throws CouldNotGetProjectsListException If the list of projects could not be retrieved.
     */
    List<Project> getProjectsList() throws CouldNotGetProjectsListException;

    /**
     * Raises a new bug in the given project.
     *
     * @param projectId        The ID of the project in which to raise the bug.
     * @param bugTitle         The title of the bug.
     * @param bugDescription   The description of the bug.
     * @param bugSeverityLevel The severity level of the bug.
     * @throws CantRaiseBugInCurrentProjectException If the bug could not be raised in the given project.
     */
    void raiseNewBug(int projectId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel) throws CantRaiseBugInCurrentProjectException;

    /**
     * Returns a list of all bugs in the given project.
     *
     * @param projectId The ID of the project for which to get the list of bugs.
     * @return A list of all bugs in the given project.
     * @throws CouldNotGetBugsList If the list of bugs could not be retrieved.
     */
    List<Bug> getBugsList(int projectId) throws CouldNotGetBugsList;
}
