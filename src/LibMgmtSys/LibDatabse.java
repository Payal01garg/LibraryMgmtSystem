package LibMgmtSys;

import Library.database.Book;
import Library.database.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibDatabse {

    // Add your JDBC_URL, USERNAME, and PASSWORD constants here

    private static final String ADD_MEMBERSHIP_QUERY = "INSERT INTO memberships (member_name, duration_months) VALUES (?, ?)";
    private static final String GET_ALL_MEMBERSHIPS_QUERY = "SELECT * FROM memberships";

    // Retrieve all books from the database
    public static List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books");
             ResultSet resultSet = preparedStatement.executeQuery()) {

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

    // Add a book to the database
    public static boolean addBook(Book book) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (title, author, available) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setBoolean(3, book.isAvailable());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Add a membership to the database
    public static boolean addMembership(Membership membership) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_MEMBERSHIP_QUERY)) {

            preparedStatement.setString(1, membership.getMemberName());
            preparedStatement.setInt(2, membership.getDurationMonths());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all memberships from the database
    public static List<Membership> getAllMemberships() {
        List<Membership> memberships = new ArrayList<>();

        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_MEMBERSHIPS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int membershipNo = resultSet.getInt("membership_no");
                String memberName = resultSet.getString("member_name");
                int durationMonths = resultSet.getInt("duration_months");

                Membership membership = new Membership(memberName, durationMonths);

                memberships.add(membership);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return memberships;
    }
    public static boolean addUser(User user) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)")) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    // Check if a user with the given username already exists
    public static boolean isUserExists(String username) {
        try (Connection connection = DbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {

            preparedStatement.setString(1, username);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // Returns true if a user with the given username exists
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
