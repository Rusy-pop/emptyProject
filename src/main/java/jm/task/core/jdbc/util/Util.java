package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.HashMap;

public class Util {

    public static Session getSession() {
        HashMap<String, String> settings = new HashMap<>();
        settings.put("connection.driver_class", "com.mysql.cj.jdbc.Driver");
        settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/mydb?createDatabaseIfNotExist=true");
        settings.put("hibernate.connection.username", "rusy");
        settings.put("hibernate.connection.password", "Some123Password");
        settings.put("dialect", "org.hibernate.dialect.MySQL8Dialect");
        settings.put("hbm2ddl.auto", "update");

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(settings).build();
        MetadataSources metadataSources = new MetadataSources(serviceRegistry).addAnnotatedClass(jm.task.core.jdbc.model.User.class);
        SessionFactory sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        return sessionFactory.openSession();
    }
    // реализуйте настройку соеденения с БД
}
