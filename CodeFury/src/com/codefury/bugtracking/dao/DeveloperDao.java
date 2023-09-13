package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;

/**
 * An interface representing data access operations for developers in the Bug Tracking system.
 */
public interface DeveloperDao {
    /**
     * Marks a bug for closing by updating its status and adding remarks.
     *
     * @param bugId       The ID of the bug to mark for closing.
     * @param remarks     Remarks for closing the bug.
     * @param developerId The ID of the developer marking the bug for closing.
     */
    void markBugForClosing(int bugId, String remarks, int developerId);

    /**
     * Retrieves a Developer object by their ID.
     *
     * @param developerId The ID of the developer.
     * @return A Developer object representing the developer with the specified ID.
     * @throws SQLException If there is an issue with the database operation.
     */
    Developer getDeveloperObject(int developerId) throws SQLException;

    /**
     * Retrieves a Project object by its ID.
     *
     * @param projectId The ID of the project.
     * @return A Project object representing the project with the specified ID.
     * @throws SQLException If there is an issue with the database operation.
     */
    Project getProjectObject(int projectId) throws SQLException;

    /**
     * Retrieves a Bug object by its ID.
     *
     * @param bugId The ID of the bug.
     * @return A Bug object representing the bug with the specified ID.
     * @throws SQLException If there is an issue with the database operation.
     */
    Bug getBugObject(int bugId) throws SQLException;
}
