package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String query = "CREATE TABLE IF NOT EXISTS user (" +
                " `id` BIGINT NOT NULL AUTO_INCREMENT," +
                " `name` VARCHAR(15) NULL," +
                " `lastName` VARCHAR(15) NULL," +
                " `age` TINYINT NULL," +
                " PRIMARY KEY (`id`)," +
                " UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";

        try (Connection connection = Util.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table created");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        String query = "DROP TABLE IF EXISTS user;";
        try (Connection connection = Util.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String query = "INSERT INTO user (" +
                "name, lastName, age) VALUES (?,?,?);";
        try (Connection connection = Util.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User saved");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        String query = "DELETE FROM user WHERE id = ?;";

        try (Connection connection = Util.getDataSource().getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User deleted");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();
        String query = "SELECT * FROM user;";
        try (Connection connection = Util.getDataSource().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(id);
                userList.add(user);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

    public void cleanUsersTable() {
        String query = "DELETE FROM user;";
        try (Connection connection = Util.getDataSource().getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            System.out.println("Table cleaned");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
