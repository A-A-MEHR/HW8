package service;

import entity.User;

import java.sql.SQLException;

public interface UserService {
    User insert(User user) throws SQLException;

    User select(String userName, String password) throws SQLException;

    void updateUserName(User user, String user_name) throws SQLException;

    void updatePassword(User user, String password) throws SQLException;

    void delete(String userName, String password) throws SQLException;
}
