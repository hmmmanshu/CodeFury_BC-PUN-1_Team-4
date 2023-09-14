package com.codefury.bugtracking.dao;

import com.codefury.bugtracking.beans.Employee;

/**
 * An interface for JSON data parsing and storage related operations.
 */
public interface JsonParserDao {

    /**
     * Saves employee data in JSON format.
     *
     * @param employee The employee object to be saved in JSON format.
     */
    void saveEmployeeData(Employee employee);
}
