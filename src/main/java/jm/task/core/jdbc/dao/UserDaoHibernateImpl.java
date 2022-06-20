package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                " `id` BIGINT NOT NULL AUTO_INCREMENT," +
                " `name` VARCHAR(15) NULL," +
                " `lastName` VARCHAR(15) NULL," +
                " `age` TINYINT NULL," +
                " PRIMARY KEY (`id`)," +
                " UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";

        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();
        transaction.commit();
        session.close();

    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF EXISTS user";
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try {
            Session session = Util.getSession();
            Transaction transaction = session.beginTransaction();
            User user = new User();
            user.setName(name);
            user.setLastName(lastName);
            user.setAge(age);
            session.persist(user);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.find(User.class, id);
        if (user != null) {
            session.remove(user);
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        return Util.getSession().createQuery("SELECT u FROM User u").getResultList();
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();
    }
}
