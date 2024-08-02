package repository.impl;

import config.ConnectionDB;
import entity.Account;
import entity.Bank;
import entity.CreditCard;
import entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import repository.CreditCardRepository;
import util.application.ApplicationContext;
import util.application.HibernateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CreditCardRepositoryImpl implements CreditCardRepository {
    public static final EntityManagerFactory emf = HibernateUtil.getEntityManagerFactory();
    private final EntityManager entityManager = emf.createEntityManager();
    @Override
    public CreditCard insert(CreditCard creditCard) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(creditCard);
            entityManager.getTransaction().commit();
            return (creditCard);
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in insert in creditCardRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CreditCard selectCardNumber(int cardNumber) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CreditCard resultList = entityManager.createQuery("from CreditCard where cardNumber=?1", CreditCard.class).setParameter(1, cardNumber).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(cardNumber) in creditRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public CreditCard selectShabaNumber(int shabaNumber)  {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CreditCard resultList = entityManager.createQuery("from CreditCard where shabaNumber=?1", CreditCard.class)
                    .setParameter(1, shabaNumber).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(shabaNumber) in creditRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    public CreditCard selectBankCard(String bankName)  {
        EntityManager entityManager = emf.createEntityManager();
        try {
            CreditCard resultList = entityManager.createQuery("from CreditCard where account.bank.name=?1", CreditCard.class)
                    .setParameter(1, bankName).getSingleResult();
            return resultList;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in select(bankCard) in creditRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    public ArrayList<CreditCard> selectALLCARDS(User user) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            List<CreditCard> resultList = entityManager.createQuery("from CreditCard where account.user.id=?1", CreditCard.class)
                    .setParameter(1, user.getId()).getResultList();
            ArrayList<CreditCard> creditCards = new ArrayList<>(resultList);
            return creditCards;
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in selectAll in creditRepositoryImpl: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(CreditCard creditCard, Boolean is_active) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("UPDATE CreditCard set isActive=?1  where id=?2", Bank.class)
                    .setParameter(1, is_active).setParameter(2,creditCard.getId()).executeUpdate();

        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in update(isActive) in creditcardRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void delete(int cardNumber) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM CreditCard where cardNumber=?1", CreditCard.class)
                    .setParameter(1, cardNumber).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete in creditcardRepositoryImpl: " + e.getMessage());
        }
    }

    @Override
    public void deleteAllCards(String userName, String password) {
        EntityManager entityManager = emf.createEntityManager();
        try {
            entityManager.createQuery("delete FROM CreditCard where account.user.userName=?1 and account.user.password=?2", CreditCard.class)
                    .setParameter(1, userName).setParameter(2,password).executeUpdate();
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            System.err.println("something went wrong in delete in deleteRepositoryImpl: " + e.getMessage());
        }
    }
}
