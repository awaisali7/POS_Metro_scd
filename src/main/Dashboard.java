package main;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private final String userRole;

    public Dashboard(String userRole) {
        this.userRole = userRole;

        // Set frame properties
        setTitle("Dashboard - Metro POS System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Welcome to Metro POS System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add role-specific buttons
        if ("SuperAdmin".equalsIgnoreCase(userRole)) {
            addSuperAdminButtons(buttonPanel);
        } else if ("BranchManager".equalsIgnoreCase(userRole)) {
            addBranchManagerButtons(buttonPanel);
        } else if ("DataEntryOperator".equalsIgnoreCase(userRole)) {
            addDataEntryOperatorButtons(buttonPanel);
        } else if ("Cashier".equalsIgnoreCase(userRole)) {
            addCashierButtons(buttonPanel);
        } else {
            JLabel errorLabel = new JLabel("Invalid role: " + userRole, SwingConstants.CENTER);
            errorLabel.setForeground(Color.RED);
            buttonPanel.add(errorLabel);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void addSuperAdminButtons(JPanel panel) {
        JButton manageBranchesButton = new JButton("Manage Branches");
        JButton reportsButton = new JButton("View Reports");

        manageBranchesButton.addActionListener(e -> {
            new SuperAdmin().setVisible(true); // Navigate to SuperAdmin screen
            dispose();
        });

        reportsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reports functionality coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(manageBranchesButton);
        panel.add(reportsButton);
    }

    private void addBranchManagerButtons(JPanel panel) {
        JButton manageEmployeesButton = new JButton("Manage Employees");
        JButton viewReportsButton = new JButton("View Reports");

        manageEmployeesButton.addActionListener(e -> {
            new BranchManager("BR001").setVisible(true); // Replace "BR001" with the branch code
            dispose();
        });

        viewReportsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Reports functionality coming soon!", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(manageEmployeesButton);
        panel.add(viewReportsButton);
    }

    private void addDataEntryOperatorButtons(JPanel panel) {
        JButton manageVendorsButton = new JButton("Manage Vendors");
        JButton manageProductsButton = new JButton("Manage Products");

        manageVendorsButton.addActionListener(e -> {
            new DataEntryOperator().setVisible(true); // Navigate to DataEntryOperator screen
            dispose();
        });

        manageProductsButton.addActionListener(e -> {
            new DataEntryOperator().setVisible(true); // Navigate to product management
            dispose();
        });

        panel.add(manageVendorsButton);
        panel.add(manageProductsButton);
    }

    private void addCashierButtons(JPanel panel) {
        JButton processSalesButton = new JButton("Process Sales");

        processSalesButton.addActionListener(e -> {
            new Cashier().setVisible(true); // Navigate to Cashier screen
            dispose();
        });

        panel.add(processSalesButton);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard("SuperAdmin"); // Change role for testing
            dashboard.setVisible(true);
        });
    }
}
