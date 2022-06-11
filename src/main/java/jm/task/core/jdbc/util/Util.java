package jm.task.core.jdbc.util;

import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

public class Util {
    public static DataSource getDataSource() throws SQLException {
        MysqlDataSource mysqlDataSource = new MysqlDataSource();
        mysqlDataSource.setServerName("localhost");
        mysqlDataSource.setPortNumber(3306);
        mysqlDataSource.setDatabaseName("mydb");
        mysqlDataSource.setUser("rusy");
        mysqlDataSource.setPassword("Bro101Dude");
        return mysqlDataSource;
    }
    // реализуйте настройку соеденения с БД
}
