package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;

import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    @Override
    public List<Bug> getAllBugs() {
        return null;
    }

    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) {

    }
}
