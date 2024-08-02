package service.Impl;

import entity.Bank;
import repository.BankRepository;
import service.BankService;

import java.sql.SQLException;
import java.util.ArrayList;

public class BankServiceImpl implements BankService {
    private final BankRepository bankRepository;

    public BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    @Override
    public void insert(Bank bank) throws SQLException {
        bankRepository.insert(bank);
    }

    @Override
    public ArrayList<Bank> selectAll(String name) throws SQLException {
        return bankRepository.selectAll(name);
    }

    @Override
    public Bank select(int branchNumber, String city) throws SQLException {
        return bankRepository.select(branchNumber, city);
    }

    @Override
    public void update(Bank bank, String name) throws SQLException {
        bankRepository.update(bank, name);
    }

    @Override
    public void delete(int branchNumber, String city) throws SQLException {
        bankRepository.delete(branchNumber, city);
    }
}
