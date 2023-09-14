package com.codefury.bugtracking.service;

import com.codefury.bugtracking.beans.Tester;
import com.codefury.bugtracking.beans.Employee;
import com.codefury.bugtracking.beans.Developer;
import com.codefury.bugtracking.dao.JsonParserDao;
import com.codefury.bugtracking.beans.ProjectManager;
import com.codefury.bugtracking.dao.JsonParserDaoImpl;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;

/**
 * Service class for parsing JSON data from a file and saving it as employee objects.
 */
public class JsonParserServiceImpl implements JsonParserService {

    /**
     * Parses JSON data from a file and saves it as employee objects.
     *
     * @param filePath The path to the JSON file to be parsed.
     */
    @Override
    public void parseJsonFile(String filePath) {

        // Create an instance of the JsonParserDao to save employee data
        JsonParserDao jsonParserDao = new JsonParserDaoImpl();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            // Read the JSON file line by line and store it in a StringBuilder
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            String jsonString = jsonContent.toString();
            String[] jsonObjects = jsonString.split("\\},\\s*\\{");

            // Loop through each JSON object
            for (String jsonObject : jsonObjects) {
                jsonObject = jsonObject.replace("{", "").replace("}", "");
                String[] keyValuePairs = jsonObject.split(",\\s*");

                String name = null, type = null, email = null;

                // Extract key-value pairs from the JSON object
                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split(":\\s*");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

                    // Parse the relevant key-value pairs
                    switch (key) {
                        case "name":
                            name = value;
                            break;
                        case "type":
                            type = value;
                            break;
                        case "email":
                            email = value;
                            break;
                    }
                }

                Employee employee;
                // Create employee objects based on the 'type' field in the JSON
                if (type != null) {
                    switch (type) {
                        case "Developer":
                            employee = new Developer(name, email);
                            break;
                        case "Tester":
                            employee = new Tester(name, email);
                            break;
                        case "Project Manager":
                            employee = new ProjectManager(name, email);
                            break;
                        default:
                            continue;
                    }
                    // Save the employee object using the JsonParserDao
                    jsonParserDao.saveEmployeeData(employee);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
