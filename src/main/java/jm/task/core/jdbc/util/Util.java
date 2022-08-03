package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Util {
    public void executeSQL(String sqlCommand) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/USERS", "root", "1234");) {
            Statement statement = conn.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
