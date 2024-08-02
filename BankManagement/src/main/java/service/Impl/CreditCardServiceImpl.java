package service.Impl;

import entity.CreditCard;
import entity.User;
import repository.CreditCardRepository;
import service.CreditCardService;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardServiceImpl implements CreditCardService {
    private final CreditCardRepository creditCardRepository;

    public CreditCardServiceImpl(CreditCardRepository creditCardRepository) {
        this.creditCardRepository = creditCardRepository;
    }

    @Override
    public CreditCard insert(CreditCard creditCard) throws SQLException {
        return creditCardRepository.insert(creditCard);
    }

    @Override
    public CreditCard selectCardNumber(int cardNumber) throws SQLException {
        return creditCardRepository.selectCardNumber(cardNumber);
    }

    @Override
    public CreditCard selectShabaNumber(int shabaNumber) throws SQLException {
        return creditCardRepository.selectShabaNumber(shabaNumber);
    }

    @Override
    public CreditCard selectBankCard(String bankName) throws SQLException {
        return creditCardRepository.selectBankCard(bankName);
    }

    public ArrayList<CreditCard> selectALLCARDS(User user) throws SQLException {
        return creditCardRepository.selectALLCARDS(user);
    }


    @Override
    public void update(CreditCard creditCard, Boolean is_active) throws SQLException {
        creditCardRepository.update(creditCard, is_active);
    }

    @Override
    public void delete(int cardNumber) throws SQLException {
        creditCardRepository.delete(cardNumber);
    }

    @Override
    public void deleteAllCards(String userName, String password) throws SQLException {
        creditCardRepository.deleteAllCards(userName, password);
    }
}
