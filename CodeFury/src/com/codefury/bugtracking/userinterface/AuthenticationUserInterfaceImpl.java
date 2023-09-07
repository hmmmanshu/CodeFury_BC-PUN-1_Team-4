package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Employee;
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
        Employee employeeType = authenticationService.validateCredentials(employeeId, password);
        // TODO : Login and pass projectManagerId or whatever user trying to login
    }

    @Override
    public void register() {
        authenticationService = new AuthenticationServiceImpl();
        scanner = new Scanner(System.in);
        int employeeId;
        String password;
        String confirmationPassword;
        System.out.println("Enter employeeId");
        employeeId = scanner.nextInt();
        System.out.println("Enter password");
        password = scanner.next();
        System.out.println("Enter password again");
        confirmationPassword = scanner.next();
        authenticationService.addNewCredentials(employeeId, password, confirmationPassword);
        System.out.println("New user added to directory successfully");
    }
}
