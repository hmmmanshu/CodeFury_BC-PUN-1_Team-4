package com.codefury.bugtracking.utils;

public interface Queries {
    String INSERT_INTO_EMPLOYEE = "INSERT INTO employee(employeeId, employeeName, email, dateOfJoining, employeePassword) VALUES (?, ?, ?, ?, ?);";
    String INSERT_INTO_DEVELOPER = "INSERT INTO developer(employeeId) VALUES(?);";
    String INSERT_INTO_TESTER= "INSERT INTO tester(employeeId) VALUES(?);";
    String INSERT_INTO_PROJECT_MANAGER= "INSERT INTO tester(employeeId) VALUES(?);";
}
