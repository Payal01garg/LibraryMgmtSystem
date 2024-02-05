package LibMgmtSys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginMenu extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private SignUp signUpMenu;
    private List<User> users;

    public LoginMenu() {
        setTitle("Library Management System - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        users = new ArrayList<>();
        users.add(new User("admin", "admin123"));
        users.add(new User("user", "user123"));

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 80, 25);
        add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(150, 50, 150, 25);
        add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 80, 25);
        add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 100, 150, 25);
        add(passwordField);

        JButton btnLogin = new JButton("Login");
        btnLogin.setBounds(150, 150, 80, 25);
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performLogin();
            }
        });
        add(btnLogin);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(250, 150, 80, 25);
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openSignUpMenu();
            }
        });
        add(btnSignUp);

        signUpMenu = new SignUp(this);
    }

    private void performLogin() {
        String username = usernameField.getText().trim();
        char[] password = passwordField.getPassword();
        String enteredPassword = new String(password).trim();

        boolean validCredentials = false;
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(enteredPassword)) {
                validCredentials = true;
                break;
            }
        }

        if (validCredentials) {
            JOptionPane.showMessageDialog(this, "Login successful!");

            SwingUtilities.invokeLater(() -> {
                if ("admin".equals(username)) {
                    new AdminMenu().setVisible(true);
                } else {
                    UserMenu userMenu = new UserMenu(username);
                    userMenu.setVisible(true); // Make the UserMenu visible
                }
                this.setVisible(false); // Hide the LoginMenu
            });

        } else {
            int option = JOptionPane.showConfirmDialog(this, "Invalid credentials. Do you want to sign up?", "Sign Up", JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                signUpMenu.setVisible(true);
            }
        }

        // Clear fields after login attempt
        usernameField.setText("");
        passwordField.setText("");
    }

    public void signIn(String username, char[] password) {
        passwordField.setText(new String(password).trim());
        usernameField.setText(username.trim());
        performLogin();
    }

    private void openSignUpMenu() {
        signUpMenu.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginMenu().setVisible(true));
    }
}
