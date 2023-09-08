package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;

import java.sql.SQLException;

public class AuthenticationDaoImpl implements AuthenticationDao{
    @Override
    public Employee getEmployeeObject(int employeeId) throws EmployeeDoesNotExistException {

        return null;
    }

    @Override
    public void addCredentials(Employee employee, String password)  throws SQLException {

    }
}
