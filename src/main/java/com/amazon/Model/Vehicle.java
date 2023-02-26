package com.amazon.Model;
//This class contains the attributes od Vehicle
// with get details and prettyPrint Method

import java.util.Scanner;
public class Vehicle {
    public int id;
    public String registrationNumber;
    public int totalSeats;
    public int filledSeats;
    public int routeId;
    public int type;
    public int vehicleStatus;
    public String startPickUpTime;
    public String startDropOffTime;
    public int adminId;
    public int driverId;
    public String createdOn;

    public Vehicle() {}
    public Vehicle(int id, String registrationNumber, int totalSeats, int filledSeats, int routeId, int type,
                   int vehicleStatus, String startPickUpTime, String startDropOffTime, int adminId, int driverId, String createdOn) {
        super();
        this.id = id;
        this.registrationNumber = registrationNumber;
        this.totalSeats = totalSeats;
        this.filledSeats = filledSeats;
        this.routeId = routeId;
        this.type = type;
        this.vehicleStatus = vehicleStatus;
        this.startPickUpTime = startPickUpTime;
        this.startDropOffTime = startDropOffTime;
        this.adminId = adminId;
        this.driverId = driverId;
        this.createdOn = createdOn;
    }

    public void getVehicleDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Vehicle Details....");

        System.out.print("Enter Registration Details : ");
        registrationNumber=scanner.nextLine();

        System.out.print("Enter Pick Up Time : ");
        startPickUpTime =scanner.nextLine();

        System.out.print("Enter Drop Off Time : ");
        startDropOffTime =scanner.nextLine();

        System.out.print("Enter Total Seats : ");
        totalSeats =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Filled Seats : ");
        filledSeats =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Vehicle Type (1 : Bus 2 : Innova) : ");
        type =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Vehicle Status (1 : On Duty 2 : Under Maintenance) : ");
        vehicleStatus =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Route Id : ");
        routeId =Integer.parseInt(scanner.nextLine());

        System.out.print("Enter Driver Id : ");
        driverId =Integer.parseInt(scanner.nextLine());

    }

    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Vehicle ID  \t\t\t\t: "+id);
        System.out.println("Registration Number \t\t: "+registrationNumber);
        System.out.println("Total Seats \t\t\t\t: "+totalSeats);
        System.out.println("Filled Seats  \t\t\t\t: "+filledSeats);
        System.out.println("Available Seats \t\t\t: "+(totalSeats - filledSeats));
        String vehicle = (type == 1) ? "Bus" : "Innova";
        System.out.println("Vehicle Type  \t\t\t\t: "+vehicle);
        String status = (vehicleStatus == 1) ? "Available" : "Not Available";
        System.out.println("Vehicle Status \t\t\t\t: "+status);
        System.out.println("Start Pick Up Time \t\t\t: " + startPickUpTime);
        System.out.println("Start Drop Off Time \t\t: "+ startDropOffTime);
        System.out.println("RouteId \t\t\t\t\t: "+routeId);
        System.out.println("Driver Id \t\t\t\t\t: "+driverId);
        System.out.println("AdminId \t\t\t\t\t: "+adminId);
        System.out.println("CreatedOn \t\t\t\t\t: "+createdOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", registrationNumber=" + registrationNumber + ", totalSeats=" + totalSeats
                + ", filledSeats=" + filledSeats + ", routeId=" + routeId + ", type=" + type + ", vehicleStatus="+ vehicleStatus + ", startPickUpTime="
                + startPickUpTime + ", startDropOffTime=" + startDropOffTime + ", adminId=" + adminId + ", driverId="
                + driverId + ", createdOn=" + createdOn + "]";
    }
}
