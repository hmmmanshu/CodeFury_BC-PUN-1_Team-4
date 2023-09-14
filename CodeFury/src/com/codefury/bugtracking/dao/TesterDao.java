package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;
import java.util.List;

/**
 * DAO interface for testers
 */
public interface TesterDao {

    /**
     * Gets the list of projects that the tester is assigned to.
     *
     * @param testerId the id of the tester
     * @return a list of projects
     * @throws SQLException if any error occurs while fetching the projects
     */
    List<Project> getProjectsList(int testerId) throws SQLException;

    /**
     * Adds a new bug to the system.
     *
     * @param bug the bug object to be added
     * @throws SQLException if any error occurs while adding the bug
     */
    void addNewBug(Bug bug) throws SQLException;

    /**
     * Gets the list of bugs for a given project.
     *
     * @param projectId the id of the project
     * @return a list of bugs
     * @throws SQLException if any error occurs while fetching the bugs
     */
    List<Bug> getBugsList(int projectId) throws SQLException;
}
