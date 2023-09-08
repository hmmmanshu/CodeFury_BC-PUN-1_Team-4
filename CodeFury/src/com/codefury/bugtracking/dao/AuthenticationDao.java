package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;

import java.sql.SQLException;

public interface AuthenticationDao {
    Employee getEmployeeObject(int employeeId) throws EmployeeDoesNotExistException;

    void addCredentials(Employee employee, String password) throws SQLException;
}
