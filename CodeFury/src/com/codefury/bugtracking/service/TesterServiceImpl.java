package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Bug;
import com.codefury.bugtracking.beans.Project;
import com.codefury.bugtracking.beans.SeverityLevel;
import com.codefury.bugtracking.dao.TesterDao;
import com.codefury.bugtracking.dao.TesterDaoImpl;

import java.util.List;

public class TesterServiceImpl implements TesterService {
    private final int testerId;
    private TesterDao testerDao;
    public TesterServiceImpl(int testerId) {
        this.testerId = testerId;
    }

    @Override
    public List<Project> getProjectsList() {
        testerDao = new TesterDaoImpl();
        // TODO: Can throw no projects assigned exception
        List<Project> projectList= testerDao.getProjectsList(testerId);
        return projectList;
    }

    @Override
    public void raiseNewBug(int bugId, int testerId,int projectId, String bugTitle, String bugDescription, SeverityLevel bugSeverityLevel) {
//        testerDao = new TesterDaoImpl();
//        Project project = testerDao.getProjectsList()
//        Bug bug = new Bug(bugId, bugTitle, bugDescription, projectId, this.testerId, )
//        testerDao.addNewBug();
    }
}
