package com.amazon.Menus;
//This class contains the method to show the user menu to the users

import com.amazon.Model.User;

import java.sql.SQLException;
import java.util.Date;
import java.util.Scanner;

public class AdminMenu extends Menu{
    public static AdminMenu menu=new AdminMenu();
    public static AdminMenu getInstance()
    {return  menu;}
    private AdminMenu(){}
    public void showMenu() {
        Scanner scanner=new Scanner(System.in);
        System.out.println("Navigating to the Admin Menu...\n");
        //Login code of Admin
        User adminUser=new User();
        System.out.print("Enter your Email : ");
        adminUser.email = scanner.nextLine();

        System.out.print("Enter your password : ");
        adminUser.password = scanner.nextLine();

        boolean result=auth.loginUser(adminUser);
        if(result==true && adminUser.type==1) {
            BusPassSession.user=adminUser;// setting the user session as admin
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Welcome to Admin App");
            System.out.println("Hello " + adminUser.name);
            System.out.println("Its : + " + new Date());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            boolean exitCode=false;
            boolean run;
            while(exitCode==false) {
                System.out.println("1: Manage Routes");
                System.out.println("2: Manage Stops");
                System.out.println("3: Manage Vehicles");
                System.out.println("4: Manage BusPass");
                System.out.println("5: Manage Feedbacks");
                System.out.println("0: Log Out");
                System.out.print("Please Enter your choice : ");
                int choice=0;
                try{
                    choice=Integer.parseInt(scanner.nextLine());
                }catch (NumberFormatException e){
                    choice=6;
                }
                switch(choice) {
                    case 1:
                        run=true;
                        while (run) {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                            System.out.println("1: Add Route");
                            System.out.println("2: Update Route");
                            System.out.println("3: Delete Route");
                            System.out.println("4: View Route");
                            System.out.println("0: To return back");

                            System.out.print("Please Enter Your Choice : ");
                            int routeChoice=0;
                            try{
                                routeChoice=Integer.parseInt(scanner.nextLine());
                            }catch (NumberFormatException e){
                                routeChoice=5;//explicitly giving the wrong choice
                            }

                            if (routeChoice == 1) {
                                routeService.addRoute();

                            } else if (routeChoice == 2) {
                                routeService.viewRouteShort();
                                try {
                                    routeService.updateRoute();

                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (routeChoice == 3) {
                                routeService.viewRouteShort();
                                try {
                                    routeService.deleteRoute();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (routeChoice == 4) {
                                try {
                                    routeService.viewRoute();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            }else if (routeChoice == 0) {
                                run = false;
                            }
                            else {
                                System.out.println("Invalid Route choice...");
                            }
                        }
                        break;
                    case 2:
                        run=true;
                        while (run) {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                            System.out.println("1: Add Stop");
                            System.out.println("2: Update Stop");
                            System.out.println("3: Delete Stop");
                            System.out.println("4: View Stop");
                            System.out.println("0: To return back");

                            System.out.print("Please Enter Your Choice : ");
                            int stopChoice=0;
                            try{
                                stopChoice=Integer.parseInt(scanner.nextLine());
                            }catch (NumberFormatException e){
                                stopChoice=5;//explicitly giving the wrong choice
                            }

                            if (stopChoice == 1) {
                                try {
                                    routeService.viewRouteShort();
                                    routeService.addStop();// needed to add sql query exception
                                }
                                catch (NumberFormatException e){
                                    System.out.println("Please enter valid input.");
                                }
                                catch (SQLException e){
                                    System.out.println("Can't add this record in the database.");
                                }
                            } else if (stopChoice == 2) {
                                try {
                                    routeService.viewRouteShort();
                                    routeService.viewStop();
                                    routeService.updateStop();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid Input.");
                                }

                            } else if (stopChoice == 3) {
                                try {
                                    routeService.viewRouteShort();
                                    routeService.viewStop();
                                    routeService.deleteStop();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid Input.");
                                }

                            } else if (stopChoice == 4) {
                                try {
                                    routeService.viewRouteShort();
                                    routeService.viewStop();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid Input.");
                                }

                            }else if (stopChoice == 0) {
                                run = false;
                            }
                            else {
                                System.out.println("Invalid Stop choice....");
                            }
                        }
                        break;
                    case 3:
                        run=true;
                        while (run) {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("1: Add Vehicle");
                            System.out.println("2: Update Vehicle");
                            System.out.println("3: Delete Vehicle");
                            System.out.println("4: View Vehicles");
                            System.out.println("0: To return back");

                            System.out.print("Please Enter Your Choice : ");
                            int vehicleChoice=0;
                            try{
                                vehicleChoice=Integer.parseInt(scanner.nextLine());
                            }catch (NumberFormatException e){
                                vehicleChoice=5;//explicitly giving the wrong choice
                            }
                            if (vehicleChoice == 1) {
                                routeService.viewRouteShort();
                                try {
                                    routeService.addVehicle();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (vehicleChoice == 2) {
                                routeService.viewRouteShort();

                                try {
                                    routeService.viewVehicle();
                                    routeService.updateVehicle();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (vehicleChoice == 3) {
                                routeService.viewRouteShort();
                                try {
                                    routeService.viewVehicle();
                                    routeService.deleteVehicle();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (vehicleChoice == 4) {
                                routeService.viewRouteShort();
                                try {
                                    routeService.viewVehicle();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input");
                                }

                            } else if (vehicleChoice == 0) {
                                run = false;
                            } else {
                                System.out.println("Invalid Vehicle choice...");
                            }
                        }
                        break;
                    case 4:
                        run=true;
                        while (run) {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("1: View Passes");
                            System.out.println("2: View Pass Requests");
                            System.out.println("3: View Pass Request By UID");
                            System.out.println("4: View Expired Passes");
                            System.out.println("5: View Passes withing the range");
                            System.out.println("6: Approve/Reject Pass Request");
                            System.out.println("7: Delete Pass");
                            System.out.println("0: To return back");
                            System.out.print("Enter Your Choice : ");
                            int passChoice=0;
                            try{
                                passChoice=Integer.parseInt(scanner.nextLine());
                            }catch (NumberFormatException e){
                                passChoice=8;//explicitly giving the wrong choice
                            }
                            if (passChoice == 1) {
                                routeService.viewRouteShort();
                                try {
                                    busPassService.viewPass();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input.");
                                }

                            } else if (passChoice == 2) {
                                routeService.viewRouteShort();
                                try {
                                    busPassService.viewPassRequests();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input.");
                                }

                            } else if (passChoice == 3) {
                                System.out.println("Enter User ID: ");

                                try {
                                    int uid = Integer.parseInt(scanner.nextLine());
                                    busPassService.viewPassRequestsByUser(uid);
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input.");
                                }

                            } else if (passChoice==4) {
                                busPassService.viewExpiredPasses();

                            } else if (passChoice==5) {
                                busPassService.vewPassInRange();

                            } else if (passChoice == 6) {

                                routeService.viewRouteShort();
                                try {
                                    if(busPassService.viewPassRequests())
                                        busPassService.approveRejectPassRequest();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input.");
                                }

                            } else if (passChoice == 7) {

                                try {
                                    if(busPassService.viewPass())
                                        busPassService.deletePass();
                                }catch (NumberFormatException e){
                                    System.out.println("Please enter a valid input.");
                                }

                            }else if (passChoice==0){
                                run=false;
                            }
                            else {
                                System.out.println("Invalid Pass Choice.\n");
                            }
                        }
                        break;
                    case 5:
                        run=true;
                        while (run) {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("1: View Feedbacks");
                            System.out.println("2: View Feedbacks by User");
                            System.out.println("3: Approve BusPass suspend requests");
                            System.out.println("4: Delete Feedback");
                            System.out.println("0: To return back");
                            System.out.print("Enter Your Choice : ");
                            int feedbackChoice=0;
                            try{
                                feedbackChoice=Integer.parseInt(scanner.nextLine());
                            }catch (NumberFormatException e){
                                feedbackChoice=5;//explicitly giving the wrong choice
                            }
                            if (feedbackChoice == 1) {
                                feedbackService.viewFeedbacks();
                            } else if (feedbackChoice == 2) {

                                System.out.print("Enter User ID : ");
                                try {
                                    int uid = Integer.parseInt(scanner.nextLine());
                                    feedbackService.viewFeedbacksByUser(uid);
                                }catch (NumberFormatException e){
                                    System.out.println("Please Enter a valid input.");
                                }

                            } else if (feedbackChoice == 3) {

                                try {
                                    feedbackService.passSuspendRequest();
                                }catch (NumberFormatException e){
                                    System.out.println("Please Enter a valid input.");
                                }

                            } else if (feedbackChoice == 4) {

                                try {
                                    if(feedbackService.viewFeedbacks())
                                        feedbackService.deleteFeedback();
                                }catch (NumberFormatException e){
                                    System.out.println("Please Enter a valid input.");
                                }

                            } else if (feedbackChoice == 0) {
                                run = false;
                            } else {
                                System.out.println("Invalid Choice..");
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Thank You for Using Admin Application....");
                        exitCode=true;
                        break;
                    default:
                        System.out.println("Please Enter a Valid Choice");
                }
            }
        }
        else
            System.out.println("Invalid Credentials Please Try Again...!!");
    }
}
