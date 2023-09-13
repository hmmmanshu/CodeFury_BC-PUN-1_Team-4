package com.codefury.bugtracking.utils;

/**
 * Interface to store all the queries to improve the modularity of the program.
 */
public interface Queries {
    String INSERT_INTO_EMPLOYEE = "INSERT INTO employee(employeeId, employeeName, email, dateOfJoining, employeePassword) VALUES (?, ?, ?, ?, ?);";
    String INSERT_INTO_DEVELOPER = "INSERT INTO developer(employeeId) VALUES(?);";
    String INSERT_INTO_TESTER = "INSERT INTO tester(employeeId) VALUES(?);";
    String INSERT_INTO_PROJECT_MANAGER = "INSERT INTO tester(employeeId) VALUES(?);";
    String INSERT_INTO_PROJECT = "INSERT INTO project (projectId, projectName, projectManagerId, projectStatus) VALUES (?, ?, ?, ?, ?);";
    String GET_PROJECTS_LIST_FOR_PROJECT_MANAGER = "SELECT * FROM project WHERE projectManagerId = ?;";
    String GET_BUGS_FOR_PROJECT = "SELECT * FROM bugs WHERE projectManagerId = ?;";
    String CLOSE_BUG = "UPDATE bugs SET bugStatus = ? WHERE bugId = ?";
    String CHANGE_PROJECT_STATUS = "UPDATE project SET projectStatus = ? WHERE projectId ?";
    String ADD_DEVELOPER_TO_PROJECT = "UPDATE developer SET projectId = ? WHERE employeeId = ?;";
    String ADD_TESTER_TO_PROJECT = "UPDATE project SET testerId = ? WHERE projectId = ?;";
}
