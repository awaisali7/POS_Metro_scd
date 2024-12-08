package main.utils;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.Map;

public class ReportGenerator {

    // Generate a bar chart for sales data
    public static void generateBarChart(String title, Map<String, Double> data, String categoryAxisLabel, String valueAxisLabel) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Sales", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createBarChart(
                title,
                categoryAxisLabel,
                valueAxisLabel,
                dataset
        );

        // Display the chart in a JFrame
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.setVisible(true);
    }

    // Generate a pie chart for product distribution
    public static void generatePieChart(String title, Map<String, Double> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Map.Entry<String, Double> entry : data.entrySet()) {
            dataset.addValue(entry.getValue(), "Products", entry.getKey());
        }

        JFreeChart chart = ChartFactory.createPieChart(title, dataset, true, true, false);

        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        ChartPanel chartPanel = new ChartPanel(chart);
        frame.add(chartPanel);
        frame.setVisible(true);
    }
}
