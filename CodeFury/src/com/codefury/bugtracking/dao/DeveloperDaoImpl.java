package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.*;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Implementation of the Developer Data Access Object (DAO) interface that provides database operations for developers.
 */
public class DeveloperDaoImpl implements DeveloperDao {
    private final Connection connection;

    /**
     * Constructs a new DeveloperDaoImpl instance and initializes the database connection.
     */
    public DeveloperDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Marks a bug for closing with the given bug ID, remarks, and developer ID.
     *
     * @param bugId       The ID of the bug to mark for closing.
     * @param remarks     Remarks or comments for closing the bug.
     * @param developerId The ID of the developer marking the bug.
     */
    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.MARK_BUG_FOR_CLOSING)) {
            preparedStatement.setString(1, remarks);
            preparedStatement.setInt(2, bugId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Retrieves a Developer object based on the developer's ID.
     *
     * @param developerId The ID of the developer.
     * @return A Developer object.
     * @throws SQLException If there is an error in the SQL operation.
     */
    @Override
    public Developer getDeveloperObject(int developerId) throws SQLException {

        Developer developer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_DEVELOPER_OBJECT)) {
            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int projectId = resultSet.getInt("projectId");
                try (PreparedStatement preparedStatement1 = connection.prepareStatement((Queries.GET_EMPLOYEE_OBJECT))) {
                    preparedStatement.setInt(1, developerId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        String employeeName = resultSet1.getString("employeeName");
                        String email = resultSet1.getString("email");
                        Date dateOfJoining = resultSet1.getDate("dateOfJoining");
                        developer = new Developer(employeeName, email, dateOfJoining);
                    }
                }
            }
            return developer;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Retrieves a Project object based on the project's ID.
     *
     * @param projectId The ID of the project.
     * @return A Project object.
     * @throws SQLException If there is an error in the SQL operation.
     */
    @Override
    public Project getProjectObject(int projectId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECT_OBJECT)) {
            Project project = null;
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isAfterLast()) throw new SQLException("Empty result");

            while (resultSet.next()) {
                String projectTitle = resultSet.getString("projectName");
                int projectManagerId = resultSet.getInt("projectManagerId");
                int testerId = resultSet.getInt("testerId");
                int projectStatusCode = resultSet.getInt("projectStatus");
                ProjectStatus projectStatus = projectStatusCode == ProjectStatus.COMPLETED.ordinal() ? ProjectStatus.COMPLETED : ProjectStatus.IN_PROGRESS;

                // get all the bugs related to the project
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(Queries.GET_BUGS_FOR_PROJECT)) {
                    preparedStatement1.setInt(1, projectId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        int bugId = resultSet1.getInt("bugId");
                        String bugHeading = resultSet1.getString("bugHeading");
                        int createdBy = resultSet1.getInt("createdBy");
                        int assignedTo = resultSet1.getInt("assignedTo");
                        Date openDate = resultSet1.getDate("openDate");
                        String markForClosing = resultSet1.getString("markForClosing");
                        int closedBy = resultSet1.getInt("closedBy");
                        Date closedOn = resultSet1.getDate("closedOn");
                        int bugStatus = resultSet1.getInt("bugStatus");
                        BugStatus bugStatus1;
                        if (bugStatus == BugStatus.OPEN.ordinal())
                            bugStatus1 = BugStatus.OPEN;
                        else
                            bugStatus1 = BugStatus.CLOSED;
                        int bugSeverityLevel = resultSet1.getInt("severityLevel");
                        SeverityLevel severityLevel = getSeverityLevel(bugSeverityLevel);
                        project.addBug(new Bug(bugHeading, bugHeading, projectId, createdBy, assignedTo, openDate, bugStatus1, severityLevel));
                    }
                }

            }
            return project;
        }
    }

    /**
     * Get the severity level of a bug as the ordinal value and convert it back to the SeverityLevel enum.
     *
     * @param bugSeverityLevel The ordinal value of the severity level.
     * @return The SeverityLevel enum value.
     */
    private static SeverityLevel getSeverityLevel(int bugSeverityLevel) {
        SeverityLevel severityLevel;
        if (bugSeverityLevel == SeverityLevel.TRIVIAL.ordinal()) {
            severityLevel = SeverityLevel.TRIVIAL;
        } else if (bugSeverityLevel == SeverityLevel.MINOR.ordinal()) {
            severityLevel = SeverityLevel.MINOR;
        } else if (bugSeverityLevel == SeverityLevel.MAJOR.ordinal()) {
            severityLevel = SeverityLevel.MAJOR;
        } else {
            severityLevel = SeverityLevel.CRITICAL;
        }
        return severityLevel;
    }

    /**
     * Retrieves a Bug object based on the bug's ID.
     *
     * @param bugId The ID of the bug.
     * @return A Bug object.
     * @throws SQLException If there is an error in the SQL operation.
     */
    @Override
    public Bug getBugObject(int bugId) throws SQLException {
        try (PreparedStatement preparedStatement1 = connection.prepareStatement(Queries.GET_BUGS_FOR_PROJECT)) {
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                int bugId1 = resultSet1.getInt("bugId");
                int projectId = resultSet1.getInt("projectId");
                String bugHeading = resultSet1.getString("bugHeading");
                int createdBy = resultSet1.getInt("createdBy");
                int assignedTo = resultSet1.getInt("assignedTo");
                Date openDate = resultSet1.getDate("openDate");
                String markForClosing = resultSet1.getString("markForClosing");
                int closedBy = resultSet1.getInt("closedBy");
                Date closedOn = resultSet1.getDate("closedOn");
                int bugStatus = resultSet1.getInt("bugStatus");
                BugStatus bugStatus1;
                if (bugStatus == BugStatus.OPEN.ordinal())
                    bugStatus1 = BugStatus.OPEN;
                else
                    bugStatus1 = BugStatus.CLOSED;
                int bugSeverityLevel = resultSet1.getInt("severityLevel");
                SeverityLevel severityLevel = getSeverityLevel(bugSeverityLevel);
                if (bugId1 == bugId) {
                    Bug bug = new Bug(bugHeading, bugHeading, projectId, createdBy, assignedTo, openDate, bugStatus1, severityLevel);
                    bug.setBugId(bugId);
                    bug.setMarkForClosing(markForClosing);
                    bug.setClosedOn(closedOn);
                    bug.setClosedBy(closedBy);
                    return bug;
                }
            }
            throw new SQLException("Could not fetch bug");
        }
    }
}
