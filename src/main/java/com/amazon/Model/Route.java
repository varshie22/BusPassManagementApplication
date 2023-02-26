package com.amazon.Model;
//This class contains the attributes od Routes
// with get details and prettyPrint Method
import java.util.Scanner;

public class Route {
    //attributes
    public int id;
    public String title;
    public String description;
    public int adminId;

    public String createdOn;
    public Route() {}
    public Route(int id, String title, String description, int adminId, String createdOn) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.adminId = adminId;
        this.createdOn = createdOn;
    }
    public void getDetails() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Capturing Route Details....");

        System.out.println("Enter Route Title : ");
        title=scanner.nextLine();

        System.out.println("Enter Route Description : ");
        description =scanner.nextLine();
    }
    public void prettyPrint() {
        boolean shrink=false;
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Route ID \t\t: "+id);
        System.out.println("Title \t\t\t: "+title);
        System.out.println("Description \t: "+description);
        if(shrink==false){
            System.out.println("Admin ID \t\t: "+adminId);
            System.out.println("Created On \t\t: "+createdOn);
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    @Override
    public String toString() {
        return "Route [id=" + id + ", title=" + title + ", description=" + description + ", adminId=" + adminId
                + ", createdOn=" + createdOn + "]";
    }
}

