package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.service.JsonParserService;
import com.codefury.bugtracking.service.JsonParserServiceImpl;

import java.util.Scanner;

/**
 * Implementation of the ImportJsonUserInterface for importing JSON data.
 */
public class ImportJsonUserInterfaceImpl implements ImportJsonUserInterface {
    /**
     * Method to select and parse a JSON file.
     */
    @Override
    public void selectJsonFile() {
        Scanner scanner;
        JsonParserService jsonParserService;
        jsonParserService = new JsonParserServiceImpl();
        scanner = new Scanner(System.in);
        String filePath;

        // Prompt for user input
        System.out.println("Enter JSON file path");
        filePath = scanner.nextLine();

        // Parse the JSON file
        jsonParserService.parseJsonFile(filePath);
    }
}
