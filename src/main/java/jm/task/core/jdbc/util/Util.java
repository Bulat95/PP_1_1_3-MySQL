package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public void executeSQL(String sqlCommand) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/USERS", "root", "1234");) {
            //Statement заменил на PreparedStatement
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
