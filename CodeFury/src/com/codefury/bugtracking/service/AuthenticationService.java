package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.CouldNotAddEmployeeException;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.exceptions.InvalidCredentialsException;
import com.codefury.bugtracking.exceptions.PasswordsDoNotMatchException;

public interface AuthenticationService {
    Employee validateCredentials(int employeeId, String password) throws InvalidCredentialsException, EmployeeDoesNotExistException;

    void addNewCredentials(int employeeId, String password, String confirmationPassword) throws PasswordsDoNotMatchException, CouldNotAddEmployeeException, EmployeeDoesNotExistException;
}
