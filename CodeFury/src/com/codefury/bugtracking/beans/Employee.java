package com.codefury.bugtracking.beans;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Abstract class representing an Employee in the organization.
 */
public abstract class Employee {
    private static int employeeIdCounter = 1;
    private int employeeId;
    private String name;
    private String email;
    private Date dateOfJoining;
    private String password;

    /**
     * Default constructor for Employee. Initializes fields and assigns an employee ID.
     */
    public Employee() {
        this.employeeId = employeeIdCounter++;
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.dateOfJoining = java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    /**
     * Constructor to create an Employee with a name.
     *
     * @param name The name of the employee.
     */
    public Employee(String name) {
        this.employeeId = employeeIdCounter++;
        this.name = name;
        LocalDateTime currentDateTime = LocalDateTime.now();
        this.dateOfJoining = java.util.Date.from(currentDateTime.atZone(java.time.ZoneId.systemDefault()).toInstant());
    }

    /**
     * Get the unique identifier of the employee.
     *
     * @return The employee ID.
     */
    public int getEmployeeId() {
        return employeeId;
    }

    /**
     * Set the name of the employee.
     *
     * @param name The name to set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the name of the employee.
     *
     * @return The name of the employee.
     */
    public String getName() {
        return name;
    }

    /**
     * Get the email address of the employee.
     *
     * @return The email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email address of the employee.
     *
     * @param email The email address to set.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the date of joining of the employee.
     *
     * @return The date of joining.
     */
    public Date getDateOfJoining() {
        return dateOfJoining;
    }

    /**
     * Set the date of joining of the employee.
     *
     * @param dateOfJoining The date of joining to set.
     */
    public void setDateOfJoining(Date dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }

    /**
     * Get the password of the employee.
     *
     * @return The password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the employee.
     *
     * @param password The password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns a string representation of this Employee object.
     *
     * @return A string containing the Employee's fields and their values.
     */
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
