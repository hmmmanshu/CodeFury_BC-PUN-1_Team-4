package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.Role;
import com.codefury.bugtracking.exceptions.*;
import com.codefury.bugtracking.service.ProjectManagerService;
import com.codefury.bugtracking.service.ProjectManagerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class ProjectManagerUserInterfaceImpl implements ProjectManagerUserInterface {
    private Scanner scanner;
    private final int projectManagerId;
    private ProjectManagerService projectManagerService;

    public ProjectManagerUserInterfaceImpl(int projectManagerId) {
        this.projectManagerId = projectManagerId;
    }


    @Override
    public void showChoices() {
        int choice;
        do {
            System.out.println("Enter choice");
            System.out.println("1. List all projects");
            System.out.println("2. Add a new project");
            System.out.println("3. Change project status");
            System.out.println("4. Close a bug");
            System.out.println("5. Add employees to a project");
            System.out.println("6. Exit");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    this.viewProjectsDirectory();
                    break;
                case 2:
                    this.addNewProject();
                    break;
                case 3:
                    this.changeProjectStatus();
                    break;
                case 4:
                    this.closeBug();
                    break;
                case 5:
                    this.addEmployeeToProject();
                case 6:
                    System.out.println("Logged out !");
                    return;
                default:
                    System.out.println("Please enter a valid choice");
            }
        } while (choice >= 1 && choice <= 5);
    }

    void addNewProject() {
        projectManagerService = new ProjectManagerServiceImpl(this.projectManagerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter Project Name");
        String projectName = scanner.nextLine();
        Project project = new Project(projectName, this.projectManagerId);

        try {
            projectManagerService.addNewProject(project);
            System.out.println("Project Id Assigned : " + project.getProjectId());
        } catch (CouldNotAddProjectException e) {
            System.out.println(e.getMessage());
        }
    }

    void viewProjectsDirectory() {
        projectManagerService = new ProjectManagerServiceImpl(this.projectManagerId);
        scanner = new Scanner(System.in);

        try {
            List<Project> projectList = projectManagerService.getProjectsList();
            for (Project project : projectList) {
                System.out.println(project);
            }
        } catch (CouldNotGetProjectsListException e) {
            System.out.println(e.getMessage());
        }
    }

    void closeBug() {
        projectManagerService = new ProjectManagerServiceImpl(this.projectManagerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter bug id to be close");
        int bugId = scanner.nextInt();
        try {
            projectManagerService.closeBug(bugId);
        } catch (CouldNotCloseBugException e) {
            System.out.println(e.getMessage());
        }
    }

    void changeProjectStatus() {
        projectManagerService = new ProjectManagerServiceImpl(this.projectManagerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter project id to mark as completed");
        int projectId = scanner.nextInt();
        try {
            projectManagerService.changeProjectStatus(projectId);
        } catch (CouldNotChangeProjectStatus e) {
            System.out.println(e.getMessage());
        }
    }

    void addEmployeeToProject() {
        projectManagerService = new ProjectManagerServiceImpl(this.projectManagerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter project id to add a new employee to");
        int projectId = scanner.nextInt();
        System.out.println("Enter employee id to be added");
        int employeeId = scanner.nextInt();
        System.out.println("Enter the role assigned\n1. Developer\n2. Tester");
        int choice = scanner.nextInt();
        Role role = choice == 1 ? Role.DEVELOPER : Role.TESTER;
        try {
            projectManagerService.addEmployeeToProject(projectId, employeeId, role);
        } catch (CouldNotAddEmployeeException e) {
            System.out.println(e.getMessage());
        }
    }
}
