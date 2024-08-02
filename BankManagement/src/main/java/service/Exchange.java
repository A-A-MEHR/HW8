package service;

import entity.CreditCard;
import util.enums.Status;
import util.enums.Success;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Exchange {
    public Success exchangeShabaNumber(Integer shabaNumber, Integer amount, Status status, ArrayList<Integer> shabaNumbers) throws SQLException;

    Success exchange(CreditCard creditCard1, Integer amount, Status status, CreditCard creditCard2) throws SQLException;

    Integer commissionShabaNumber(Integer shabaNumber, Integer amount, Status status, ArrayList<Integer> shabaNumbers) throws SQLException;

    Integer commission(CreditCard creditCard1, Integer amount, Status status, CreditCard creditCard2) throws SQLException;


}
