// SignUp.java
package LibMgmtSys;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUp extends JFrame {

    private JTextField signUpUsernameField;
    private JPasswordField signUpPasswordField;
    private LoginMenu loginMenu;

    public SignUp(LoginMenu loginMenu) {
        this.loginMenu = loginMenu;

        setTitle("Library Management System - Sign Up");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only the sign-up frame
        setLayout(null);

        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(50, 50, 80, 25);
        add(lblUsername);

        signUpUsernameField = new JTextField();
        signUpUsernameField.setBounds(150, 50, 150, 25);
        add(signUpUsernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(50, 100, 80, 25);
        add(lblPassword);

        signUpPasswordField = new JPasswordField();
        signUpPasswordField.setBounds(150, 100, 150, 25);
        add(signUpPasswordField);

        JButton btnSignUp = new JButton("Sign Up");
        btnSignUp.setBounds(150, 150, 80, 25);
        btnSignUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                performSignUp();
            }
        });
        add(btnSignUp);
    }

    private void performSignUp() {
        String username = signUpUsernameField.getText();
        char[] password = signUpPasswordField.getPassword();

        if (isUsernameTaken(username)) {
            JOptionPane.showMessageDialog(this, "Username already taken. Please choose another one.");
        } else {
            // Add the new user to the user list (replace this with database logic in a real application)
            User newUser = new User(username, new String(password));
            LibDatabse.addUser(newUser); // Corrected class name

            JOptionPane.showMessageDialog(this, "Sign Up successful!\nUsername: " + username);
            dispose(); // Close the sign-up frame
            loginMenu.signIn(username, password); // Automatically sign in after sign up
        }

        // Clear fields after sign-up attempt
        signUpUsernameField.setText("");
        signUpPasswordField.setText("");
    }

    private boolean isUsernameTaken(String username) {
        // Check if the username already exists in the user list (replace this with database logic in a real application)
        return LibDatabse.isUserExists(username); // Corrected class name
    }
}
