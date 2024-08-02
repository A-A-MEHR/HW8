package service.Impl;

import entity.User;
import repository.UserRepository;
import service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insert(User user) throws SQLException {
        return userRepository.insert(user);
    }

    @Override
    public User select(String userName, String password) throws SQLException {
        return userRepository.select(userName, password);
    }

    @Override
    public void updateUserName(User user, String user_name) throws SQLException {
        userRepository.updateUserName(user, user_name);
    }

    @Override
    public void updatePassword(User user, String password) throws SQLException {
        userRepository.updatePassword(user, password);
    }

    @Override
    public void delete(String userName, String password) throws SQLException {
        userRepository.delete(userName, password);
    }
}
