package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;

import java.util.List;

public interface TesterDao {
    List<Project> getProjectsList(int testerId);
    void addNewBug(Bug bug);
}
