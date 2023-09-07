package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;

public interface AuthenticationService {
    Employee validateCredentials(int employeeId, String password);

    void addNewCredentials(int employeeId, String password, String confirmationPassword);
}
