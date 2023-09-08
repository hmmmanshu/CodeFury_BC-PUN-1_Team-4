package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.beans.ProjectManager;
import com.codefury.bugtracking.exceptions.CouldNotAddEmployeeException;
import com.codefury.bugtracking.exceptions.EmployeeDoesNotExistException;
import com.codefury.bugtracking.exceptions.InvalidCredentialsException;
import com.codefury.bugtracking.exceptions.PasswordsDoNotMatchException;
import com.codefury.bugtracking.service.AuthenticationService;
import com.codefury.bugtracking.service.AuthenticationServiceImpl;

import java.util.Scanner;

public class AuthenticationUserInterfaceImpl implements AuthenticationUserInterface {
    private Scanner scanner;
    private AuthenticationService authenticationService;

    @Override
    public void login() {
        authenticationService = new AuthenticationServiceImpl();
        int employeeId;
        String password;
        scanner = new Scanner(System.in);

        System.out.println("Enter employeeId");
        employeeId = scanner.nextInt();
        System.out.println("Enter password");
        password = scanner.next();

        try {
            Employee employee = authenticationService.validateCredentials(employeeId, password);

            if (employee instanceof ProjectManager) {
                ProjectManagerUserInterface projectManagerUserInterface = new ProjectManagerUserInterfaceImpl(employeeId);
                projectManagerUserInterface.showChoices();
            } else if (employee instanceof Developer) {
                DeveloperUserInterface developerUserInterface = new DeveloperUserInterfaceImpl(employeeId);
                developerUserInterface.showChoices();
            } else {
                TesterUserInterface testerUserInterface = new TesterUserInterfaceImpl(employeeId);
                testerUserInterface.showChoices();
            }
        } catch (InvalidCredentialsException | EmployeeDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void register() {
        authenticationService = new AuthenticationServiceImpl();
        scanner = new Scanner(System.in);
        System.out.println("Enter employeeId");
        int employeeId = scanner.nextInt();
        System.out.println("Enter new password");
        String password = scanner.next();
        System.out.println("Enter password again");
        String confirmationPassword = scanner.next();

        try {
            authenticationService.addNewCredentials(employeeId, password, confirmationPassword);
            System.out.println("New user added to directory successfully");
        } catch (PasswordsDoNotMatchException | CouldNotAddEmployeeException | EmployeeDoesNotExistException e) {
            System.out.println(e.getMessage());
        }
    }
}
