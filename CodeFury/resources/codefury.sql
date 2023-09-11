DROP DATABASE codefury;

CREATE DATABASE codefury;

USE codefury;

CREATE TABLE Employee(
    employeeId INT PRIMARY KEY,
    employeeName VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    dateOfJoining DATE,
    employeePassword VARCHAR(100)
);

CREATE TABLE ProjectManager(
    employeeId INT,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId)
);

CREATE TABLE Tester(
    employeeId INT PRIMARY KEY,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId)
);

CREATE TABLE Project(
    projectId INT PRIMARY KEY,
    projectName VARCHAR(25),
    projectManagerId INT,
    testerId INT,
    projectStatus INT,
    FOREIGN KEY (projectManagerId) REFERENCES ProjectManager(employeeId),
    FOREIGN KEY (testerId) REFERENCES Tester(employeeId)
);

CREATE TABLE Bugs(
    bugId INT,
    bugHeading VARCHAR(25),
    projectId INT,
    createdBy INT,
    assignedTo INT,
    openDate DATE,
    markForClosing VARCHAR(25),
    closedBy INT,
    closedOn DATE,
    bugStatus INT,
    severityLevel INT,
    FOREIGN KEY (projectId) REFERENCES Project(projectId),
    FOREIGN KEY (createdBy) REFERENCES Tester(employeeId),
    FOREIGN KEY (assignedTo) REFERENCES ProjectManager(employeeId),
    FOREIGN KEY (closedBy) REFERENCES ProjectManager(employeeId)
);

CREATE TABLE Developer(
    employeeId INT PRIMARY KEY,
    projectId INT,
    FOREIGN KEY (employeeId) REFERENCES Employee(employeeId),
    FOREIGN KEY (projectId) REFERENCES Project(projectId)
);