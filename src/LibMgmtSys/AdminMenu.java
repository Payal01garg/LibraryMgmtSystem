package LibMgmtSys;

import Library.database.Book;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class AdminMenu extends JFrame {

        public AdminMenu() {
            setTitle("Admin Menu");
            setSize(400, 300);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(textArea);

            add(scrollPane, BorderLayout.CENTER);

            // Display some admin-related information
            textArea.setText("Admin Menu\n");
            textArea.append("1. Add Book\n");
            textArea.append("2. View Books\n");

            JButton btnAddBook = new JButton("Add Book");
            btnAddBook.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    addBook();
                }
            });
            add(btnAddBook, BorderLayout.SOUTH);

            JButton btnViewBooks = new JButton("View Books");
            btnViewBooks.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    viewBooks();
                }
            });
            add(btnViewBooks, BorderLayout.NORTH);
        }

        private void addBook() {
            String title = JOptionPane.showInputDialog("Enter Book Title:");
            String author = JOptionPane.showInputDialog("Enter Book Author:");
            boolean available = true;  // Assuming the book is initially available

        }

        private void viewBooks() {
            JTextArea textArea = new JTextArea();
            textArea.setEditable(false);

            // Retrieve book information from the database
            List<Book> books = LibDatabse.getAllBooks();

            // Display book information in the text area
            for (Book book : books) {
                textArea.append(book.toString() + "\n");
            }

            JScrollPane scrollPane = new JScrollPane(textArea);

            JFrame viewBooksFrame = new JFrame("View Books");
            viewBooksFrame.setSize(400, 300);
            viewBooksFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            viewBooksFrame.setLayout(new BorderLayout());
            viewBooksFrame.add(scrollPane, BorderLayout.CENTER);
            viewBooksFrame.setVisible(true);
        }

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> new AdminMenu().setVisible(true));
        }
    }
