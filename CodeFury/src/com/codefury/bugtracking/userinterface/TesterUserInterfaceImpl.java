package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.service.TesterService;
import com.codefury.bugtracking.service.TesterServiceImpl;

import java.util.List;
import java.util.Scanner;

public class TesterUserInterfaceImpl implements TesterUserInterface {
    private final int testerId;
    private TesterService testerService;

    public TesterUserInterfaceImpl(int testerId) {
        this.testerId = testerId;
    }

    @Override
    public void listProjects() {
        testerService = new TesterServiceImpl(testerId);
        List<Project> projectList = testerService.getProjectsList();
        for (Project project : projectList) {
            System.out.println(project);
            // TODO: Print projects with bugs
        }
    }

    @Override
    public void raiseNewBug() {
        testerService = new TesterServiceImpl(testerId);
        Scanner scanner = new Scanner(System.in);
        // TODO : Self Generated Bug Id
        System.out.println("Enter bug Id");
        int bugId = scanner.nextInt();
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
        testerService.raiseNewBug(bugId,projectId, testerId, bugTitle, bugDescription, bugSeverityLevel);
    }
}
