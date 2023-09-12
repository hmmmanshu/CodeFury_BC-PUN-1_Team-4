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

public class JsonParserServiceImpl implements JsonParserService {

    @Override
    public void parseJsonFile(String filePath) {

        JsonParserDao jsonParserDao = new JsonParserDaoImpl();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }

            String jsonString = jsonContent.toString();
            String[] jsonObjects = jsonString.split("\\},\\s*\\{");

            for (String jsonObject : jsonObjects) {
                jsonObject = jsonObject.replace("{", "").replace("}", "");
                String[] keyValuePairs = jsonObject.split(",\\s*");

                String name = null, type = null, email = null;

                for (String pair : keyValuePairs) {
                    String[] keyValue = pair.split(":\\s*");
                    String key = keyValue[0].replace("\"", "").trim();
                    String value = keyValue[1].replace("\"", "").trim();

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
