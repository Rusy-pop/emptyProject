package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();
    private static final String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS user (" +
            " `id` BIGINT NOT NULL AUTO_INCREMENT," +
            " `name` VARCHAR(15) NULL," +
            " `lastName` VARCHAR(15) NULL," +
            " `age` TINYINT NULL," +
            " PRIMARY KEY (`id`)," +
            " UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS user;";
    private static final String SAVE_USER = "INSERT INTO user(name, lastname, age)" +
            "VALUES (?, ?, ?);";
    private static final String REMOVE_BY_ID = "DELETE FROM user WHERE id = ?;";

    private static final String GET_ALL_USERS = "SELECT * FROM user;";
    private static final String CLEAN_TABLE = "TRUNCATE user;";

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(DROP_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement statement = connection.prepareStatement(SAVE_USER)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement(REMOVE_BY_ID)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(GET_ALL_USERS)) {

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
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(CLEAN_TABLE);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
