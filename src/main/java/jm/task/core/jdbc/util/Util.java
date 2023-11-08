package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties properties = new Properties();
                properties.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                properties.put(Environment.URL, "jdbc:mysql://localhost:3306/mydb");
                properties.put(Environment.USER, "root");
                properties.put(Environment.PASS, "root");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                configuration.setProperties(properties).addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getConnection() {
        final String HOSTNAME = "jdbc:mysql://localhost:3306/mydb";
        final String USERNAME = "root";
        final String PASS = "root";
        {
            try {
                Driver driver = new com.mysql.cj.jdbc.Driver();
                DriverManager.registerDriver(driver);
                return DriverManager.getConnection(HOSTNAME, USERNAME, PASS);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
