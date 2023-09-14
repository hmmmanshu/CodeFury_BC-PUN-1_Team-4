package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;

import java.sql.SQLException;

/**
 * Interface for the AuthenticationDao class.
 */
public interface AuthenticationDao {

    /**
     * Returns the employee object for the given employee ID.
     *
     * @param employeeId The ID of the employee.
     * @return The employee object for the given employee ID.
     * @throws EmployeeDoesNotExistException If the employee does not exist.
     * @throws SQLException                  If there is a problem with the database connection.
     */
    Employee getEmployeeObject(int employeeId) throws EmployeeDoesNotExistException, SQLException;

    /**
     * Adds credentials for the given employee.
     *
     * @param employee The employee object.
     * @param password The password for the employee.
     * @throws SQLException If there is a problem with the database connection.
     */
    void addCredentials(Employee employee, String password) throws SQLException;
}
