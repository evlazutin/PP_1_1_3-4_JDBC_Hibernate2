package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        Util.getSessionFactory().openSession();
        UserDao userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Ivanov", (byte) 10);
        userDao.saveUser("Dmitriy", "Ivanov", (byte) 18);
        userDao.saveUser("Marina", "Ivanova", (byte) 22);
        userDao.getAllUsers();
        userDao.removeUserById(2);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}