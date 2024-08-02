package repository.impl;

import config.ConnectionDB;
import entity.Account;
import entity.Bank;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.BankRepository;
import util.application.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepository {
    public static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
    private final EntityManager entityManager = emf.createEntityManager();
    @Override
    public void insert(Bank bank) throws SQLException {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(bank);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in insert in BankRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<Bank> selectAll(String name) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<Bank> resultList = entityManager.createQuery("from Bank where name=?1", Bank.class).setParameter(1, name).getResultList();
            ArrayList<Bank> banks = new ArrayList<>(resultList);
            return banks;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in selectAll in BankRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Bank select(int branchNumber, String city) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Bank resultList = entityManager.createQuery("from Bank where branchNumber=?1 and city=?2", Bank.class).setParameter(1, branchNumber).setParameter(2,city).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(shaba) in BankRepositoryImpl: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void update(Bank bank, String name) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE Bank set name=?1  where id=?2", Bank.class)
                    .setParameter(1, name).setParameter(2,bank.getId()).executeUpdate();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(shaba) in AccountRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void delete(int branchNumber, String city) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM Bank where branchNumber=?1 and city=?2", Account.class).setParameter(1, branchNumber).setParameter(2,city).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete in AccountRepositoryImpl: " + e.getMessage());
        }
    }
}
