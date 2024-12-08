package main;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashScreen extends JFrame {
    private static final Logger LOGGER = Logger.getLogger(SplashScreen.class.getName());
    private final JProgressBar progressBar;

    public SplashScreen() {
        // Set the title and default close operation
        setTitle("Metro POS System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window on the screen
        setUndecorated(true); // Remove window borders

        // Create a panel for the splash screen
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);

        // Add a label for the logo or title
        JLabel titleLabel = new JLabel("Metro POS System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(new Color(34, 139, 34)); // Dark green color
        panel.add(titleLabel, BorderLayout.CENTER);

        // Add a progress bar
        progressBar = new JProgressBar();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(34, 139, 34)); // Match title color
        panel.add(progressBar, BorderLayout.SOUTH);

        add(panel);
    }

    public void showSplash() {
        setVisible(true);

        // Simulate loading process
        for (int i = 0; i <= 100; i++) {
            try {
                Thread.sleep(30); // Adjust speed of the progress bar
                progressBar.setValue(i);
            } catch (InterruptedException e) {
                LOGGER.log(Level.SEVERE, "An error occurred during splash screen loading", e);
            }
        }

        // After loading is complete, close splash screen and open login
        dispose();
        new Login().setVisible(true); // Replace with your Login screen
    }

    public static void main(String[] args) {
        // Configure logging level
        LOGGER.setLevel(Level.ALL);

        // Run the splash screen
        SplashScreen splash = new SplashScreen();
        splash.showSplash();
    }
}
