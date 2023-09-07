package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;

import java.util.List;

public interface DeveloperService {
    List<Bug> getAllBugs();
    void markBugForClosing(int bugId, String remarks, int developerId);
}
