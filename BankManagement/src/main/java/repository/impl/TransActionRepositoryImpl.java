package repository.impl;

import config.ConnectionDB;
import entity.Bank;
import entity.CreditCard;
import entity.TransAction;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.TransActionRepository;
import util.application.HibernateUtil;
import util.enums.Status;
import util.enums.Success;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransActionRepositoryImpl implements TransActionRepository {
    public static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
    private final EntityManager entityManager = emf.createEntityManager();
    @Override
    public void insert(TransAction transAction){
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(transAction);
        entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in insert in transactionRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public ArrayList<TransAction> selectAll(User user){
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<TransAction> resultList = entityManager.createQuery("from TransAction where creditCard.account.user=?1", TransAction.class)
                    .setParameter(1, user).getResultList();
            ArrayList<TransAction> transactions = new ArrayList<>(resultList);
            return transactions;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in selectAll in transactionRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<TransAction> selectAmount(Integer amount){
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<TransAction> resultList = entityManager.createQuery("from TransAction where amount=?1", TransAction.class)
                    .setParameter(1, amount).getResultList();
            ArrayList<TransAction> transActions = new ArrayList<>(resultList);
            return transActions;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(amount) in transactionRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<TransAction> selectStatus(Status status){
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<TransAction> resultList = entityManager.createQuery("from TransAction where status=?1", TransAction.class)
                    .setParameter(1, status).getResultList();
            ArrayList<TransAction> transActions = new ArrayList<>(resultList);
            return transActions;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(status) in transactionRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<TransAction> select(Date date, Date date2){
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<TransAction> resultList = entityManager.createQuery("from TransAction where date>?1 and date<?2", TransAction.class)
                    .setParameter(1, date).setParameter(2,date2).getResultList();
            ArrayList<TransAction> transActions = new ArrayList<>(resultList);
            return transActions;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(date1,date2) in transactionRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<TransAction> selectDay(Date date, Time time){
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<TransAction> resultList = entityManager.createQuery("from TransAction where date=?1 and time=?2", TransAction.class)
                    .setParameter(1, date).setParameter(2,time).getResultList();
            ArrayList<TransAction> transActions = new ArrayList<>(resultList);
            return transActions;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(date,time) in transactionRepositoryImpl: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void update(TransAction transAction, Status status){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE TransAction set status=?1  where id=?2", TransAction.class)
                    .setParameter(1, status).setParameter(2,transAction.getId()).executeUpdate();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in update(status) in transactionRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void delete(Date date, Time time){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM TransAction where date=?1 and time=?2", TransAction.class)
                    .setParameter(1, date).setParameter(2,time).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete(date,time) in transactionRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void deleteCardId(Integer credit_card_id){
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM TransAction where creditCard.id=?1", TransAction.class)
                    .setParameter(1,credit_card_id).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete(credit_cardId) in transactionRepositoryImpl: " + e.getMessage());
        }
    }
}
