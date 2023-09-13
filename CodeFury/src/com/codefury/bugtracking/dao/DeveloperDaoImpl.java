package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.utils.DatabaseConnection;
import com.codefury.bugtracking.utils.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class DeveloperDaoImpl implements DeveloperDao {
    private final Connection connection;

    public DeveloperDaoImpl() {
        this.connection = DatabaseConnection.getConnection();
    }

    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.MARK_BUG_FOR_CLOSING)) {
            preparedStatement.setString(1, remarks);
            preparedStatement.setInt(2, bugId);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public Developer getDeveloperObject(int developerId) throws SQLException {

        Developer developer = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.GET_DEVELOPER_OBJECT)) {
            preparedStatement.setInt(1, developerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int projectId = resultSet.getInt("projectId");
                try (PreparedStatement preparedStatement1 = connection.prepareStatement((Queries.GET_EMPLOYEE_OBJECT))) {
                    preparedStatement.setInt(1, developerId);
                    ResultSet resultSet1 = preparedStatement1.executeQuery();
                    while (resultSet1.next()) {
                        String employeeName = resultSet1.getString("employeeName");
                        String email = resultSet1.getString("email");
                        Date dateOfJoining = resultSet1.getDate("dateOfJoining");
                        developer = new Developer(employeeName, email, dateOfJoining);
                    }
                }
            }
            return developer;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    @Override
    public Project getProjectObject(int projectId) {
        return null;
    }

    @Override
    public Bug getBugObject(int bugId) {
        return null;
    }
}
