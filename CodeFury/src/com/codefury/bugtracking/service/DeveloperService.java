package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.exceptions.InvalidBugMarkedForClosingException;
import com.codefury.bugtracking.exceptions.NoProjectAssignedToDeveloper;

import java.util.List;

public interface DeveloperService {
    List<Bug> getAllBugs() throws NoProjectAssignedToDeveloper;
    void markBugForClosing(int bugId, String remarks, int developerId) throws NoProjectAssignedToDeveloper, InvalidBugMarkedForClosingException;
}
