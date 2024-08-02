package service;

import entity.Bank;

import java.sql.SQLException;
import java.util.ArrayList;

public interface BankService {
    void insert(Bank bank) throws SQLException;

    ArrayList<Bank> selectAll(String name) throws SQLException;

    Bank select(int branchNumber, String city) throws SQLException;

    void update(Bank bank, String name) throws SQLException;

    void delete(int branchNumber, String city) throws SQLException;
}
