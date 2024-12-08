package utils;

import java.util.HashMap;
import java.util.Map;

public class ReportGeneratorTest {
    public static void main(String[] args) {
        Map<String, Double> salesData = new HashMap<>();
        salesData.put("Product A", 5000.0);
        salesData.put("Product B", 3000.0);
        salesData.put("Product C", 2000.0);

        // Generate bar chart
        ReportGenerator.generateBarChart("Sales Report", salesData, "Products", "Sales");

        // Generate pie chart
        ReportGenerator.generatePieChart("Product Distribution", salesData);
    }
}
