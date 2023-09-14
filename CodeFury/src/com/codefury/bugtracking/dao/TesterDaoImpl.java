package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.*;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the Tester Data Access Object (DAO) interface that provides database operations for testers.
 */
public class TesterDaoImpl implements TesterDao {

    private final Connection connection;

    /**
     * Constructs a new TesterDaoImpl instance and initializes the database connection.
     */
    public TesterDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Fetches a list of projects handled by a tester from the database.
     *
     * @param testerId The ID of the tester.
     * @return A list of projects that the tester handles.
     * @throws SQLException If there is an error in fetching the project list.
     */
    @Override
    public List<Project> getProjectsList(int testerId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECTS_LIST_FOR_TESTER)) {
            preparedStatement.setInt(1, testerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projectList = new ArrayList<>();

            while (resultSet.next()) {
                int projectId = resultSet.getInt("projectId");
                String projectTitle = resultSet.getString("projectName");
                int projectManagerId = resultSet.getInt("projectManagerId");
                int projectStatus = resultSet.getInt("projectStatus");

                ProjectStatus projectStatus1;
                if (projectStatus == ProjectStatus.IN_PROGRESS.ordinal()) {
                    projectStatus1 = ProjectStatus.IN_PROGRESS;
                } else {
                    projectStatus1 = ProjectStatus.COMPLETED;
                }
                Project project = new Project(projectTitle, projectManagerId);
                project.setProjectId(projectId);
                project.setProjectStatus(projectStatus1);

                try (PreparedStatement preparedStatement1 = connection.prepareStatement(Queries.GET_BUGS_FOR_PROJECT)) {
                    preparedStatement1.setInt(1, projectId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        int bugId = resultSet1.getInt("bugId");
                        String bugHeading = resultSet1.getString("bugHeading");
                        int projectId2 = project.getProjectId();
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
                        Bug bug = new Bug(bugHeading, bugHeading, projectId2, createdBy, assignedTo, openDate, bugStatus1, severityLevel);
                        bug.setBugId(bugId);
                        bug.setMarkForClosing(markForClosing);
                        bug.setClosedOn(closedOn);
                        bug.setClosedBy(closedBy);
                        project.addBug(bug);
                    }
                }
                projectList.add(project);
            }
            return projectList;
        }
    }

    /**
     * Gets the severity level of a bug as the ordinal value and converts it back to an Enum.
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
     * Adds a new bug to the database.
     *
     * @param bug The Bug object to be added.
     * @throws SQLException If there is an error in adding the bug to the database.
     */
    @Override
    public void addNewBug(Bug bug) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_BUGS)) {
            preparedStatement.setInt(1, bug.getBugId());
            preparedStatement.setString(2, bug.getBugHeading());
            preparedStatement.setInt(3, bug.getProjectId());
            preparedStatement.setInt(4, bug.getCreatedBy());
            preparedStatement.setInt(5, bug.getAssignedTo());
            preparedStatement.setDate(6, (java.sql.Date) bug.getOpenDate());
            preparedStatement.setString(7, bug.getMarkForClosing());
            preparedStatement.setInt(8, bug.getClosedBy());
            preparedStatement.setDate(9, (java.sql.Date) bug.getClosedOn());
            preparedStatement.setInt(10, bug.getBugStatus().ordinal());
            preparedStatement.setInt(11, bug.getSeverityLevel().ordinal());
            preparedStatement.execute();
        }
    }

    /**
     * Fetches a list of bugs for a specific project from the database.
     *
     * @param projectId The ID of the project.
     * @return A list of bugs related to the specified project.
     * @throws SQLException If there is an error in fetching the bug list.
     */
    @Override
    public List<Bug> getBugsList(int projectId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_BUGS_FOR_TESTER)) {
            preparedStatement.setInt(1, projectId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Bug> bugsList = new ArrayList<>();
            while (resultSet.next()) {
                int bugId = resultSet.getInt("bugId");
                String bugHeading = resultSet.getString("bugHeading");
                int createdBy = resultSet.getInt("createdBy");
                int assignedTo = resultSet.getInt("assignedTo");
                Date openDate = resultSet.getDate("openDate");
                String markForClosing = resultSet.getString("markForClosing");
                int closedBy = resultSet.getInt("closedBy");
                Date closedOn = resultSet.getDate("closedOn");
                int bugStatus = resultSet.getInt("bugStatus");
                BugStatus bugStatus1;
                if (bugStatus == BugStatus.OPEN.ordinal())
                    bugStatus1 = BugStatus.OPEN;
                else
                    bugStatus1 = BugStatus.CLOSED;
                int bugSeverityLevel = resultSet.getInt("severityLevel");
                SeverityLevel severityLevel = getSeverityLevel(bugSeverityLevel);
                Bug bug = new Bug(bugHeading, bugHeading, projectId, createdBy, assignedTo, openDate, bugStatus1, severityLevel);
                bug.setBugId(bugId);
                bug.setMarkForClosing(markForClosing);
                bug.setClosedOn(closedOn);
                bug.setClosedBy(closedBy);
                bugsList.add(bug);
            }
            return bugsList;
        }
    }
}
