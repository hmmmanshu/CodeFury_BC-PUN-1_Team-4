package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.dao.AuthenticationDao;
import com.codefury.bugtracking.dao.AuthenticationDaoImpl;
import com.codefury.bugtracking.exceptions.CouldNotAddEmployeeException;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.exceptions.InvalidCredentialsException;
import com.codefury.bugtracking.exceptions.PasswordsDoNotMatchException;

import java.sql.SQLException;

public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationDao authenticationDao;

    @Override
    public Employee validateCredentials(int employeeId, String password) throws InvalidCredentialsException, EmployeeDoesNotExistException {
        authenticationDao = new AuthenticationDaoImpl();

        Employee employee = authenticationDao.getEmployeeObject(employeeId);

        if (employee.getEmployeeId() == employeeId && password.equals(employee.getPassword())) {
            return employee;
        } else {
            throw new InvalidCredentialsException("Invalid Credentials Entered");
        }
    }

    @Override
    public void addNewCredentials(int employeeId, String password, String confirmationPassword) throws PasswordsDoNotMatchException, CouldNotAddEmployeeException, EmployeeDoesNotExistException {
        authenticationDao = new AuthenticationDaoImpl();
        if (!password.equals(confirmationPassword)) {
            throw new PasswordsDoNotMatchException("Passwords do not match");
        }

        try {
            Employee employee = authenticationDao.getEmployeeObject(employeeId);
            authenticationDao.addCredentials(employee, password);
        } catch (SQLException e) {
            throw new CouldNotAddEmployeeException("Could not save data");
        }
    }
}
