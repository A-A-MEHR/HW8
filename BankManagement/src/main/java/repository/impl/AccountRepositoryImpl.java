package repository.impl;

import config.ConnectionDB;
import entity.Account;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.AccountRepository;
import util.application.HibernateUtil;
import util.enums.Success;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    public static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
    private final EntityManager entityManager = emf.createEntityManager();
    @Override
    public Account insert(Account account){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(account);
            entityManager.getTransaction().commit();
            return (account);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in insert in AccountRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Account> selectAll(int owner_id) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<Account> resultList = entityManager.createQuery("from Account where user.id=?1", Account.class).setParameter(1, owner_id).getResultList();
            ArrayList<Account> accounts = new ArrayList<>(resultList);
            return accounts;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in selectAll in AccountRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Account select(Integer shabaNumber) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            Account resultList = entityManager.createQuery("from Account where shabaNumber=?1", Account.class).setParameter(1, shabaNumber).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(shaba) in AccountRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(Integer shaba_number, Integer credit) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE Account set credit=?1  where shabaNumber=?2", Account.class)
                    .setParameter(1,credit).setParameter(2,shaba_number).getSingleResult();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(shaba) in AccountRepositoryImpl: " + e.getMessage());
        }

    }

    public Success updateBach(Integer amount, ArrayList<Integer> shabaNumbers) throws SQLException {

        Connection connection = ConnectionDB.getConnection();

        String query = "UPDATE accounts set credit=?  where shaba_number=? AND =?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        connection.setAutoCommit(false);
        //  Savepoint savepoint1 = null;
        for (int i = 0; i < shabaNumbers.size(); i++) {
            Account account = select(shabaNumbers.get(i));
            Integer credit = account.getCredit();
            preparedStatement.setInt(1, credit + amount);
            preparedStatement.setInt(2, account.getShabaNumber());
            preparedStatement.setInt(3, account.getUser().getId());
            preparedStatement.addBatch();
        }
        int[] count = preparedStatement.executeBatch();
        // connection.commit();
        if (count.length == shabaNumbers.size()) {
            return Success.SUCCESSFUL;
        } else {
            return Success.UN_SUCCESSFUL;
        }

    }

    @Override
    public void delete(int shabaNumber) throws SQLException {
        EntityManager entityManager = emf.createEntityManager();
        try {
          entityManager.createQuery("delete FROM Account where shabaNumber=?1", Account.class).setParameter(1, shabaNumber).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete in AccountRepositoryImpl: " + e.getMessage());
        }
    }
}
