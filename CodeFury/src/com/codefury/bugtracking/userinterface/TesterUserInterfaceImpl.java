package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.exceptions.CantRaiseBugInCurrentProjectException;
import com.codefury.bugtracking.exceptions.CouldNotGetBugsList;
import com.codefury.bugtracking.exceptions.CouldNotGetProjectsListException;
import com.codefury.bugtracking.service.TesterService;
import com.codefury.bugtracking.service.TesterServiceImpl;

import java.util.List;
import java.util.Scanner;

public class TesterUserInterfaceImpl implements TesterUserInterface {
    private final int testerId;
    private TesterService testerService;
    private Scanner scanner;

    public TesterUserInterfaceImpl(int testerId) {
        this.testerId = testerId;
    }

    @Override
    public void showChoices() {
        scanner = new Scanner(System.in);
        System.out.println("Enter choice");
        System.out.println("1. Show projects list");
        System.out.println("2. Raise new bug");
        System.out.println("3. Exit");
        int choice;
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.listProjects();
                    break;
                case 2:
                    this.raiseNewBug();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Please enter one of the above choices");
            }
        } while (choice < 1 || choice > 3);
    }

    void listProjects() {
        testerService = new TesterServiceImpl(testerId);
        try {
            List<Project> projectList = testerService.getProjectsList();
            for (Project project : projectList) {
                System.out.println(project);
                try {
                    List<Bug> bugsList = testerService.getBugsList(project.getProjectId());
                    for (Bug bug : bugsList) System.out.println(bug);
                } catch (CouldNotGetBugsList e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (CouldNotGetProjectsListException e) {
            System.out.println(e.getMessage());
        }
    }

    void raiseNewBug() {
        testerService = new TesterServiceImpl(testerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter project Id to raise bug in");
        int projectId = scanner.nextInt();
        System.out.println("Enter bug title");
        String bugTitle = scanner.nextLine();
        System.out.println("Enter bug description");
        String bugDescription = scanner.nextLine();
        SeverityLevel bugSeverityLevel;
        int choice;
        do {
            System.out.println("Enter bug severity level");
            System.out.println("1. Trivial");
            System.out.println("2. Major");
            System.out.println("3. Minor");
            System.out.println("4. Critical");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    bugSeverityLevel = SeverityLevel.TRIVIAL;
                    break;
                case 2:
                    bugSeverityLevel = SeverityLevel.MINOR;
                    break;
                case 3:
                    bugSeverityLevel = SeverityLevel.MAJOR;
                    break;
                case 4:
                    bugSeverityLevel = SeverityLevel.CRITICAL;
                    break;
                default:
                    bugSeverityLevel = null;
                    System.out.println("Please enter one of the above choices");
            }
        } while (choice < 1 || choice > 4);
        try {
            testerService.raiseNewBug(projectId, bugTitle, bugDescription, bugSeverityLevel);
        } catch (CantRaiseBugInCurrentProjectException e) {
            System.out.println(e.getMessage());
        }
    }
}
