package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.*;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TesterDaoImpl implements TesterDao {

    private final Connection connection;

    public TesterDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Fetch project list from the database using the relevant query from <br>
     * com.codefury.bugtracking.utils.Queries and execute using a prepared statement
     *
     * @return list of the projects that the tester handles
     * @throws SQLException could not fetch the project list
     */
    @Override
    public List<Project> getProjectsList(int testerId) throws SQLException {
        // use try with resources to create prepared statement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECTS_LIST_FOR_TESTER)) {

            // set the variable in the prepared statement
            preparedStatement.setInt(1, testerId);

            // get the resultSet for the query
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Project> projectList = new ArrayList<>();

            // while there are more rows to be read
            while (resultSet.next()) {
                int projectId = resultSet.getInt("projectId");
                String projectTitle = resultSet.getString("projectName");
                int projectManagerId = resultSet.getInt("projectManagerId");
                int projectStatus = resultSet.getInt("projectStatus");

                // convert enum to ordinal to store in the database
                ProjectStatus projectStatus1;
                if (projectStatus == ProjectStatus.IN_PROGRESS.ordinal()) {
                    projectStatus1 = ProjectStatus.IN_PROGRESS;
                } else {
                    projectStatus1 = ProjectStatus.COMPLETED;
                }
                Project project = new Project(projectTitle, projectManagerId);
                project.setProjectId(projectId);
                project.setProjectStatus(projectStatus1);

                // get all the bugs related to the project
                try (PreparedStatement preparedStatement1 = connection.prepareStatement(Queries.GET_BUGS_FOR_PROJECT)) {
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
     * Get the severity level of bug as the ordinal value and convert back to Enum
     *
     * @param bugSeverityLevel ordinal value of the severity level
     * @return enum of severity level
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
            preparedStatement.setInt(10, bug.getStatus().ordinal());
            preparedStatement.setInt(11, bug.getSeverityLevel().ordinal());
            preparedStatement.execute();
        }
    }

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


























