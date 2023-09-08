package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.ProjectStatus;
import com.codefury.bugtracking.exceptions.CouldNotAddProjectException;
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


    @Override
    public void addNewProject() {
        projectManagerService = new ProjectManagerServiceImpl();
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

    @Override
    public void viewProjectsDirectory() {
        projectManagerService = new ProjectManagerServiceImpl();
        scanner = new Scanner(System.in);
        projectManagerService = new ProjectManagerServiceImpl();
        List<Project> projectList = projectManagerService.getProjectsList();
        for (Project project : projectList) {
            System.out.println(project);
        }
    }

    @Override
    public void closeBug() {
        projectManagerService = new ProjectManagerServiceImpl();
        scanner = new Scanner(System.in);
        System.out.println("Enter bug id to be close");
        int bugId = scanner.nextInt();
        projectManagerService.closeBug(bugId);
    }

    @Override
    public void changeProjectStatus() {

    }

    @Override
    public void addEmployeeToProject() {

    }
}
