package main;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {
    private final String userRole;

    public Dashboard(String userRole) {
        this.userRole = userRole;

        // Set frame properties
        setTitle("Dashboard - Metro POS System");
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Welcome to Metro POS System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create user role label
        JLabel roleLabel = new JLabel("Role: " + userRole, SwingConstants.CENTER);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        roleLabel.setForeground(Color.GRAY);
        mainPanel.add(roleLabel, BorderLayout.SOUTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(0, 1, 10, 10)); // Adjust grid for dynamic rows
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        // Add role-specific buttons
        switch (userRole.toLowerCase()) {
            case "superadmin":
                addSuperAdminButtons(buttonPanel);
                break;
            case "branchmanager":
                addBranchManagerButtons(buttonPanel);
                break;
            case "dataentryoperator":
                addDataEntryOperatorButtons(buttonPanel);
                break;
            case "cashier":
                addCashierButtons(buttonPanel);
                break;
            default:
                JLabel errorLabel = new JLabel("Invalid role: " + userRole, SwingConstants.CENTER);
                errorLabel.setForeground(Color.RED);
                buttonPanel.add(errorLabel);
        }

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
    }

    private void addSuperAdminButtons(JPanel panel) {
        JButton manageBranchesButton = createButton("Manage Branches");
        JButton reportsButton = createButton("View Reports");

        manageBranchesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Navigating to Branch Management (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        reportsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Generating Reports (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(manageBranchesButton);
        panel.add(reportsButton);
    }

    private void addBranchManagerButtons(JPanel panel) {
        JButton manageEmployeesButton = createButton("Manage Employees");
        JButton viewReportsButton = createButton("View Reports");

        manageEmployeesButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Navigating to Employee Management (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        viewReportsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Generating Reports (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(manageEmployeesButton);
        panel.add(viewReportsButton);
    }

    private void addDataEntryOperatorButtons(JPanel panel) {
        JButton manageVendorsButton = createButton("Manage Vendors");
        JButton manageProductsButton = createButton("Manage Products");

        manageVendorsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Navigating to Vendor Management (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        manageProductsButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Navigating to Product Management (Feature Coming Soon)", "Info", JOptionPane.INFORMATION_MESSAGE);
        });

        panel.add(manageVendorsButton);
        panel.add(manageProductsButton);
    }

    private void addCashierButtons(JPanel panel) {
        JButton processSalesButton = createButton("Process Sales");

        processSalesButton.addActionListener(e -> {
            new Cashier().setVisible(true); // Navigate to Cashier screen
            dispose();
        });

        panel.add(processSalesButton);
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(new Color(34, 139, 34)); // Dark green
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1, true));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(50, 205, 50)); // Lighter green
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(34, 139, 34)); // Dark green
            }
        });

        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Dashboard dashboard = new Dashboard("SuperAdmin"); // Change role for testing
            dashboard.setVisible(true);
        });
    }
}
