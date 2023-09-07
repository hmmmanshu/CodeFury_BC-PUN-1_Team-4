package com.codefury.bugtracking.userinterface;

import java.util.Scanner;

public class AuthenticationUserInterfaceImpl implements AuthenticationUserInterface {
    private Scanner scanner;

    @Override
    public void login() {
        int employeeId;
        String password;
        scanner = new Scanner(System.in);
        System.out.println("Enter employeeId");
        employeeId = scanner.nextInt();
        System.out.println("Enter password");
        password = scanner.next();
        // TODO : Login and pass projectManagerid
    }

    @Override
    public void register() {
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
    }
}
