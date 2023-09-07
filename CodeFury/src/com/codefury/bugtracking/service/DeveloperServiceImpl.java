package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.dao.DeveloperDao;
import com.codefury.bugtracking.dao.DeveloperDaoImpl;

import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperDao developerDao;
    private int developerId;

    public DeveloperServiceImpl(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public List<Bug> getAllBugs() {
        developerDao = new DeveloperDaoImpl();
        Developer developer = developerDao.getDeveloperObject(developerId);

        // Can throw no project assigned to developer
        int projectId = developer.getProjectId();

        // This SQL call should populate the bugs list too
        Project project = developerDao.getProjectObject(projectId);
        List<Bug> bugsList = new ArrayList<>();
        for (int bugId : project.getBugsList()) {
            bugsList.add(developerDao.getBugObject(bugId));
        }
        return bugsList;
    }

    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) {
        developerDao = new DeveloperDaoImpl();
        Developer developer = developerDao.getDeveloperObject(developerId);
        int projectId = developer.getProjectId();
        Project project = developerDao.getProjectObject(projectId);
        List<Bug> bugsList = new ArrayList<>();
        for (int bugId2 : project.getBugsList()) {
            if (bugId2 == bugId) {
                developerDao.markBugForClosing(bugId, remarks, developerId);
                return;
            }
        }
        System.out.println("Throw new no such bug exception");
    }
}
