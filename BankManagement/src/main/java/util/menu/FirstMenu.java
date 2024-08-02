package util.menu;

import entity.User;
import util.application.ApplicationContext;

import java.sql.SQLException;
import java.util.Scanner;

public class FirstMenu {
    public static User token = null;

    public static User menu() throws SQLException {
        System.out.println("WELCOME TO THE MENU");
        Scanner scanner = new Scanner(System.in);
        while (token == null) {
            System.out.println("select between REGISTER = 1 / LOGIN = 2");
            String inputedValue = scanner.nextLine();
            switch (inputedValue) {
                case "1" -> {
                    System.out.println("enter your username");
                    String username = scanner.nextLine();
                    System.out.println("enter your password");
                    String password = scanner.nextLine();
                    User user = new User(username, password);
                    if (ApplicationContext.getInstance().getUserService().select(username, password) != null) {
                        token = ApplicationContext.getInstance().getUserService().select(username, password);
                        System.out.println("Thid user has been registered!");
                        System.out.println();
                    } else {

                        token = ApplicationContext.getInstance().getUserService().insert(user);
                    }
                    if (token == null) {
                        System.out.println("Invalid username or password");
                    }
                }
                case "2" -> {
                    System.out.println("enter your username");
                    String username = scanner.nextLine();
                    System.out.println("enter your password");
                    String password = scanner.nextLine();
                    User user = new User(username, password);

                    token = ApplicationContext.getInstance().getUserService().select(username, password);


                }
                default -> System.out.println("roung input value");
            }
        }
        return token;
    }
}