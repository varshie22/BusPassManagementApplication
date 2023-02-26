package com.amazon.Controller;

import com.amazon.Menus.BusPassSession;
import com.amazon.DB.RouteDAO;
import com.amazon.DB.StopDAO;
import com.amazon.DB.VehicleDAO;
import  com.amazon.Model.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

//To handle Route, Stop and Vehicle
public class RouteService {
    RouteDAO routeDAO = new RouteDAO();
    StopDAO stopDAO = new StopDAO();
    VehicleDAO vehicleDAO = new VehicleDAO();

    private static RouteService routeService = new RouteService();
    Scanner scanner = new Scanner(System.in);
    public static RouteService getInstance() {
        return routeService;
    }
    private RouteService() {}
    //handler for Route
    public void addRoute() {
        Route route = new Route();
        route.getDetails();
        route.adminId = BusPassSession.user.id;
        int result=routeDAO.insert(route);
        String message = (result > 0) ? "Route Added Successfully" : "Adding Route Failed Please Try Again...";
        System.out.println(message);
    }
    public void deleteRoute() throws NumberFormatException{
        Route route = new Route();
        System.out.println("Enter Route Id to be Deleted : ");
        route.id = Integer.parseInt(scanner.nextLine());
        int result = routeDAO.delete(route);
        String message = (result > 0) ? "Route Deleted Successfully" : "Deleting Route Failed Please Try Again...";
        System.out.println(message);
    }
    public void updateRoute() throws NumberFormatException{
        System.out.print("Enter Route ID to be Updated : ");
        int id=Integer.parseInt(scanner.nextLine());
        RouteDAO dao=new RouteDAO();
        String sql="Select * from Route Where id="+id;
        List<Route> routes=dao.retrieve(sql);
        if(routes.size()>0){
            System.out.println("Press Enter to skip the column");
            Route route=routes.get(0);
            System.out.print("Enter The Title : ");
            String title= scanner.nextLine();
            if(!title.isEmpty()){
                route.title=title;
            }
            System.out.print("Enter Description: ");
            String description=scanner.nextLine();
            if(!description.isEmpty()){
                route.description=description;
            }
            if(dao.update(route)>0){
                System.out.println("DB Updated Successfully");
            }
            else {
                System.out.println("DB Not Updated Something Went Wrong...");
            }
        }
        else {
            System.out.println("Updating Route Failed. Try again...");
        }
    }
    public void viewRouteShort(){
        List<Route> objects = routeDAO.retrieve();
        System.out.println(" ~Available Routes~ ");
        for(Route object : objects) {
            System.out.print("\n Route ID : "+object.id);
            System.out.println(" | Route Title : "+object.title);
        }
        System.out.println("``````````````````````````````````````");
    }
    public void viewRoute() throws NumberFormatException{
        List<Route> objects = routeDAO.retrieve();
        for(Route object : objects) {
            object.prettyPrint();
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Do You Wish to View the Stops & Vehicles For Any Particular Route");
        System.out.print("If Yes Then Enter the Route ID or 0 Cancel : ");
        int routeId = Integer.parseInt(scanner.nextLine());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Route route =null;
        for(Route object :objects) {
            if(object.id == routeId) {
                route = object;
            }
        }
        if(routeId!=0 && route==null){
            System.out.println("Route Not Exist....\n");
            return;
        }
        if(routeId != 0 && route != null) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Listing Details For The Route : " + route.title);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("***************");
            System.out.println("STOPS on Route");
            System.out.println("***************");
            String sql = "SELECT * from Stop where routeId = "+routeId;
            List<Stop>	filteredStops = stopDAO.retrieve(sql);
            if(filteredStops.isEmpty()){
                System.out.println("No stops added");
            }
            for(Stop object : filteredStops) {
                object.prettyPrint();
            }
            System.out.println("***************");
            System.out.println("VEHICLES on Route");
            System.out.println("***************");
            sql = "SELECT * from Vehicle where routeId = "+routeId;

            List<Vehicle>	filteredVehicles = vehicleDAO.retrieve(sql);
            if(filteredVehicles.isEmpty()){
                System.out.println("No vehicles added");
            }
            for(Vehicle object : filteredVehicles) {
                object.prettyPrint();
            }
        }
    }
    //handler for Stop
    public void addStop()  throws NumberFormatException, SQLException {
        Stop stop = new Stop();
        stop.getStopDetails();
        stop.adminId = BusPassSession.user.id;
        int result=stopDAO.insert(stop);
        String message = (result > 0) ? "Stop Added Successfully" : "Adding Route Failed Please Try Again...";
        System.out.println(message);
    }
    public void deleteStop() throws NumberFormatException{
        Stop stop = new Stop();
        System.out.println("Enter Stop Id to be Deleted : ");
        stop.id = Integer.parseInt(scanner.nextLine());
        int result = stopDAO.delete(stop);
        String message = (result > 0) ? "Stop Deleted Successfully" : "Deleting Stop Failed Please Try Again...";
        System.out.println(message);
    }
    public void updateStop() throws NumberFormatException{
        System.out.print("Enter stop Id : ");
        int id = Integer.parseInt(scanner.nextLine());
        StopDAO dao = new StopDAO();
        String sql = "Select * from Stop where Id = "+id;
        List<Stop> stops=dao.retrieve(sql);
        if(stops.size()>0){
            System.out.println("Press Enter to skip the column.");
            Stop stop=stops.get(0);
            System.out.print("Enter the Address : ");
            String address= scanner.nextLine();
            if(!address.isEmpty()){
                stop.address=address;
            }
            System.out.print("Enter Sequence Order : ");
            String sequenceOrder=scanner.nextLine();
            if(!sequenceOrder.isEmpty()){
                stop.sequenceOrder=Integer.parseInt(sequenceOrder);
            }
            if(dao.update(stop)>0){
                System.out.println("Stop Updated Successfully");
            }
            else {
                System.out.println("Stop not Updated Something Went Wrong Please Try Again...");
            }
        }
        else {
            System.out.println("Updating Stop Failed Please Try again...");
        }
    }
    public void viewStop() throws NumberFormatException{
        System.out.println("Enter Route Id to Get all the Stop on Route");
        System.out.println("or 0 for all Stops");
        System.out.println("Enter Route Id : ");
        int routeId = Integer.parseInt(scanner.nextLine());

        List<Stop> objects = null;

        if(routeId == 0) {
            objects = stopDAO.retrieve();
        }else {
            String sql = "SELECT * from Stop Where routeId = "+routeId + " order by sequenceOrder";
            objects = stopDAO.retrieve(sql);
        }
        if(objects.isEmpty()){
            System.out.println("No stops exist on this route");
        }
        for(Stop object : objects) {
            object.prettyPrint();
        }
    }
    //handler for vehicle
    public void addVehicle() throws NumberFormatException {
        Vehicle vehicle = new Vehicle();
        vehicle.getVehicleDetails();
        vehicle.adminId = BusPassSession.user.id;
        int result;
        if(vehicle.totalSeats<vehicle.filledSeats){
            result=0;
        }
        else{
            result=vehicleDAO.insert(vehicle);
        }
        String message = (result > 0) ? "Vehicle Added Successfully" : "Adding vehicle Failed Please Try Again...";
        System.out.println(message);
    }
    public void deleteVehicle() throws NumberFormatException{
        Vehicle vehicle = new Vehicle();
        System.out.print("Enter Vehicle ID to be Deleted : ");
        vehicle.id = Integer.parseInt(scanner.nextLine());
        int result = vehicleDAO.delete(vehicle);
        String message = (result > 0) ? "Vehicle Deleted Successfully" : "Deleting Vehicle Failed Please Try Again...";
        System.out.println(message);
    }
    public void updateVehicle() throws NumberFormatException{
        System.out.print("Enter Vehicle ID to be Updated : ");
        int id = Integer.parseInt(scanner.nextLine());
        VehicleDAO dao = new VehicleDAO();
        String sql = "Select * from Vehicle where id =" +id;
        List<Vehicle> vehicles=dao.retrieve(sql);
        if(vehicles.size()>0){
            System.out.println("Press Enter to skip the column.");
            Vehicle vehicle=vehicles.get(0);
            System.out.println("Total number of seats are : "+vehicle.totalSeats);
            System.out.print("Enter Filled Seat : ");
            String filledSeat = scanner.nextLine();
            if(!filledSeat.isEmpty()){

                vehicle.filledSeats=Integer.parseInt(filledSeat);
                if(vehicle.totalSeats<vehicle.filledSeats){
                    System.out.println("Filled seats cannot be more than the total seats.");
                    System.out.println("Updating Stop Failed Please Try again...");
                    return;
                }

            }
            System.out.print("Enter Vehicle Status (1 : On Duty 2 : Under Maintenance) : ");
            String status =scanner.nextLine();
            if(!status.isEmpty()){
                vehicle.vehicleStatus= Integer.parseInt(status);
            }

            System.out.print("Enter Vehicle Start Pick Up Time : ");
            String startPickUpTime = scanner.nextLine();
            if(!startPickUpTime.isEmpty()){
                vehicle.startPickUpTime=startPickUpTime;
            }

            System.out.print("Enter Vehicle Start Drop Off Time : ");
            String startDropOffTime = scanner.nextLine();
            if(!startDropOffTime.isEmpty()){
                vehicle.startDropOffTime=startDropOffTime;
            }
            if(dao.update(vehicle)>0){
                System.out.println("Vehicle Updated Successfully");
            }
            else {
                System.out.println("Vehicle Not Updated Something Went Wrong Please Try Again...");
            }
        }
        else {
            System.out.println("Updating Stop Failed Please Try again...");
        }
    }
    public void viewVehicle() throws NumberFormatException{
        System.out.println("Enter Route Id to Get All the Vehicle on Route");
        System.out.println("or 0 for All Vehicle");
        System.out.println("Enter Route Id : ");

        int routeId = Integer.parseInt(scanner.nextLine());
        List<Vehicle> objects = null;
        if(routeId == 0) {
            objects = vehicleDAO.retrieve();
        }else {
            String sql = "SELECT * from Vehicle Where routeId = "+routeId;
            objects = vehicleDAO.retrieve(sql);
        }
        if(objects.isEmpty()){
            System.out.println("No vehicle exist on this route.");
        }
        for(Vehicle object : objects) {
            object.prettyPrint();
        }
    }
}

