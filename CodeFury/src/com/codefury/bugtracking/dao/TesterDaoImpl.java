package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;

import java.sql.SQLException;
import java.util.List;

public class TesterDaoImpl implements TesterDao{
    @Override
    public List<Project> getProjectsList(int testerId) throws SQLException {

        return null;
    }

    @Override
    public void addNewBug(Bug bug)throws SQLException {

    }

    @Override
    public List<Bug> getBugsList(int projectId)throws SQLException {
        return null;
    }
}
