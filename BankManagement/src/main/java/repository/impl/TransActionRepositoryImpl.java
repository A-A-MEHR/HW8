package repository.impl;

import config.ConnectionDB;
import entity.TransAction;
import entity.User;
import repository.TransActionRepository;
import util.enums.Status;
import util.enums.Success;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class TransActionRepositoryImpl implements TransActionRepository {
    @Override
    public void insert(TransAction transAction) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "INSERT INTO transactions (status, date, time, amount, credit_card_id, success) VALUES (?::status, ?, ?, ?, ?, ?::success)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, transAction.getStatus().name());
        preparedStatement.setDate(2, java.sql.Date.valueOf(transAction.getDate()));
        preparedStatement.setTime(3, Time.valueOf(transAction.getTime()));
        preparedStatement.setInt(4, transAction.getAmount());
        preparedStatement.setInt(5, transAction.getCredit_card_id());
        preparedStatement.setString(6, transAction.getSuccess().name());
        preparedStatement.executeUpdate();
    }

    @Override
    public ArrayList<TransAction> selectAll(User user) throws SQLException {
        ArrayList<TransAction> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from transactions join credit_cards on transactions.credit_card_id=credit_cards.id  join accounts on credit_cards.account_id=accounts.id join users on accounts.owner_id=users.id where user_name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new TransAction(Status.valueOf(resultSet.getString("status")), resultSet.getInt("amount"), resultSet.getInt("credit_card_id"), Success.valueOf(resultSet.getString("success"))));
        }
        return arrayList;
    }

    @Override
    public ArrayList<TransAction> selectAmount(Integer amount) throws SQLException {
        ArrayList<TransAction> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from transactions where amount>?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, amount);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new TransAction(Status.valueOf(resultSet.getString("status")), resultSet.getInt("amount"), resultSet.getInt("credit_card_id"), Success.valueOf(resultSet.getString("success"))));
        }
        return arrayList;
    }

    @Override
    public ArrayList<TransAction> selectStatus(Status status) throws SQLException {
        ArrayList<TransAction> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from transactions where status::text=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, status.name());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new TransAction(Status.valueOf(resultSet.getString("status")), resultSet.getInt("amount"), resultSet.getInt("credit_card_id"), Success.valueOf(resultSet.getString("success"))));

        }
        return arrayList;
    }

    @Override
    public ArrayList<TransAction> select(Date date, Date date2) throws SQLException {
        ArrayList<TransAction> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "SELECT * FROM transactions WHERE (date >= ?::date AND date <= ?::date)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, java.sql.Date.valueOf(String.valueOf(date)));
        preparedStatement.setDate(2, java.sql.Date.valueOf(String.valueOf(date2)));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new TransAction(Status.valueOf(resultSet.getString("status")), resultSet.getInt("amount"), resultSet.getInt("credit_card_id"), Success.valueOf(resultSet.getString("success"))));

        }
        return arrayList;
    }

    public ArrayList<TransAction> selectDay(Date date, Time time) throws SQLException {
        ArrayList<TransAction> arrayList = new ArrayList<>();
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from transactions where  (date=?) AND (time =?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, java.sql.Date.valueOf(String.valueOf(date)));
        preparedStatement.setTime(2, Time.valueOf(time.toLocalTime()));
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            arrayList.add(new TransAction(Status.valueOf(resultSet.getString("status")), resultSet.getInt("amount"), resultSet.getInt("credit_card_id"), Success.valueOf(resultSet.getString("success"))));
        }
        return arrayList;
    }


    @Override
    public void update(TransAction transAction, Status status) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE transactions set status=?  where  date=? ANd time=? AND credit_card_id=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, java.sql.Date.valueOf(transAction.getDate()));
        preparedStatement.setTime(2, Time.valueOf(transAction.getTime()));
        preparedStatement.setInt(3, transAction.getCredit_card_id());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(Date date, Time time) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE from transactions where  date=? ANd time=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setDate(1, (java.sql.Date) date);
        preparedStatement.setTime(2, time);
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteCardId(Integer credit_card_id) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE from transactions where credit_card_id=? ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, credit_card_id);
        preparedStatement.executeUpdate();
    }
}
