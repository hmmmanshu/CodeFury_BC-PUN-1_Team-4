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
 * Gives implementation for the ProjectManagerDao and contains necessary extra utility <br>
 * functions to implement those functions
 */
public class ProjectManagerDaoImpl implements ProjectManagerDao {

    // employee id of the project manager that has logged in
    private final int projectManagerId;

    // connection object to interact with jdbc
    private final Connection connection;

    /**
     * Constructor to get the mandatory employee id of the project manager that has logged in
     *
     * @param projectManagerId employee id of the project manager that has logged in
     */
    public ProjectManagerDaoImpl(int projectManagerId) {
        this.projectManagerId = projectManagerId;
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Add a new project to the database by fetching the relevant query from <br>
     * com.codefury.bugtracking.utils.Queries and execute using prepared statement
     *
     * @param project project object to be added to the database
     */
    @Override
    public void addNewProject(Project project) throws SQLException {
        // use try with resources to create prepared statement
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_PROJECT)) {
            preparedStatement.setInt(1, project.getProjectId());
            preparedStatement.setString(2, project.getProjectName());
            preparedStatement.setInt(3, this.projectManagerId);
            preparedStatement.setInt(5, project.getProjectStatus().ordinal());
            preparedStatement.execute();
        }
    }

    /**
     * Fetch project list from the database using the relevant query from <br>
     * com.codefury.bugtracking.utils.Queries and execute using a prepared statement
     *
     * @return list of the projects that the project manager handles
     * @throws SQLException could not fetch the project list
     */
    @Override
    public List<Project> getProjectsList() throws SQLException {
        // use try with resources to create prepared statement to execute the query
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_PROJECTS_LIST_FOR_PROJECT_MANAGER)) {

            // set the variable in the prepared statement
            preparedStatement.setInt(1, this.projectManagerId);

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

    /**
     * change the bug status to closed
     *
     * @param bugId the bug id of the bug to be closed
     * @throws SQLException could not close the bug
     */
    @Override
    public void closeBug(int bugId) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.CLOSE_BUG)) {
            preparedStatement.setInt(1, BugStatus.CLOSED.ordinal());
            preparedStatement.setInt(2, bugId);
            preparedStatement.execute();
        }
    }

    /**
     * change the project status
     *
     * @param projectId     project id of the project to be modified
     * @param projectStatus new status of the project
     * @throws SQLException could not modify project status
     */
    @Override
    public void changeProjectStatus(int projectId, ProjectStatus projectStatus) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.CHANGE_PROJECT_STATUS)) {
            preparedStatement.setInt(1, projectStatus.ordinal());
            preparedStatement.setInt(2, projectId);
            preparedStatement.execute();
        }
    }

    /**
     * Assign a new tester or developer to the project
     *
     * @param projectId  project id of the project to be modified
     * @param employeeId employee id of the employee to be added
     * @param role       role to be assigned to the new employee
     * @throws SQLException could not add employee to the project
     */
    @Override
    public void addEmployeeToProject(int projectId, int employeeId, Role role) throws SQLException {
        if (role == Role.TESTER) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.ADD_TESTER_TO_PROJECT)) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.setInt(2, projectId);
                preparedStatement.execute();
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.ADD_DEVELOPER_TO_PROJECT)) {
                preparedStatement.setInt(1, projectId);
                preparedStatement.setInt(2, employeeId);
                preparedStatement.execute();
            }
        }
    }
}
