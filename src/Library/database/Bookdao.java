package Library.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Bookdao {

        private static final String SELECT_ALL_BOOKS_QUERY = "SELECT * FROM books";
        private static final String INSERT_BOOK_QUERY = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";

        public static List<Book> getAllBooks() {
            List<Book> books = new ArrayList<>();

            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS_QUERY)) {

                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String title = resultSet.getString("title");
                    String author = resultSet.getString("author");
                    boolean available = resultSet.getBoolean("available");

                    Book book = new Book(id, title, author, available);
                    books.add(book);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return books;
        }

        public static boolean addBook(String title, String author, boolean available) {
            try (Connection connection = DbConnector.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK_QUERY)) {

                preparedStatement.setString(1, title);
                preparedStatement.setString(2, author);
                preparedStatement.setBoolean(3, available);

                int rowsAffected = preparedStatement.executeUpdate();
                return rowsAffected > 0; // Returns true if the book is added successfully

            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


