package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Project;
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
    public void addNewProject() {
        projectManagerService = new ProjectManagerServiceImpl();
        scanner = new Scanner(System.in);
        // TODO : Self generated project id
        System.out.println("Enter Project Id");
        int projectId = scanner.nextInt();
        System.out.println("Enter Project Name");
        String projectName = scanner.nextLine();
        Project project = new Project(projectId, projectName, this.projectManagerId);
        projectManagerService.addNewProject(project);
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
}
