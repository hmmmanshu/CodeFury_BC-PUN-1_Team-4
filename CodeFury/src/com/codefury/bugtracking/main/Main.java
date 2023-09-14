package com.codefury.bugtracking.main;

import com.codefury.bugtracking.userinterface.AuthenticationUserInterface;
import com.codefury.bugtracking.userinterface.AuthenticationUserInterfaceImpl;
import com.codefury.bugtracking.userinterface.ImportJsonUserInterface;
import com.codefury.bugtracking.userinterface.ImportJsonUserInterfaceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int userChoice;

        do {
            printMainMenu();

            try {
                userChoice = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character

                switch (userChoice) {
                    case 1:
                        // Perform login
                        System.out.println("Logging in...");
                        AuthenticationUserInterface authenticationUserInterface = new AuthenticationUserInterfaceImpl();
                        authenticationUserInterface.login();
                        break;
                    case 2:
                        // Perform registration
                        System.out.println("Registering...");
                        AuthenticationUserInterface registrationUserInterface = new AuthenticationUserInterfaceImpl();
                        registrationUserInterface.register();
                        break;
                    case 3:
                        // Import JSON Data
                        System.out.println("Importing JSON Data...");
                        ImportJsonUserInterface importJsonUserInterface = new ImportJsonUserInterfaceImpl();
                        importJsonUserInterface.selectJsonFile();
                        break;
                    case 4:
                        System.out.println("Exiting the Bug Tracking System.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer choice.");
                scanner.nextLine(); // Clear the input buffer
                userChoice = -1; // Set an invalid choice to continue the loop
            }
        } while (userChoice != 4);

        scanner.close();
    }

    /**
     * Prints the main menu options to the console.
     */
    private static void printMainMenu() {
        System.out.println("Bug Tracking System - Main Menu");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Import JSON Data");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

}