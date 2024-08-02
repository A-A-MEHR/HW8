package repository.impl;

import config.ConnectionDB;
import entity.Bank;
import repository.BankRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BankRepositoryImpl implements BankRepository {
    @Override
    public void insert(Bank bank) throws SQLException {
        String city = bank.getCity();
        Integer branch_number = bank.getBranchNumber();
        String name = bank.getName();
        Connection connection = ConnectionDB.getConnection();
        String query = "INSERT INTO banks (city,branch_number,name )values(?,?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, city);
        preparedStatement.setInt(2, branch_number);
        preparedStatement.setString(3, name);
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<Bank> selectAll(String name) throws SQLException {
        ArrayList<Bank> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from banks where  name=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, name);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new Bank(resultSet.getString("city"), resultSet.getInt("branch_number"), resultSet.getString("name")));
        }
        return arrayList;
    }

    @Override
    public Bank select(int branchNumber, String city) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from banks where  branch_number=? AND city=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, branchNumber);
        preparedStatement.setString(2, city);
        ResultSet resultSet = preparedStatement.executeQuery();
        return (Bank) resultSet;
    }


    @Override
    public void update(Bank bank, String name) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE banks set name=?  where  branch_number=? AND city=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, bank.getBranchNumber());
        preparedStatement.setString(2, bank.getCity());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int branchNumber, String city) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE from accounts where branch_number=? AND city=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, branchNumber);
        preparedStatement.setString(2, city);
        preparedStatement.executeUpdate();
    }
}
