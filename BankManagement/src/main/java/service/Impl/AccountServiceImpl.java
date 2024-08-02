package service.Impl;

import entity.Account;
import entity.CreditCard;
import repository.AccountRepository;
import service.AccountService;
import util.enums.Success;

import java.sql.SQLException;
import java.util.ArrayList;

public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account insert(Account account) throws SQLException {
        return accountRepository.insert(account);
    }

    @Override
    public ArrayList<Account> selectAll(int owner_id) throws SQLException {
        return accountRepository.selectAll(owner_id);
    }

    @Override
    public Account select(Integer shabaNumber) throws SQLException {
        return accountRepository.select(shabaNumber);
    }

    @Override
    public void update(Integer shaba_number, Integer credit) throws SQLException {
        accountRepository.update(shaba_number, credit);
    }

    public Success updateBach(Integer amount, ArrayList<Integer> shabaNumbers) throws SQLException {
        return accountRepository.updateBach(amount, shabaNumbers);
    }

    @Override
    public void delete(int shabaNumber) throws SQLException {
        accountRepository.delete(shabaNumber);
    }
}
