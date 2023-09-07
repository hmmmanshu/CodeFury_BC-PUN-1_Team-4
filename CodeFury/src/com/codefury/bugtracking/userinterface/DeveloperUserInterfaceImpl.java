package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.service.DeveloperService;
import com.codefury.bugtracking.service.DeveloperServiceImpl;

import java.util.List;
import java.util.Scanner;

public class DeveloperUserInterfaceImpl implements DeveloperUserInterface {
    private DeveloperService developerService;
    private final int developerId;

    public DeveloperUserInterfaceImpl(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public void listAllBugs() {
        developerService = new DeveloperServiceImpl(developerId);
        List<Bug> bugs = developerService.getAllBugs();
        for (Bug bug : bugs) {
            System.out.printf("[Bug ID : %d\tBug Heading : %s]", bug.getBugId(), bug.getBugHeading());
        }
    }

    @Override
    public void markBugForClosing() {
        Scanner scanner;
        developerService = new DeveloperServiceImpl(developerId);
        scanner = new Scanner(System.in);
        System.out.println("Enter Bug Id to be marked for closing");
        int bugId = scanner.nextInt();
        System.out.println("Enter remarks for closing");
        String remarks = scanner.next();
        developerService.markBugForClosing(bugId, remarks, developerId);
    }
}
