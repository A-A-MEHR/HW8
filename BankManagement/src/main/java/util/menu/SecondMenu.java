package util.menu;

import entity.Account;
import entity.CreditCard;
import entity.TransAction;
import entity.User;
import util.application.ApplicationContext;
import util.enums.Status;
import util.enums.Success;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;

public class SecondMenu {
    public static void menu(User token) throws SQLException {

        Scanner scanner;
        scanner = new Scanner(System.in);
        while (true) {
            System.out.println("you can now do Card operations = 1 / transaction operations = 2 / or Financial operations = 3/ or  back = 4 /  or logout 5 ");
            String inputedValue = scanner.nextLine();
            switch (inputedValue) {
                case "1" -> {
                    System.out.println("insert card = 1 / delete card = 2 / selectCardNumber = 3 / selectBankCard = 4 / selectALLCARDS = 5 / delete all cards = 6 / selectShabaNumber = 7");
                    String inputedValue1 = scanner.nextLine();
                    switch (inputedValue1) {
                        case "1" -> {
                            Random random = new Random();
                            System.out.println("Enter your bankName:");
                            String bankName = scanner.nextLine();
                            Integer accountNumber = random.nextInt(1000000);
                            Integer shabaNumber = random.nextInt(1000000);
                            Account account = new Account(accountNumber, 0, token.getId(), bankName, shabaNumber);
                            account = ApplicationContext.getInstance().getAccountService().insert(account);
                            System.out.println("your accountNumber is :" + accountNumber);
                            System.out.println("your shabaNumber is :" + shabaNumber);
                            CreditCard creditCard = new CreditCard(random.nextInt(1000000), account.getId(), true, shabaNumber);
                            CreditCard creditCard1 = ApplicationContext.getInstance().getCreditCardService().insert(creditCard);
                            System.out.println(creditCard1);
                        }
                        case "2" -> {
                            System.out.println("Enter your CardNumber:");
                            Integer cardNumber = scanner.nextInt();
                            ApplicationContext.getInstance().getCreditCardService().delete(cardNumber);
                        }
                        case "3" -> {
                            System.out.println("Enter your CardNumber:");
                            Integer cardNumber = scanner.nextInt();
                            CreditCard creditCard = ApplicationContext.getInstance().getCreditCardService().selectCardNumber(cardNumber);
                            System.out.println(creditCard);
                        }
                        case "4" -> {
                            System.out.println("Enter your BankName:");
                            String bankName = scanner.nextLine();
                            CreditCard creditCard = ApplicationContext.getInstance().getCreditCardService().selectBankCard(bankName);
                            System.out.println(creditCard);
                        }
                        case "5" -> {
                            System.out.println("Enter your username:");
                            String userName = scanner.nextLine();
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            User user = new User(userName, password);
                            ArrayList<CreditCard> creditCards = ApplicationContext.getInstance().getCreditCardService().selectALLCARDS(user);
                            System.out.println(creditCards);
                        }
                        case "6" -> {
                            System.out.println("Enter your username:");
                            String userName = scanner.nextLine();
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            ApplicationContext.getInstance().getCreditCardService().deleteAllCards(userName, password);
                        }
                        case "7" -> {
                            System.out.println("Enter your shabaNumber:");
                            Integer shabaNumber = scanner.nextInt();
                            CreditCard creditCard = ApplicationContext.getInstance().getCreditCardService().selectShabaNumber(shabaNumber);
                            System.out.println(creditCard);
                        }

                    }
                }
                case "2" -> {
                    System.out.println("selectAll = 1 / selectAmount = 2/ selectStatus = 3 / select(period) = 4 / selectDay = 5");
                    String inputedValue2 = scanner.nextLine();
                    switch (inputedValue2) {
                        case "1" -> {
                            System.out.println("Enter your username:");
                            String userName = scanner.nextLine();
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            User user = ApplicationContext.getInstance().getUserService().select(userName, password);
                            ArrayList<TransAction> transActions = ApplicationContext.getInstance().getTransActionService().selectAll(user);
                            System.out.println(transActions);
                        }
                        case "2" -> {
                            System.out.println("Enter the amount :");
                            Integer amount = scanner.nextInt();
                            ArrayList<TransAction> transActions = ApplicationContext.getInstance().getTransActionService().selectAmount(amount);
                            System.out.println(transActions);
                        }
                        case "3" -> {
                            System.out.println("Enter the status:");
                            Status status = Status.valueOf(scanner.nextLine());
                            ArrayList<TransAction> transActions = ApplicationContext.getInstance().getTransActionService().selectStatus(status);
                            System.out.println(transActions);
                        }
                        case "4" -> {
                            System.out.println("Enter the first date:");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String dateInput = scanner.nextLine();
                            LocalDate localDate = LocalDate.parse(dateInput, formatter);
                            Date firstDate = java.sql.Date.valueOf(localDate);


                            System.out.println("Enter the second date:");
                            String dateInput2 = scanner.nextLine();
                            LocalDate localDate2 = LocalDate.parse(dateInput2, formatter);
                            Date secondDate = java.sql.Date.valueOf(localDate2);
                            ArrayList<TransAction> transActions = ApplicationContext.getInstance().getTransActionService().select(firstDate, secondDate);
                            System.out.println(transActions);
                        }
                        case "5" -> {
                            System.out.println("Enter the date:");
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                            String dateInput = scanner.nextLine();
                            LocalDate localDate = LocalDate.parse(dateInput, formatter);
                            Date date = java.sql.Date.valueOf(localDate);

                            System.out.println("Enter the time:");
                            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                            String timeInput = scanner.nextLine();
                            LocalTime localTime = LocalTime.parse(timeInput, timeFormatter);
                            Time time = java.sql.Time.valueOf(localTime);
                            ArrayList<TransAction> transActions = ApplicationContext.getInstance().getTransActionService().selectDay(date, time);
                            System.out.println(transActions);
                        }

                    }
                }
                case "3" -> {
                    System.out.println("Enter the type of transacting(NORMAL=1, PAYA_SINGLE=2, PAYA_SECTIOAL=3, SATNA=4):");
                    Integer inp = scanner.nextInt();
                    Status status;
                    if (inp == 1) {
                        status = Status.NORMAL;
                    } else if (inp == 2) {
                        status = Status.PAYA_SINGLE;
                    } else if (inp == 3) {
                        status = Status.PAYA_SECTIOAL;
                    } else {
                        status = Status.SATNA;
                    }
                    System.out.println("Enter the amount that you want to transact:");
                    Integer amount = scanner.nextInt();
                    if (status == Status.NORMAL) {
                        System.out.println("Enter the provenance cardNumber:");
                        Integer cardNumber1 = scanner.nextInt();
                        CreditCard creditCard1 = ApplicationContext.getInstance().getCreditCardService().selectCardNumber(cardNumber1);
                        System.out.println("Enter the destination cardNumber:");
                        Integer cardNumber2 = scanner.nextInt();
                        CreditCard creditCard2 = ApplicationContext.getInstance().getCreditCardService().selectCardNumber(cardNumber2);
                        Success success = ApplicationContext.getInstance().getExchangeService().exchange(creditCard1, amount, status, creditCard2);
                        System.out.println(success);
                    } else {
                        System.out.println("Enter the provenance shabaNumber:");
                        Integer shabaNumber = scanner.nextInt();
                        ArrayList<Integer> shabaNumbers = new ArrayList<>();
                        System.out.println("Enter the number of account that you want to update:");
                        Integer count = scanner.nextInt();
                        for (int i = 0; i < count; i++) {
                            System.out.println("Enter the destination shabaNumber:");
                            shabaNumbers.add(scanner.nextInt());
                        }
                        Success success = ApplicationContext.getInstance().getExchangeService().exchangeShabaNumber(shabaNumber, amount, status, shabaNumbers);
                        System.out.println(success);
                    }


                }
                case "4" -> {
                    FirstMenu.menu();
                }
                case "5" -> {
                    return;
                }
                default -> {
                    System.out.println("invalid input!");
                }

            }
        }
    }
}
