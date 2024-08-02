package repository.impl;

import config.ConnectionDB;
import entity.User;
import repository.UserRepository;

import java.sql.*;

public class UserRepositoryImpl implements UserRepository {

    @Override
    public User insert(User user) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "INSERT INTO users (user_name,password)values(?,?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user.getUserName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();
        User user1 = select(user.getUserName(), user.getPassword());
        return user1;
    }

    @Override
    public User select(String userName, String password) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "select * from users where user_name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;
        if (resultSet.next()) {
            user = new User(resultSet.getInt("id"), resultSet.getString("user_name"), resultSet.getString("password"));
        }
        return user;
    }

    @Override
    public void updateUserName(User user, String user_name) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE users set user_name=?  where user_name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, user_name);
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updatePassword(User user, String password) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "UPDATE users set password=?  where user_name=? AND password=?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, password);
        preparedStatement.setString(2, user.getUserName());
        preparedStatement.setString(3, user.getPassword());
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(String userName, String password) throws SQLException {
        Connection connection = ConnectionDB.getConnection();
        String query = "DELETE from users where  user_name=? AND password=?  ";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, userName);
        preparedStatement.setString(2, password);
        preparedStatement.executeUpdate();
    }
}
