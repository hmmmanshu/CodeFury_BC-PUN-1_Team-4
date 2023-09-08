package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.dao.DeveloperDao;
import com.codefury.bugtracking.dao.DeveloperDaoImpl;
import com.codefury.bugtracking.exceptions.InvalidBugMarkedForClosingException;
import com.codefury.bugtracking.exceptions.NoProjectAssignedToDeveloper;

import java.util.ArrayList;
import java.util.List;

public class DeveloperServiceImpl implements DeveloperService {
    private DeveloperDao developerDao;
    private final int developerId;

    public DeveloperServiceImpl(int developerId) {
        this.developerId = developerId;
    }

    @Override
    public List<Bug> getAllBugs() throws NoProjectAssignedToDeveloper {
        developerDao = new DeveloperDaoImpl();
        Developer developer = developerDao.getDeveloperObject(developerId);

        if (developer.getProjectId().isEmpty())
            throw new NoProjectAssignedToDeveloper("You have no project assigned");
        int projectId = developer.getProjectId().get();
        Project project = developerDao.getProjectObject(projectId);
        List<Bug> bugsList = new ArrayList<>();
        for (int bugId : project.getBugsList()) {
            bugsList.add(developerDao.getBugObject(bugId));
        }
        return bugsList;
    }

    @Override
    public void markBugForClosing(int bugId, String remarks, int developerId) throws NoProjectAssignedToDeveloper, InvalidBugMarkedForClosingException {
        developerDao = new DeveloperDaoImpl();
        Developer developer = developerDao.getDeveloperObject(developerId);
        if (developer.getProjectId().isEmpty())
            throw new NoProjectAssignedToDeveloper("You have no project assigned");
        int projectId = developer.getProjectId().get();
        Project project = developerDao.getProjectObject(projectId);
        for (int bugId2 : project.getBugsList()) {
            if (bugId2 == bugId) {
                developerDao.markBugForClosing(bugId, remarks, developerId);
                return;
            }
        }
        throw new InvalidBugMarkedForClosingException("Could not mark such bug for closing");
    }
}
