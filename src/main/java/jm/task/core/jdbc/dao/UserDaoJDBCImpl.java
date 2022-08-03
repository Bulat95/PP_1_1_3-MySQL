package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Util util = new Util();
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String sqlCommand = "CREATE TABLE IF NOT EXISTS User (Id INT (11) PRIMARY KEY NOT NULL AUTO_INCREMENT, name VARCHAR(20), lastName VARCHAR(20), age INT)";
        util.executeSQL(sqlCommand);
    }

    public void dropUsersTable() {
        String sqlCommand = "DROP TABLE IF EXISTS User";
        util.executeSQL(sqlCommand);
    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println("User с именем Ц " + name + " добавлен в базу данных");
        String sqlCommand = String.format("INSERT INTO USER (name, lastname, age) VALUES ('%s', '%s', %d)", name, lastName, age);
        util.executeSQL(sqlCommand);
    }

    public void removeUserById(long id) {
        String sqlCommand = "DELETE FROM USER WHERE Id = " + id + "";
        util.executeSQL(sqlCommand);
    }

    public List<User> getAllUsers() {
        List <User> lu = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/USERS", "root", "1234");) {
            Statement statement = conn.createStatement();
            ResultSet res = statement.executeQuery("SELECT * FROM USER");
            while (res.next()) {
                int i = res.getInt("Id");
                String name = res.getString("name");
                String lastName = res.getString("lastName");
                int age = res.getInt("age");

                User user = new User(name, lastName, (byte)age);
                System.out.println(user.toString());
                lu.add(user);

            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return lu;
    }

    public void cleanUsersTable() {
        String sqlCommand = "TRUNCATE TABLE USER";
        util.executeSQL(sqlCommand);
    }
}
