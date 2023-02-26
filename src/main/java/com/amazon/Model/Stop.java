package com.amazon.Model;
//This class contains the attributes of Stop
// with get details and prettyPrint Method


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Scanner;

public class Stop {
    public int id;
    public String address;
    public int sequenceOrder;
    public int routeId;
    public int adminId;
    public String createdOn;

    public Stop() {
    }
    public Stop(int id, String address, int sequenceOrder, int routeId, int adminId, String createdOn) {
        super();
        this.id = id;
        this.address = address;
        this.sequenceOrder = sequenceOrder;
        this.routeId = routeId;
        this.adminId = adminId;
        this.createdOn = createdOn;
    }

    public void getStopDetails() throws NumberFormatException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Route Id : ");
        routeId =Integer.parseInt(scanner.nextLine());

        System.out.println("Capturing Stop Details....");

        System.out.println("Enter Stop Address : ");
        address=scanner.nextLine();

        System.out.println("Enter Sequence Order : ");
        sequenceOrder =Integer.parseInt(scanner.nextLine());

    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Stop ID \t\t: "+id);
        System.out.println("Address \t\t: "+address);
        System.out.println("Sequence Order \t: "+sequenceOrder);
        System.out.println("Route \t\t\t: "+routeId);
        System.out.println("Admin \t\t\t: "+adminId);
        System.out.println("createdOn \t\t "+createdOn);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "Stop [id=" + id + ", address=" + address + ", sequenceOrder=" + sequenceOrder + ", routeId=" + routeId
                + ", adminId=" + adminId + ", createdOn=" + createdOn + "]";
    }
}

