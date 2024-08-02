package repository.impl;

import config.ConnectionDB;
import entity.TransAction;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.UserRepository;
import util.application.HibernateUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    public static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
    private final EntityManager entityManager = emf.createEntityManager();
    @Override
    public User insert(User user){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            return user;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in insert in userRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User select(String userName, String password){
        EntityManager entityManager = emf.createEntityManager();
        try {
            User resultList = entityManager.createQuery("from User where userName=?1 and password=?2", User.class)
                    .setParameter(1, userName).setParameter(2,password).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(username,password) in userRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void updateUserName(User user, String user_name){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE User set userName=?1  where id=?2", User.class)
                    .setParameter(1, user_name).setParameter(2,user.getId()).executeUpdate();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in update(username) in UserRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void updatePassword(User user, String password){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE User set password=?1  where id=?2", User.class)
                    .setParameter(1, password).setParameter(2,user.getId()).executeUpdate();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in update(password) in UserRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void delete(String userName, String password){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM User where userName=?1 and password=?2", TransAction.class)
                    .setParameter(1, userName).setParameter(2,password).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete(username,password) in transactionRepositoryImpl: " + e.getMessage());
        }
    }
}
