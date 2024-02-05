package Library.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnector {

    private static final String JDBC_URL = "jdbc:mysql:///lms";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "payal@123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
    }
}


