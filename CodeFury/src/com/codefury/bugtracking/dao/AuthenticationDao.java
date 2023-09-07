package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;

public interface AuthenticationDao {
    Employee getCredentials(int employeeId);
    void addCredentials(int employeeId, String password);
}
