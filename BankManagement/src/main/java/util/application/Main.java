package util.application;

import entity.User;
import util.menu.FirstMenu;
import util.menu.SecondMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        User token = FirstMenu.menu();
        SecondMenu.menu(token);

    }
}
