package com.codefury.bugtracking.userinterface;

import com.codefury.bugtracking.service.JsonParserService;
import com.codefury.bugtracking.service.JsonParserServiceImpl;

import java.util.Scanner;

public class ImportJsonUserInterfaceImpl implements ImportJsonUserInterface {
    @Override
    public void selectJsonFile() {
        Scanner scanner;
        JsonParserService jsonParserService;
        jsonParserService = new JsonParserServiceImpl();
        scanner = new Scanner(System.in);
        String filePath;
        System.out.println("Enter json file path");
        filePath = scanner.nextLine();
        jsonParserService.parseJsonFile(filePath);
    }
}
