package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.beans.ProjectManager;
import com.codefury.bugtracking.beans.Tester;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class AuthenticationDaoImpl implements AuthenticationDao {
    private Connection connection = null;

    public AuthenticationDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Find the employee if exists, and which kind of employee is asked to be logged in to the system
     *
     * @param employeeId employee Id of the person to be logged in
     * @return Employee object that has logged in
     * @throws EmployeeDoesNotExistException employee not found in database
     * @throws SQLException                  could not perform a specific SQL function
     */
    @Override
    public Employee getEmployeeObject(int employeeId) throws EmployeeDoesNotExistException, SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_EMPLOYEE_OBJECT)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.isAfterLast())
                throw new EmployeeDoesNotExistException("Employee not found");
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_EMPLOYEE_SPECIFIC_OBJECT)) {
            preparedStatement.setString(1, "developer");
            preparedStatement.setInt(2, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.isAfterLast()) {
                String name = resultSet.getString("employeeName");
                Date dateOfJoining = resultSet.getDate("dateOfJoining");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");

                Employee developer = new Developer(name, email, dateOfJoining);
                developer.setPassword(password);
                return developer;
            }
            preparedStatement.setString(1, "tester");
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.isAfterLast()) {
                String name = resultSet.getString("employeeName");
                Date dateOfJoining = resultSet.getDate("dateOfJoining");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                return new Tester();
            } else {
                String name = resultSet.getString("employeeName");
                Date dateOfJoining = resultSet.getDate("dateOfJoining");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                return new ProjectManager();
            }


        }
    }

    @Override
    public void addCredentials(Employee employee, String password) throws SQLException {

    }
}
