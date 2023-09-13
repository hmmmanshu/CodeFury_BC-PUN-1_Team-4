package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.exceptions.InvalidBugMarkedForClosingException;
import com.codefury.bugtracking.exceptions.NoProjectAssignedToDeveloper;
import com.codefury.bugtracking.service.DeveloperService;
import com.codefury.bugtracking.service.DeveloperServiceImpl;

import java.util.List;
import java.util.Scanner;

/**
 * Implementation of the DeveloperUserInterface for handling developer-specific interactions.
 */
public class DeveloperUserInterfaceImpl implements DeveloperUserInterface {
    private DeveloperService developerService;
    private final int developerId;
    private Scanner scanner;

    /**
     * Constructor to create a DeveloperUserInterfaceImpl with the developer's ID.
     *
     * @param developerId The ID of the developer.
     */
    public DeveloperUserInterfaceImpl(int developerId) {
        this.developerId = developerId;
    }

    /**
     * Method to display the available choices/options for a developer.
     */
    @Override
    public void showChoices() {
        scanner = new Scanner(System.in);
        System.out.println("Enter choice : ");
        System.out.println("1. List all bugs");
        System.out.println("2. Mark bug for closing");
        System.out.println("3. Exit");
        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.listAllBugs();
                    break;
                case 2:
                    this.markBugForClosing();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please enter a valid choice");
            }
        } while (choice < 1 || choice > 3);
    }

    /**
     * Method to list all bugs assigned to the developer.
     */
    void listAllBugs() {
        developerService = new DeveloperServiceImpl(developerId);
        List<Bug> bugs = null;
        try {
            bugs = developerService.getAllBugs();
            for (Bug bug : bugs) {
                System.out.printf("[Bug ID : %d\tBug Heading : %s]", bug.getBugId(), bug.getBugHeading());
            }
        } catch (NoProjectAssignedToDeveloper e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Method to mark a bug for closing by the developer.
     */
    void markBugForClosing() {
        developerService = new DeveloperServiceImpl(developerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter Bug Id to be marked for closing");
        int bugId = scanner.nextInt();
        System.out.println("Enter remarks for closing");
        String remarks = scanner.next();
        try {
            developerService.markBugForClosing(bugId, remarks, developerId);
        } catch (NoProjectAssignedToDeveloper | InvalidBugMarkedForClosingException e) {
            System.out.println(e.getMessage());
        }
    }
}
