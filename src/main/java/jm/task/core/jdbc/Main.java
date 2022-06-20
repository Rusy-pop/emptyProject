package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 22);
        userService.saveUser("Petr", "Petrov", (byte) 22);
        userService.saveUser("Smir", "Smirnov", (byte) 22);
        userService.saveUser("Another", "Monkey", (byte) 22);
        userService.removeUserById(4);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
