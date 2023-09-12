package com.codefury.bugtracking.beans;

import java.time.LocalDateTime;
import java.util.Date;

public abstract class Employee {
    private static int employeeIdCounter = 1;
    private int employeeId;
    private String name;
    private String email;
    private Date dateOfJoining;
    private String password;

    public Employee() {
        this.employeeId = employeeIdCounter++;
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.dateOfJoining = java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    public Employee(String name) {
        this.employeeId = employeeIdCounter++;
        this.name = name;
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.dateOfJoining = java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", dateOfJoining=" + dateOfJoining +
                '}';
    }


}
