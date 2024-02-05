package Library.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Userdao {


            private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE username = ? AND password = ?";

            public static boolean validateUser(String username, String password) {
                try (Connection connection = DbConnector.getConnection();
                     PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_QUERY)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    ResultSet resultSet = preparedStatement.executeQuery();
                    return resultSet.next(); // Returns true if a matching user is found

                } catch (SQLException e) {
                    e.printStackTrace();
                    return false;
                }
            }
        }




