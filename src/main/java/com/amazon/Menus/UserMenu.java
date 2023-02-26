package com.amazon.Menus;
//This class contains the method to Show the User menu to the Users with Register and Login functionality

import com.amazon.Model.User;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class UserMenu extends Menu{
    public static UserMenu menu=new UserMenu();
    public static UserMenu getInstance(){
        return  menu;}
    private UserMenu(){}
    public void showMenu () {
        Scanner scanner=new Scanner(System.in);
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("3: Quit");
        System.out.print("Please Enter Your Choice : ");
        int initialChoice=0;
        try{
            initialChoice=Integer.parseInt(scanner.nextLine());
        }catch (NumberFormatException e){
            showMenu();
        }
        boolean result = false;
        User user = new User();

        if(initialChoice == 1) {
            System.out.print("Enter Your Name : ");
            user.name =scanner.nextLine();

            System.out.print("Enter Your phone : ");
            user.phone =scanner.nextLine();

            System.out.print("Enter Your Email : ");
            user.email =scanner.nextLine();

            System.out.print("Enter Your password : ");
            user.password =scanner.nextLine();
            try {
                //encrypting the password with MD
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch(Exception e) {
                System.out.println("Something Went Wrong... Please Try Again. "+e);
            }
            System.out.print("Enter Your Address : ");
            user.address =scanner.nextLine();

            System.out.print("Enter Your Department : ");
            user.department =scanner.nextLine();

            user.type = 2;
            result = auth.registerUser(user);
            if(result) {
                System.out.println("Profile Created Successfully....");
            }else {
                System.out.println("Profile Creation Failed...  ");
            }
            showMenu();
        }else if(initialChoice ==2) {

            System.out.print("Enter Your Email : ");
            user.email =scanner.nextLine();

            System.out.print("Enter Your Password : ");
            user.password =scanner.nextLine();
            try {
                // Encoded to Hash i.e. SHA-256 so to match correctly
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                user.password = Base64.getEncoder().encodeToString(hash);
            }catch(Exception e) {
                System.out.println("Something Went Wrong..."+e);
            }
            result = auth.loginUser(user);

        }else if(initialChoice ==3) {
            System.out.println("Thank You for Using Amazon Bus Pass Application....!!");
        }else {
            System.out.println("Invalid choice...");
            System.out.println("Thank You for Using Amazon Bus Pass Application....!!");
        }
        if(result && user.type == 2) {

            BusPassSession.user = user;
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to User App");
            System.out.println("Hello, " + user.name);
            System.out.println("Its: " + new Date());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            boolean exitCode = false;
            while (true) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println("1 : View Routes");
                System.out.println("2 : My Bus Pass");
                System.out.println("3 : Apply for Pass");
                System.out.println("4 : Request Pass Update");
                System.out.println("5 : Write Feedbacks");
                System.out.println("6 : My Profile");
                System.out.println("0 : Log Out ");
                System.out.print("Please Enter Your Choice : ");

                int choice=0;
                try{
                    choice=Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    choice=7;//explicitly giving the wrong choice
                }
                switch(choice) {
                    case 1:
                        try {
                            routeService.viewRoute();
                        }catch (NumberFormatException e){
                            System.out.println("Please enter a valid choice.");
                        }
                        break;
                    case 2:
                        busPassService.viewPass(BusPassSession.user.id);
                        break;
                    case 3:

                        try {
                            routeService.viewRoute();
                            busPassService.requestPass();
                        }catch (NumberFormatException e){
                            System.out.println("Please enter a valid choice.");
                        }
                        break;
                    case 4:
                        try {
                            busPassService.passUpdate(BusPassSession.user.id);
                        }catch (NumberFormatException e){
                            System.out.println("Please enter a valid choice.");
                        }
                        break;
                    case 5:
                        try {
                            feedbackService.createFeedback();
                        }catch (NumberFormatException e){
                            System.out.println("Please enter a valid choice.");
                        }
                        break;
                    case 6:
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("My Profile");
                        user.prettyPrint();
                        System.out.print("Do You Wish to Update Your Profile (1 : Update 0: Cancel) : ");
                        choice = Integer.parseInt(scanner.nextLine());
                        if(choice==1) {
                            System.out.println("Press Enter to skip the column.");
                            System.out.print("Enter Your Name : ");
                            String name =scanner.nextLine();
                            if(name.isEmpty()==false) {
                                user.name =name;
                            }
                            System.out.print("Enter Your phone : ");
                            String phone =scanner.nextLine();
                            if(!phone.isEmpty()) {
                                user.phone=phone;
                            }
                            System.out.print("Enter Your password : ");
                            String password =scanner.nextLine();
                            if(!password.isEmpty()) {
                                user.password=password;
                                try {
                                    // Encoded to Hash i.e. SHA-256 so to match correctly
                                    MessageDigest digest = MessageDigest.getInstance("SHA-256");
                                    byte[] hash = digest.digest(user.password.getBytes(StandardCharsets.UTF_8));
                                    user.password = Base64.getEncoder().encodeToString(hash);
                                }catch(Exception e) {
                                    System.out.println("Something Went Wrong..."+e);
                                }
                            }
                            System.out.print("Enter Your Address : ");
                            String address =scanner.nextLine();
                            if(!address.isEmpty()) {
                                user.address=address;
                            }
                            System.out.print("Enter Your Department : ");
                            String department =scanner.nextLine();
                            if(!department.isEmpty()) {
                                user.department=department;
                            }
                            if(auth.updateUser(user)) {
                                System.out.println("Profile Updated Successfully....");
                            }else {
                                System.out.println("Profile Update Failed...");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Thank You For Using The User App...!");
                        exitCode = true;
                        break;
                    default:
                        System.out.println("Invalid Choice Please Enter a Valid Choice...");
                        break;
                }
                if(exitCode) {
                    break;
                }
            }
        }else {
            System.out.println("Invalid Credentials, Please Try Again...!!");
        }
    }
}
