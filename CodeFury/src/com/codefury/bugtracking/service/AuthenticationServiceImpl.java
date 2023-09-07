package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.dao.AuthenticationDao;
import com.codefury.bugtracking.dao.AuthenticationDaoImpl;

public class AuthenticationServiceImpl implements AuthenticationService {
    private AuthenticationDao authenticationDao;

    @Override
    public Employee validateCredentials(int employeeId, String password) {
        authenticationDao = new AuthenticationDaoImpl();

        // Get a DTO from DAO layer with employeeId and password
        Employee employee = authenticationDao.getCredentials(employeeId);

        if (employee.getEmployeeId() == employeeId && password.equals(employee.getPassword())) {
            return employee;
        } else {
            System.out.println("Throw an exception InvalidCredentialsException");
            return null;
        }
    }

    @Override
    public void addNewCredentials(int employeeId, String password, String confirmationPassword) {
        authenticationDao = new AuthenticationDaoImpl();
        if(!password.equals(confirmationPassword)){
            System.out.println("Passwords Don't Match Exception");
        }
        authenticationDao.addCredentials(employeeId, password);
        // TODO: Throw employeeNotExistException
    }
}
