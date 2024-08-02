package service.Impl;

import entity.TransAction;
import entity.User;
import repository.TransActionRepository;
import service.TransActionService;
import util.enums.Status;

import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

public class TransActionServiceImpl implements TransActionService {

    private final TransActionRepository transActionRepository;

    public TransActionServiceImpl(TransActionRepository transActionRepository) {
        this.transActionRepository = transActionRepository;
    }

    @Override
    public void insert(TransAction transAction) throws SQLException {
        transActionRepository.insert(transAction);
    }

    @Override
    public ArrayList<TransAction> selectAll(User user) throws SQLException {
        return transActionRepository.selectAll(user);
    }

    @Override
    public ArrayList<TransAction> selectAmount(Integer amount) throws SQLException {
        return transActionRepository.selectAmount(amount);
    }

    @Override
    public ArrayList<TransAction> selectStatus(Status status) throws SQLException {
        return transActionRepository.selectStatus(status);
    }

    @Override
    public ArrayList<TransAction> select(Date date, Date date2) throws SQLException {
        return transActionRepository.select(date, date2);
    }

    @Override
    public ArrayList<TransAction> selectDay(Date date, Time time) throws SQLException {
        return transActionRepository.selectDay(date, time);
    }

    @Override
    public void update(TransAction transAction, Status status) throws SQLException {
        transActionRepository.update(transAction, status);
    }

    @Override
    public void delete(Date date, Time time) throws SQLException {
        transActionRepository.delete(date, time);
    }

    @Override
    public void deleteCardId(Integer credit_card_id) throws SQLException {
        transActionRepository.deleteCardId(credit_card_id);
    }
}
