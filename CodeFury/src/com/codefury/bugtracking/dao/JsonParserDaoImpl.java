package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.beans.ProjectManager;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * DAO class for JSON parser
 */
public class JsonParserDaoImpl implements JsonParserDao {

    private final Connection connection;

    public JsonParserDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    /**
     * Saves the employee data to the database.
     *
     * @param employee the employee object to be saved
     */
    @Override
    public void saveEmployeeData(Employee employee) {
        int employeeId = employee.getEmployeeId();
        String employeeName = employee.getName();
        String employeeEmail = employee.getEmail();
        String password = employee.getPassword();
        Date dateOfJoining = employee.getDateOfJoining();

        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_EMPLOYEE)) {
            preparedStatement.setInt(1, employeeId);
            preparedStatement.setString(2, employeeName);
            preparedStatement.setString(3, employeeEmail);
            preparedStatement.setString(4, String.valueOf(dateOfJoining.getDate()));
            preparedStatement.setString(5, password);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if (employee instanceof Developer) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_DEVELOPER)) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else if (employee instanceof ProjectManager) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_PROJECT_MANAGER)) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_TESTER)) {
                preparedStatement.setInt(1, employeeId);
                preparedStatement.execute();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
