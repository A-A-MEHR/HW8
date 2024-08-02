package service;

import entity.TransAction;
import entity.User;
import util.enums.Status;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public interface TransActionService {
    void insert(TransAction transAction) throws SQLException;

    ArrayList<TransAction> selectAll(User user) throws SQLException;

    ArrayList<TransAction> selectAmount(Integer amount) throws SQLException;

    ArrayList<TransAction> selectStatus(Status status) throws SQLException;

    public ArrayList<TransAction> selectDay(Date date, Time time) throws SQLException;

    ArrayList<TransAction> select(Date date, Date date2) throws SQLException;

    void update(TransAction transAction, Status status) throws SQLException;

    void delete(Date date, Time time) throws SQLException;

    void deleteCardId(Integer credit_card_id) throws SQLException;
}
