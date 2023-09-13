package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.exceptions.CouldNotAddEmployeeException;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.exceptions.InvalidCredentialsException;
import com.codefury.bugtracking.exceptions.PasswordsDoNotMatchException;

import java.sql.SQLException;

/**
 * Service interface for authentication and managing user credentials.
 */
public interface AuthenticationService {

    /**
     * Validates user credentials based on employee ID and password.
     *
     * @param employeeId The employee ID to validate.
     * @param password   The password to validate.
     * @return An Employee object if the credentials are valid.
     * @throws InvalidCredentialsException   If the credentials are invalid.
     * @throws EmployeeDoesNotExistException If the employee does not exist.
     */
    Employee validateCredentials(int employeeId, String password) throws InvalidCredentialsException, EmployeeDoesNotExistException, SQLException;

    /**
     * Adds new user credentials if they meet the requirements.
     *
     * @param employeeId           The employee ID to add.
     * @param password             The user's password.
     * @param confirmationPassword The confirmation of the password.
     * @throws PasswordsDoNotMatchException     If the password and confirmation password do not match.
     * @throws CouldNotAddEmployeeException    If the employee could not be added.
     * @throws EmployeeDoesNotExistException   If the employee does not exist.
     */
    void addNewCredentials(int employeeId, String password, String confirmationPassword)
            throws PasswordsDoNotMatchException, CouldNotAddEmployeeException, EmployeeDoesNotExistException;
}
