package util.application;

import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import util.menu.FirstMenu;
import util.menu.SecondMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        EntityManagerFactory entityManagerFactory = HibernateUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        User token = FirstMenu.menu();
        SecondMenu.menu(token);

    }
}
