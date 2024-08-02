package repository.impl;

import config.ConnectionDB;
import entity.CreditCard;
import entity.User;
import repository.CreditCardRepository;
import util.application.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardRepositoryImpl implements CreditCardRepository {
    @Override
    public CreditCard insert(CreditCard creditCard) throws SQLException {
        Integer card_number = creditCard.getCardNumber();
        Integer account_id = creditCard.getAccount_id();
        Boolean is_active = creditCard.isActive();
        Connection connection = ConnectionDB.getConnection();
        String query = "INSERT INTO credit_cards (card_number,account_id,is_active,shaba_number )values(?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, card_number);
        preparedStatement.setInt(2, account_id);
        preparedStatement.setBoolean(3, is_active);
        preparedStatement.setInt(4, creditCard.getShabaNumber());
        preparedStatement.executeUpdate();
        return selectCardNumber(creditCard.getCardNumber());
    }

    @Override
    public CreditCard selectCardNumber(int cardNumber) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from credit_cards where  card_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, cardNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        CreditCard creditCard = null;
        while (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("id"), resultSet.getInt("card_number"), resultSet.getInt("account_id"), resultSet.getBoolean("is_active"), resultSet.getInt("shaba_number"));
        }
        return creditCard;
    }

    @Override
    public CreditCard selectShabaNumber(int shabaNumber) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from credit_cards where  shaba_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, shabaNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        CreditCard creditCard = null;
        while (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("id"), resultSet.getInt("card_number"), resultSet.getInt("account_id"), resultSet.getBoolean("is_active"), resultSet.getInt("shaba_number"));
        }
        return creditCard;
    }

    public CreditCard selectBankCard(String bankName) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from credit_cards join accounts on credit_cards.account_id=accounts.id where accounts.bank_name=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, bankName);
        ResultSet resultSet = preparedStatement.executeQuery();
        CreditCard creditCard = null;
        while (resultSet.next()) {
            creditCard = new CreditCard(resultSet.getInt("card_number"), resultSet.getInt("account_id"), resultSet.getBoolean("is_active"), resultSet.getInt("shaba_number"));
        }
        return creditCard;
    }

    public ArrayList<CreditCard> selectALLCARDS(User user) throws SQLException {
        ArrayList<CreditCard> objects = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select credit_cards.* from credit_cards join accounts on credit_cards.account_id=accounts.id join users on accounts.owner_id=users.id where user_name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            objects.add(new CreditCard(resultSet.getInt("card_number"), resultSet.getInt("account_id"), resultSet.getBoolean("is_active"), resultSet.getInt("shaba_number")));
        }
        return objects;


    }

    @Override
    public void update(CreditCard creditCard, Boolean is_active) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE credit_cards set is_active=?  where  card_number=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setBoolean(1, is_active);
        preparedStatement.setInt(2, creditCard.getCardNumber());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int cardNumber) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        CreditCard creditCard = ApplicationContext.getInstance().getCreditCardService().selectCardNumber(cardNumber);
        Integer id = creditCard.getId();
        ApplicationContext.getInstance().getTransActionService().deleteCardId(id);
        String query = "DELETE from credit_cards where  card_number=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, cardNumber);
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteAllCards(String userName, String password) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE FROM credit_cards USING accounts, users WHERE credit_cards.account_id = accounts.id AND accounts.owner_id = users.id AND user_name = ? AND password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }
}
