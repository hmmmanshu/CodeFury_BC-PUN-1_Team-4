package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationDaoImpl implements AuthenticationDao {
    private Connection connection = null;

    public AuthenticationDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public Employee getEmployeeObject(int employeeId) throws EmployeeDoesNotExistException {
        return null;
    }

    @Override
    public void addCredentials(Employee employee, String password) throws SQLException {

    }
}
