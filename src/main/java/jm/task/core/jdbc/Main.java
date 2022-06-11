package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl daoJDBC = new UserServiceImpl();

        daoJDBC.createUsersTable();
        daoJDBC.saveUser("Ivan", "Ivanov", (byte) 22);
        daoJDBC.saveUser("Petr", "Petrov", (byte) 22);
        daoJDBC.saveUser("Smir", "Smirnov", (byte) 22);
        daoJDBC.saveUser("Another", "Monkey", (byte) 22);
        daoJDBC.getAllUsers().forEach(System.out::println);
        daoJDBC.cleanUsersTable();
        daoJDBC.dropUsersTable();
    }
}
