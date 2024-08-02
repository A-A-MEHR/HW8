package service.Impl;

import dto.AccountDTO;
import entity.Account;
import entity.Bank;
import entity.User;
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
    public Account insert(User token, AccountDTO dto) {
        Bank bank = Bank.builder().name(dto.getBankName()).build();
        Account account = new Account(dto.getAccountNumber(), dto.getCredit(), token,bank, dto.getShabaNumber());
      accountRepository.insert(account);
     return new Account();
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
