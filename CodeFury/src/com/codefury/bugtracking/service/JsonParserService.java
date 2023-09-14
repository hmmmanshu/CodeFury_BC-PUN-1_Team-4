package com.codefury.bugtracking.service;

/**
 * Interface for parsing JSON files.
 */
public interface JsonParserService {
    /**
     * Parses a JSON file given its file path.
     *
     * @param filePath The path to the JSON file to be parsed.
     */
    void parseJsonFile(String filePath);
}
