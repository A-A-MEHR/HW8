package repository;

import entity.Account;
import entity.CreditCard;
import util.enums.Success;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AccountRepository {
    Account insert(Account account);

    ArrayList<Account> selectAll(int owner_id) throws SQLException;

    Account select(Integer shabaNumber) throws SQLException;

    void update(Integer shaba_number, Integer credit) throws SQLException;

    Success updateBach(Integer amount, ArrayList<Integer> shabaNumbers) throws SQLException;

    void delete(int shabaNumber) throws SQLException;

}
