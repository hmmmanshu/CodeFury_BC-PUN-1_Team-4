package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.beans.ProjectStatus;

public interface ProjectManagerUserInterface {
    void addNewProject();

    void viewProjectsDirectory();

    void closeBug();

    void changeProjectStatus();

    void showChoices();

    void addEmployeeToProject();
}
