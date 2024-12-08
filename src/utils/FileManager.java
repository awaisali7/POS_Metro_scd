package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    // Write data to a file
    public static void writeToFile(String filePath, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Data written to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to write to file: " + e.getMessage());
        }
    }

    // Read data from a file
    public static List<String> readFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            System.out.println("Data read from file: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to read from file: " + e.getMessage());
        }
        return lines;
    }

    // Clear the content of a file
    public static void clearFile(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(""); // Write empty content to clear the file
            System.out.println("File cleared: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to clear file: " + e.getMessage());
        }
    }
}
