package service.Impl;

import entity.Account;
import entity.CreditCard;
import entity.TransAction;
import service.Exchange;
import util.application.ApplicationContext;
import util.enums.Status;
import util.enums.Success;

import java.sql.SQLException;
import java.util.ArrayList;

public class ExchangeImpl implements Exchange {
    @Override
    public Success exchange(CreditCard creditCard1, Integer amount, Status status, CreditCard creditCard2) throws SQLException {
        Integer commission;
        Boolean possibleTransaction = true;
        Boolean bool = (status == Status.NORMAL && amount < 15000000);
        if (creditCard1.isActive() == false || creditCard2.isActive() == false) {
            possibleTransaction = false;
        }
        if (!bool) {
            possibleTransaction = false;
        }
        commission = commission(creditCard1, amount, status, creditCard2);
        Integer shabaNumber = creditCard1.getShabaNumber();
        Integer credit = ApplicationContext.getInstance().getAccountService().select(shabaNumber).getCredit();
        if (credit < (amount + commission)) {
            possibleTransaction = false;
        }
        if (possibleTransaction) {
            Integer shabaNumber1 = creditCard1.getShabaNumber();
            Account account = ApplicationContext.getInstance().getAccountService().select(shabaNumber1);
            Integer credit1 = account.getCredit();
            ApplicationContext.getInstance().getAccountService().update(account.getShabaNumber(), credit1 - amount - commission);
            Integer shabaNumber2 = creditCard2.getShabaNumber();
            Account account2 = ApplicationContext.getInstance().getAccountService().select(shabaNumber2);
            Integer credit2 = account2.getCredit();
            ApplicationContext.getInstance().getAccountService().update(account2.getShabaNumber(), credit2 + amount);
            TransAction transAction = new TransAction(Success.SUCCESSFUL, amount, creditCard1, status);
            ApplicationContext.getInstance().getTransActionService().insert(transAction);
        } else {
            TransAction transAction = new TransAction(Success.UN_SUCCESSFUL, amount, creditCard1, status);
            ApplicationContext.getInstance().getTransActionService().insert(transAction);
        }
        if (possibleTransaction) {
            return Success.SUCCESSFUL;
        } else {
            return Success.UN_SUCCESSFUL;
        }
    }


    public Integer commission(CreditCard creditCard1, Integer amount, Status status, CreditCard creditCard2) throws SQLException {

        if (status == Status.NORMAL) {
//            if(amount/100>240){
//                return Math.min(amount/100,3000);
//            }
//           else {
//               return 240;
//            }
            return amount / 1000;
        } else {
            System.out.println("Invalid Status!");
            return null;
        }
    }

    @Override
    public Integer commissionShabaNumber(Integer shabaNumber, Integer amount, Status status, ArrayList<Integer> shabaNumbers) throws SQLException {
        Integer count = shabaNumbers.size();
        if (status == Status.PAYA_SINGLE) {
//            if(amount/100>240){
//                return Math.min(amount/100,3000);
//            }
//           else {
//               return 240;
//            }
            return amount / 1000;

        } else if (status == Status.PAYA_SECTIOAL) {
            if (count <= 10) {
                return 1200;
            } else {
                return count * 120;
            }
        } else if (status == Status.SATNA) {
//            return Math.min(25000,amount/50);
            return amount / 500;
        }

        return 0;

    }


    @Override
    public Success exchangeShabaNumber(Integer shabaNumber, Integer amount, Status status, ArrayList<Integer> shabaNumbers) throws SQLException {
        Integer count = shabaNumbers.size();
        Integer commission;
        Boolean possibleTransaction = true;
        Boolean bool2 = (status == Status.PAYA_SECTIOAL && (amount > 15000000 && amount < 50000000));
        Boolean bool3 = (status == Status.PAYA_SINGLE && (amount > 15000000 && amount < 50000000));
        Boolean bool4 = (status == Status.SATNA && (amount > 50000000 && amount < 200000000));
        Boolean bool = (bool2 || bool3 || bool4);

        if (!bool) {
            possibleTransaction = false;
        }
        commission = commissionShabaNumber(shabaNumber, amount, status, shabaNumbers);
        Account account = ApplicationContext.getInstance().getAccountService().select(shabaNumber);
        if (account.getCredit() < (amount + commission)) {
            possibleTransaction = false;
        }
        CreditCard creditCard = ApplicationContext.getInstance().getCreditCardService().selectShabaNumber(shabaNumber);
        if (creditCard.isActive() == false) {
            possibleTransaction = false;
        }
        if (possibleTransaction) {
            ApplicationContext.getInstance().getAccountService().update(account.getShabaNumber(), account.getCredit() - amount * count - commission);
            Success success = ApplicationContext.getInstance().getAccountService().updateBach(amount, shabaNumbers);
            TransAction transAction = new TransAction(success, amount, creditCard, status);
            ApplicationContext.getInstance().getTransActionService().insert(transAction);
            if (possibleTransaction && success == Success.SUCCESSFUL) {
                return Success.SUCCESSFUL;
            }
            return Success.UN_SUCCESSFUL;
        } else {
            TransAction transAction = new TransAction(Success.UN_SUCCESSFUL, amount, creditCard, status);
            ApplicationContext.getInstance().getTransActionService().insert(transAction);
            return Success.UN_SUCCESSFUL;
        }


    }
}
