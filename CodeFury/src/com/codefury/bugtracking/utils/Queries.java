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
    String INSERT_INTO_BUGS = "INSERT INTO BUGS (bugId, bugHeading, projectId, createdBy, assignedTo, openDate, markForClosing, closedBy, closedOn, bugStatus, severityLevel) VALUES (?,'?',?,?,?,?,?,?,?,?,?);";
    String GET_PROJECTS_LIST_FOR_PROJECT_MANAGER = "SELECT * FROM project WHERE projectManagerId = ?;";
    String GET_PROJECTS_LIST_FOR_TESTER = "SELECT * FROM project WHERE testerId = ?;";
    String GET_BUGS_FOR_PROJECT = "SELECT * FROM bugs WHERE projectManagerId = ?;";
    String GET_DEVELOPER_OBJECT = "SELECT * FROM developer WHERE employeeId = ?;";
    String GET_EMPLOYEE_OBJECT = "SELECT * FROM employee WHERE employeeId = ?;";
    String GET_PROJECT_OBJECT = "SELECT * FROM project WHERE projectId = ?;";
    String GET_BUGS_FOR_TESTER = "SELECT * FROM bugs WHERE projectId = ?;";
    String ADD_DEVELOPER_TO_PROJECT = "UPDATE developer SET projectId = ? WHERE employeeId = ?;";
    String ADD_TESTER_TO_PROJECT = "UPDATE project SET testerId = ? WHERE projectId = ?;";
    String CLOSE_BUG = "UPDATE bugs SET bugStatus = ? WHERE bugId = ?";
    String CHANGE_PROJECT_STATUS = "UPDATE project SET projectStatus = ? WHERE projectId ?";
    String MARK_BUG_FOR_CLOSING = "UPDATE bugs SET markForClosing = '?' where bugId = ?;";
}
