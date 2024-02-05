package LibMgmtSys;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LibMgmtSys {


        public static void main(String[] args) {
            // Initialize the database connection
            try {
               // Load the JDBC driver
                // Connect to the database
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql:///lms",
                        "root",
                        "payal@123"
                );
                // Check if the connection is successful
                if (connection != null) {
                    JOptionPane.showMessageDialog(null, "Connected to the database!");
                    // Open the login frame
                    SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
                } else {
                    JOptionPane.showMessageDialog(null, "Error connecting to the database. Please check your connection.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
            }
        }
    }

