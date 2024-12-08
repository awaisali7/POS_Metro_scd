package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OfflineModeHandler {
    private static final String OFFLINE_DATA_FILE = "data.txt";
    private static final String STATUS_FILE = "status.txt";

    public void saveOfflineData(String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OFFLINE_DATA_FILE, true))) {
            writer.write(data);
            writer.newLine();
            System.out.println("Data saved offline.");
        } catch (IOException e) {
            System.err.println("Failed to save offline data: " + e.getMessage());
        }
    }

    public List<String> loadOfflineData() {
        List<String> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(OFFLINE_DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            System.out.println("Offline data loaded.");
        } catch (IOException e) {
            System.err.println("Failed to load offline data: " + e.getMessage());
        }
        return data;
    }

    public void setOfflineStatus(boolean isOffline) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STATUS_FILE))) {
            writer.write(isOffline ? "true" : "false");
            System.out.println("Offline status updated.");
        } catch (IOException e) {
            System.err.println("Failed to update offline status: " + e.getMessage());
        }
    }

    public boolean isOffline() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STATUS_FILE))) {
            String status = reader.readLine();
            return "true".equalsIgnoreCase(status);
        } catch (IOException e) {
            System.err.println("Failed to read offline status: " + e.getMessage());
        }
        return false;
    }

    public void clearOfflineData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OFFLINE_DATA_FILE))) {
            writer.write(""); // Clear the file
            System.out.println("Offline data cleared.");
        } catch (IOException e) {
            System.err.println("Failed to clear offline data: " + e.getMessage());
        }
    }
}
