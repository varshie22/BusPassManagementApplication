package com.amazon.Menus;

//This class contains the method of show Main menu to the user and admin Login

import java.util.Scanner;
import com.amazon.Controller.*;
import com.amazon.DB.DB;

public class Menu {
    AuthenticationService auth = AuthenticationService.getInstance();
    RouteService routeService = RouteService.getInstance();
    BusPassService busPassService = BusPassService.getInstance();
    FeedbackService feedbackService = FeedbackService.getInstance();
    Scanner scanner = new Scanner(System.in);
    void showMainMenu() {

        // Initial Menu for the Application
        while(true) {
            System.out.println("1: Admin");
            System.out.println("2: User");
            System.out.println("0: Quit");
            System.out.print("Select an Option : ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    System.out.println("Thank You for Using Bus Pass Application GoodBye...!");
                    DB db = DB.getInstance();
                    db.closeConnection();
                    scanner.close();
                    break;
                }

                Menu menu = MenuFactory.getMenu(choice);
                menu.showMenu();
            }catch (Exception e) {
                System.out.println("Invalid Choice Please Enter a Valid Choice...");
            }
        }
    }
    public void showMenu() {
        System.out.println("Showing the Menu...");
    }

}
