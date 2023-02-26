package com.amazon.Menus;

import com.amazon.DB.DB;

// Start Point of the application
public class App {
    public static void main( String[] args ){

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println( "Welcome to Bus Pass Management Application" );
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Menu menu = new Menu();
        if(args.length > 0) {
            System.out.println(args[0]);
             DB.FILEPATH = args[0];
        }
        DB.getInstance().loadDriver();

        menu.showMainMenu();
        DB.getInstance().closeConnection();
    }
}


