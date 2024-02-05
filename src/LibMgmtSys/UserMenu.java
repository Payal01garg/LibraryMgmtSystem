package LibMgmtSys;

import Library.database.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class UserMenu extends JFrame {

    private List<Book> libraryBooks; // List to store library books
    private List<Membership> memberships; // List to store memberships

    public UserMenu() {
        setTitle("Library Management System - User Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        libraryBooks = new ArrayList<>(); // Initialize the list
        memberships = new ArrayList<>(); // Initialize the membership list

        // Add some dummy book data for simplicity
        libraryBooks.add(new Book(1, "Animal Farm", "George Orwell", true));
        libraryBooks.add(new Book(2, "Baburnama", "Babur", true));

        JLabel lblWelcome = new JLabel("Welcome to the User Menu!");
        lblWelcome.setBounds(50, 50, 300, 25);
        add(lblWelcome);

        JButton btnViewBooks = new JButton("View Available Books");
        btnViewBooks.setBounds(50, 100, 150, 25);
        btnViewBooks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAvailableBooks();
            }
        });
        add(btnViewBooks);

        JButton btnAddBook = new JButton("Add a Book");
        btnAddBook.setBounds(220, 100, 150, 25);
        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        add(btnAddBook);

        JButton btnAddMembership = new JButton("Add Membership");
        btnAddMembership.setBounds(50, 150, 150, 25);
        btnAddMembership.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMembership();
            }
        });
        add(btnAddMembership);

        JButton btnViewMemberships = new JButton("View Memberships");
        btnViewMemberships.setBounds(220, 150, 150, 25);
        btnViewMemberships.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewMemberships();
            }
        });
        add(btnViewMemberships);

        JButton btnLogout = new JButton("Logout");
        btnLogout.setBounds(150, 200, 100, 25);
        btnLogout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        add(btnLogout);
    }
    private String username;  // Add a field to store the username

    public UserMenu(String username) {
        this.username = username;  // Store the username passed as an argument

        setTitle("Library Management System - User Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add your user menu components and functionality here
        JLabel welcomeLabel = new JLabel("Welcome, " + username + "!");
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(welcomeLabel, BorderLayout.CENTER);
    }
    private void viewAvailableBooks() {
        StringBuilder message = new StringBuilder("Available Books:\n");
        for (Book book : libraryBooks) {
            if (book.isAvailable()) {
                message.append(book.toString()).append("\n");
            }
        }
        JOptionPane.showMessageDialog(this, message.toString());
    }

    private void addBook() {
        String title = JOptionPane.showInputDialog(this, "Enter the title of the book:");
        String author = JOptionPane.showInputDialog(this, "Enter the author of the book:");

        // Generate a unique ID for the new book (you may need a more robust solution)
        int newBookId = libraryBooks.size() + 1;

        // Create a new Book object and add it to the libraryBooks list
        Book newBook = new Book(newBookId, title, author, true);
        libraryBooks.add(newBook);

        JOptionPane.showMessageDialog(this, "Book added successfully:\n" + newBook.toString());
    }

    private void addMembership() {
        String memberName = JOptionPane.showInputDialog(this, "Enter your name:");

        String[] durationOptions = {"6 months", "1 year", "2 years"};
        int selectedOption = JOptionPane.showOptionDialog(this, "Select membership duration:", "Membership Duration", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, durationOptions, durationOptions[0]);

        // Default duration is 6 months if none selected
        int durationMonths = selectedOption == 0 ? 6 : selectedOption == 1 ? 12 : 24;

        // Create a new Membership object and add it to the memberships list
        Membership newMembership = new Membership(memberName, durationMonths);
        memberships.add(newMembership);

        // Show a message with the added membership details
        JOptionPane.showMessageDialog(this, "Membership added successfully!\nMember Name: " + memberName + "\nDuration: " + durationMonths + " months");
    }


    private void viewMemberships() {
        StringBuilder message = new StringBuilder("Memberships:\n");
        for (Membership membership : memberships) {
            message.append("Member Name: ").append(membership.getMemberName()).append(", Duration: ").append(membership.getDurationMonths()).append(" months\n");
        }
        JOptionPane.showMessageDialog(this, message.toString());
    }


    private void logout() {
        int confirmLogout = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirmLogout == JOptionPane.YES_OPTION) {
            dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UserMenu().setVisible(true));
    }
}
