package com.amazon.Controller;

import com.amazon.DB.RouteDAO;
import com.amazon.Menus.BusPassSession;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import com.amazon.DB.BusPassDAO;
import com.amazon.Model.BusPass;
import com.amazon.Model.Route;

public class BusPassService {
    BusPassDAO passDAO = new BusPassDAO();
    RouteDAO routeDAO=new RouteDAO();
    private static BusPassService passService = new BusPassService();
    Scanner scanner = new Scanner(System.in);

    public static BusPassService getInstance() {
        return passService;
    }
    private BusPassService() {}

    // Handler for the Bus Pass
    public void requestPass()  {
        BusPass pass = new BusPass();
        pass.getDetails();
        //route existance check
        String sql="select * from route where id="+pass.routeId;
        List<Route> routeList=routeDAO.retrieve(sql);
        if(routeList.isEmpty()) {
            System.out.println("Please enter a valid route ID");
            return;
        }
        sql="select * from buspass where uid="+BusPassSession.user.id+" and routeId="+pass.routeId;
        List<BusPass> busPassList=passDAO.retrieve(sql);

        if(!busPassList.isEmpty()){
            if(busPassList.get(0).status==1||busPassList.get(0).status==2) {
                System.out.println("Already applied to this route");
                return;
            }
        }
        // Add the User ID Implicitly.
        pass.uid = BusPassSession.user.id;
        int result= passDAO.insert(pass);

        String message = (result > 0) ? "Pass Requested Successfully" : "Request for Pass Failed. Try Again..";
        System.out.println(message);
    }
    public void deletePass() {
        BusPass pass = new BusPass();
        System.out.println("Enter Pass ID to be deleted: ");
        pass.id = Integer.parseInt(scanner.nextLine());
        int result = passDAO.delete(pass);
        String message = (result > 0) ? "Pass Deleted Successfully" : "Deleting Pass Failed. Try Again..";
        System.out.println(message);
    }
    public void approveRejectPassRequest() {

        BusPass pass = new BusPass();
        System.out.println("Enter Pass ID: ");
        pass.id = Integer.parseInt(scanner.nextLine());
        boolean run=true;
        while(run) {
            System.out.println("2: Approve");
            System.out.println("3: Cancel");
            System.out.print("Enter Approval Choice: ");
            pass.status = Integer.parseInt(scanner.nextLine());
            if (pass.status != 2 && pass.status != 3) {
                System.out.println("Please choose a valid choice.");
            }
            else
                run=false;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();
        pass.approvedRejectedOn = dateFormat.format(date1);

        if(pass.status == 2) {
            calendar.add(Calendar.YEAR, 1);// by default providing 1 year validity to the user
            Date date2 = calendar.getTime();
            pass.validTill = dateFormat.format(date2);
        }else {
            pass.validTill = pass.approvedRejectedOn;
        }
        int result = passDAO.update(pass);
        String message = (result > 0) ? "Pass Request Updated Successfully" : "Updating Pass Request Failed. Try Again..";
        System.out.println(message);
    }
    public void viewExpiredPasses(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date1 = calendar.getTime();//current date and time
        String sql="select * from buspass where validTill < '"+dateFormat.format(date1)+"'";
        List<BusPass> busPassList = passDAO.retrieve(sql);
        if(busPassList.isEmpty()) {
            System.out.println("No expired passes.");
            return;
        }
        System.out.println("Expired Buss Passes are: ");
        System.out.println("```````````````````````````");
        for (BusPass busPass:busPassList){
            busPass.prettyPrint();
        }
    }
    public void vewPassInRange(){
        System.out.println("Enter the date range in format (YYYY-MM-DD hh:mm:ss)");

        System.out.print("From: ");
        String from=scanner.nextLine();
        System.out.print("Till: ");
        String till=scanner.nextLine();

        String sql="select * from buspass where validTill BETWEEN '"+ from +"' AND '"+till+"'";
        List<BusPass> busPassList = passDAO.retrieve(sql);
        if(busPassList.isEmpty()) {
            System.out.println("No passes in this range.");
            return;
        }
        System.out.println(" __________________________");
        System.out.println("| Expired Buss Passes are: |");
        System.out.println(" --------------------------");
        for (BusPass busPass:busPassList){
            busPass.prettyPrint();
        }
    }

    //for admin
    public boolean viewPass() throws NumberFormatException{

        System.out.println("Enter Route ID to get All the Pass Requests for a Route");
        System.out.println("Or 0 for All Bus Pass Requests");
        System.out.println("Enter Route ID: ");
        int routeId = Integer.parseInt(scanner.nextLine());

        List<BusPass> objects = null;

        if(routeId == 0) {
            objects = passDAO.retrieve();
        }else {
            String sql = "SELECT * from BusPass where routeId="+routeId;
            objects = passDAO.retrieve(sql);
        }
        if(objects.isEmpty()){
            System.out.println("No passes.");
            return false;//represents no passes in db
        }
        for(BusPass object : objects) {
            object.prettyPrint();
        }
        return true;//represents existence of passes in DB
    }

    //for user
    public void viewPass(int uid) {
        String sql = "SELECT * from BusPass where uid="+uid;
        List<BusPass> busPassList = passDAO.retrieve(sql);

        for(BusPass busPass:busPassList) {
            busPass.prettyPrint();
        }
    }

    //for admin
    public boolean viewPassRequests() throws NumberFormatException{

        System.out.println("Enter Route ID to get All the Pass Requests for a Route");
        System.out.println("Or 0 for All Bus Pass Requests");
        System.out.println("Enter Route ID: ");
        int routeId = Integer.parseInt(scanner.nextLine());

        List<BusPass> objects = null;
        String sql = "SELECT * from BusPass where status = 1";
        if(routeId == 0) {
            objects = passDAO.retrieve(sql);
        }else {
            sql+=" and routeId="+routeId;
            objects = passDAO.retrieve(sql);
        }
        if(objects.isEmpty()){
            System.out.println("No pass requests.");
            return false;
        }
        for(BusPass object : objects) {
            object.prettyPrint();
        }
        return true;//shows presence of pass requests
    }

    //for user
    public void viewPassRequestsByUser(int uid) {

        String sql = "SELECT * from BusPass where status = 1 and uid = "+uid;
        List<BusPass> objects = passDAO.retrieve(sql);
        if(objects.isEmpty()){
            System.out.println("No pass request from this user.");
        }
        for(BusPass object : objects) {
            object.prettyPrint();
        }
    }
    public void passUpdate(int uid)  throws NumberFormatException{
        String sql = "SELECT * from BusPass where uid = "+uid;
        List<BusPass> objects = passDAO.retrieve(sql);
        for(BusPass object : objects) {
            object.prettyPrint();
        }
        System.out.print("\nEnter the passId who's date is to be updated: ");
        int passID=Integer.parseInt(scanner.nextLine());
        BusPass busPass=null;
        for(BusPass object:objects){
            if(passID==object.id){
                busPass=object;
                break;
            }
        }
        if(busPass==null){
            System.out.println("Pass with the given id not found");
        }else{
            if(busPass.status!=2){
                System.out.println("BusPass status not in the state of updation.");
                return;
            }
            System.out.println("Pass needs to be updated by:");
            System.out.println("1. Year \n 2. Month \n 3. Days");
            System.out.print("Your choice: ");
            int choice=Integer.parseInt(scanner.nextLine());
            if(choice!=1 && choice!=2 && choice!=3)
            {
                System.out.println("Wrong choice..\n");
                return;
            }
            System.out.println("Update by how many years/months/days.");
            int tenure=Integer.parseInt(scanner.nextLine());

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date date1 = calendar.getTime();//current date and time
            // converting years/months to days for simpler calculations
            if(choice==1) {
                tenure=tenure*365;
            }
            else if (choice==2) {
                tenure=tenure*30;
            }
            try {
                Date date2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(busPass.validTill);
                long diff=date2.getTime()-date1.getTime();// difference in millisec
                long difference_In_Days = TimeUnit.MILLISECONDS.toDays(diff)%365+tenure;

                calendar.add(Calendar.DATE, (int) difference_In_Days);//updated date
                Date date3 = calendar.getTime();
                busPass.validTill = dateFormat.format(date3);

                int result=passDAO.update(busPass);
                String message = (result > 0) ? "Pass updated Successfully" : "Updating Pass Failed. Try Again..";
                System.out.println(message);

            }catch (Exception e){
                System.out.println(e.getStackTrace());
            }

        }
    }
}
