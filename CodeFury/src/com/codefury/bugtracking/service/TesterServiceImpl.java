package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.BugStatus;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.dao.TesterDao;
import com.codefury.bugtracking.dao.TesterDaoImpl;
import com.codefury.bugtracking.exceptions.CantRaiseBugInCurrentProjectException;
import com.codefury.bugtracking.exceptions.CouldNotGetBugsList;
import com.codefury.bugtracking.exceptions.CouldNotGetProjectsListException;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the TesterService interface.
 */
public class TesterServiceImpl implements TesterService {

    /**
     * The ID of the tester.
     */
    private final int testerId;

    /**
     * The TesterDao object.
     */
    private TesterDao testerDao;

    /**
     * Constructor for the TesterServiceImpl class.
     *
     * @param testerId The ID of the tester.
     */
    public TesterServiceImpl(int testerId) {
        this.testerId = testerId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Project> getProjectsList() throws CouldNotGetProjectsListException {
        testerDao = new TesterDaoImpl();
        List<Project> projectList = null;
        try {
            projectList = testerDao.getProjectsList(testerId);
        } catch (SQLException e) {
            throw new CouldNotGetProjectsListException("Could not get projects list : " + e.getMessage());
        }
        return projectList;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Bug> getBugsList(int projectId) throws CouldNotGetBugsList {
        testerDao = new TesterDaoImpl();
        try {
            return testerDao.getBugsList(projectId);
        } catch (SQLException e) {
            throw new CouldNotGetBugsList("Could not get bugs list : " + e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void raiseNewBug(int projectId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel) throws CantRaiseBugInCurrentProjectException {
        testerDao = new TesterDaoImpl();
        try {
            List<Project> projectList = testerDao.getProjectsList(this.testerId);
            if (projectList.get(0).getProjectId() != projectId && projectList.get(1).getProjectId() != projectId)
                throw new CantRaiseBugInCurrentProjectException("Cant raise bug in current project");

            int projectManagerId = projectList.get(0).getProjectId() == projectId ? projectList.get(0).getProjectManagerId() : projectList.get(1).getProjectManagerId();
            LocalDateTime currentDateTime = LocalDateTime.now();
            Date currentDate = java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

            Bug bug = new Bug(bugTitle, bugDescription, projectId, this.testerId, projectManagerId, currentDate, BugStatus.OPEN, bugSeverityLevel);
            testerDao.addNewBug(bug);
        } catch (SQLException e) {
            throw new CantRaiseBugInCurrentProjectException("Cant raise bug in current project : " + e.getMessage());
        }

    }
}
