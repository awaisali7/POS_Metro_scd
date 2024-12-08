package utils;

import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler;

import java.util.Map;

public class ReportGenerator {

    // Generate a bar chart for sales data
    public static void generateBarChart(String title, Map<String, Double> data, String xAxisLabel, String yAxisLabel) {
        // Create the chart
        XYChart chart = new XYChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .xAxisTitle(xAxisLabel)
                .yAxisTitle(yAxisLabel)
                .build();

        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);

        // Add data to the chart
        double[] xData = new double[data.size()];
        double[] yData = new double[data.size()];

        int i = 0;
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            xData[i] = i + 1; // Use index for x-axis
            yData[i] = entry.getValue();
            i++;
        }
        chart.addSeries("Sales", xData, yData);

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }

    // Generate a pie chart for product distribution
    public static void generatePieChart(String title, Map<String, Double> data) {
        // Create the chart
        PieChart chart = new PieChartBuilder()
                .width(800)
                .height(600)
                .title(title)
                .build();

        // Add data to the chart
        for (Map.Entry<String, Double> entry : data.entrySet()) {
            chart.addSeries(entry.getKey(), entry.getValue());
        }

        // Show the chart
        new SwingWrapper<>(chart).displayChart();
    }
}
