package repository.impl;

import config.ConnectionDB;
import entity.Account;
import repository.AccountRepository;
import util.enums.Success;

import java.sql.*;
import java.util.ArrayList;

public class AccountRepositoryImpl implements AccountRepository {
    @Override
    public Account insert(Account account) throws SQLException {
        Integer account_number = account.getAccountNumber();
        Integer credit = account.getCredit();
        Integer owner_id = account.getUserId();
        Connection connection = ConnectionDB.getConnection();
        String query = "INSERT INTO accounts (account_number,credit,owner_id,bank_name,shaba_number )values(?,?,?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, account_number);
        preparedStatement.setInt(2, credit);
        preparedStatement.setInt(3, owner_id);
        preparedStatement.setString(4, account.getBankName());
        preparedStatement.setInt(5, account.getShabaNumber());
        preparedStatement.executeUpdate();
        Account account1 = select(account.getShabaNumber());
        return account1;
    }

    @Override
    public ArrayList<Account> selectAll(int owner_id) throws SQLException {
        int owner2_id = owner_id;
        ArrayList<Account> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from accounts where  owner_id=owner2_id";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new Account(resultSet.getInt("account_number"), resultSet.getInt("credit"), resultSet.getInt("owner_id"), resultSet.getString("bank_name"), resultSet.getInt("shaba_number")));
        }
        return arrayList;
    }

    @Override
    public Account select(Integer shabaNumber) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from accounts where shaba_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, shabaNumber);
        ResultSet resultSet = preparedStatement.executeQuery();
        Account account = null;

        while (resultSet.next()) {
            account = new Account(resultSet.getInt("id"), resultSet.getInt("account_number"), resultSet.getInt("credit"), resultSet.getInt("owner_id"), resultSet.getInt("shaba_number"), resultSet.getString("bank_name"));
        }
        return account;
    }

    @Override
    public void update(Integer shaba_number, Integer credit) throws SQLException {

        Connection connection = ConnectionDB.getConnection();
        Account account = select(shaba_number);
        String query = "UPDATE accounts set credit=?  where shaba_number=? AND owner_id=?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, credit);
        preparedStatement.setInt(2, account.getShabaNumber());
        preparedStatement.setInt(3, account.getUserId());

        preparedStatement.executeUpdate();

    }

    public Success updateBach(Integer amount, ArrayList<Integer> shabaNumbers) throws SQLException {

        Connection connection = ConnectionDB.getConnection();

        String query = "UPDATE accounts set credit=?  where shaba_number=? AND owner_id=?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        connection.setAutoCommit(false);
        //  Savepoint savepoint1 = null;
        for (int i = 0; i < shabaNumbers.size(); i++) {
            Account account = select(shabaNumbers.get(i));
            Integer credit = account.getCredit();
            preparedStatement.setInt(1, credit + amount);
            preparedStatement.setInt(2, account.getShabaNumber());
            preparedStatement.setInt(3, account.getUserId());
            preparedStatement.addBatch();
        }
        int[] count = preparedStatement.executeBatch();
        // connection.commit();
        if (count.length == shabaNumbers.size()) {
            return Success.SUCCESSFUL;
        } else {
            return Success.UN_SUCCESSFUL;
        }
    }

    @Override
    public void delete(int shabaNumber) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE from accounts where shaba_number=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, shabaNumber);
        preparedStatement.executeUpdate();
    }
}
