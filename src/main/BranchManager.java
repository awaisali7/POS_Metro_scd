package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BranchManager extends JFrame {
    private final List<Employee> employees = new ArrayList<>();
    private final String branchCode;

    public BranchManager(String branchCode) {
        this.branchCode = branchCode;

        // Set frame properties
        setTitle("Branch Manager - Metro POS System");
        setSize(600, 400);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add title label
        JLabel titleLabel = new JLabel("Branch Manager Dashboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create buttons panel
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Add buttons
        JButton addEmployeeButton = new JButton("Add Employee");
        JButton viewEmployeesButton = new JButton("View Employees");
        buttonPanel.add(addEmployeeButton);
        buttonPanel.add(viewEmployeesButton);

        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);

        // Action listeners
        addEmployeeButton.addActionListener(e -> showAddEmployeeDialog());
        viewEmployeesButton.addActionListener(e -> showEmployeesList());
    }

    private void showAddEmployeeDialog() {
        // Dialog for adding an employee
        JDialog dialog = new JDialog(this, "Add Employee", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new GridLayout(6, 2, 10, 10));

        // Form fields
        JTextField empIdField = new JTextField();
        JTextField nameField = new JTextField();
        JComboBox<String> roleComboBox = new JComboBox<>(new String[]{"Cashier", "Data Entry Operator"});
        JTextField emailField = new JTextField();
        JTextField salaryField = new JTextField();

        // Add components
        dialog.add(new JLabel("Employee ID:"));
        dialog.add(empIdField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Role:"));
        dialog.add(roleComboBox);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(new JLabel("Salary:"));
        dialog.add(salaryField);

        // Buttons
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        dialog.add(saveButton);
        dialog.add(cancelButton);

        // Save button action
        saveButton.addActionListener(e -> {
            String empId = empIdField.getText();
            String name = nameField.getText();
            String role = (String) roleComboBox.getSelectedItem();
            String email = emailField.getText();
            String salaryText = salaryField.getText();

            if (empId.isEmpty() || name.isEmpty() || role.isEmpty() || email.isEmpty() || salaryText.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    double salary = Double.parseDouble(salaryText);
                    employees.add(new Employee(empId, name, role, email, branchCode, salary));
                    JOptionPane.showMessageDialog(dialog, "Employee added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(dialog, "Invalid salary value.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Cancel button action
        cancelButton.addActionListener(e -> dialog.dispose());

        dialog.setVisible(true);
    }

    private void showEmployeesList() {
        // Display list of employees
        StringBuilder employeeInfo = new StringBuilder("Employees:\n\n");
        for (Employee employee : employees) {
            employeeInfo.append(employee).append("\n");
        }

        if (employees.isEmpty()) {
            employeeInfo.append("No employees added yet.");
        }

        JOptionPane.showMessageDialog(this, employeeInfo.toString(), "Employee List", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BranchManager manager = new BranchManager("BR001");
            manager.setVisible(true);
        });
    }
}

// Helper class to represent an Employee
class Employee {
    private final String empId;
    private final String name;
    private final String role;
    private final String email;
    private final String branchCode;
    private final double salary;

    public Employee(String empId, String name, String role, String email, String branchCode, double salary) {
        this.empId = empId;
        this.name = name;
        this.role = role;
        this.email = email;
        this.branchCode = branchCode;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %s, Name: %s, Role: %s, Email: %s, Branch: %s, Salary: %.2f",
                empId, name, role, email, branchCode, salary);
    }
}
