package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.dao.AuthenticationDao;
import com.codefury.bugtracking.dao.AuthenticationDaoImpl;
import com.codefury.bugtracking.exceptions.CouldNotAddEmployeeException;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.exceptions.InvalidCredentialsException;
import com.codefury.bugtracking.exceptions.PasswordsDoNotMatchException;

import java.sql.SQLException;

/**
 * Implementation of the AuthenticationService interface.
 */
public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationDao authenticationDao;

    @Override
    public Employee validateCredentials(int employeeId, String password) throws InvalidCredentialsException, EmployeeDoesNotExistException, SQLException {
        authenticationDao = new AuthenticationDaoImpl();

        // Retrieve the employee object from the database using the employeeId
        Employee employee = authenticationDao.getEmployeeObject(employeeId);

        // Check if the provided employeeId and password match the database records
        if (employee.getEmployeeId() == employeeId && password.equals(employee.getPassword())) {
            return employee;
        } else {
            throw new InvalidCredentialsException("Invalid Credentials Entered");
        }
    }

    @Override
    public void addNewCredentials(int employeeId, String password, String confirmationPassword)
            throws PasswordsDoNotMatchException, CouldNotAddEmployeeException, EmployeeDoesNotExistException {
        authenticationDao = new AuthenticationDaoImpl();

        // Check if the provided password and confirmation password match
        if (!password.equals(confirmationPassword)) {
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }

        try {
            // Retrieve the employee object from the database using the employeeId
            Employee employee = authenticationDao.getEmployeeObject(employeeId);

            // Add new credentials for the employee
            authenticationDao.addCredentials(employee, password);
        } catch (SQLException e) {
            throw new CouldNotAddEmployeeException("Could not save data");
        }
    }
}
