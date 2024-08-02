package repository;

import entity.CreditCard;
import entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CreditCardRepository {
    CreditCard insert(CreditCard creditCard) throws SQLException;

    CreditCard selectCardNumber(int cardNumber) throws SQLException;

    CreditCard selectShabaNumber(int shabaNumber) throws SQLException;

    CreditCard selectBankCard(String bankName) throws SQLException;

    ArrayList<CreditCard> selectALLCARDS(User user) throws SQLException;

    void update(CreditCard creditCard, Boolean is_active) throws SQLException;

    void delete(int cardNumber) throws SQLException;

    void deleteAllCards(String userName, String password) throws SQLException;
}
