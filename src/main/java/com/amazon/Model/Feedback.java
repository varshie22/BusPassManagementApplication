package com.amazon.Model;

//This class contains the attributes od Feedback
// with get details and prettyPrint Method

import java.util.Scanner;
public class Feedback {

    public int id;
    public int userId;	// user id for the user who raised the feedback
    public String title;
    public String description;
    public int type;
    public String raisedBy; // email
    public String createdOn;
    public Feedback() {}
    public Feedback(int id, int userId, String title, String description, int type, String raisedBy, String createdOn) {
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.type = type;
        this.raisedBy = raisedBy;
        this.createdOn = createdOn;
    }
    public void getDetails() {

        Scanner scanner = new Scanner(System.in);
        boolean run=true;
        while (run) {
            System.out.println("Capturing Feedback Details....");
            System.out.println("1: Suggestion");
            System.out.println("2: Complaint");
            System.out.println("3: Pass Suspension");
            System.out.print("Select Type of Feedback : ");
            type = Integer.parseInt(scanner.nextLine());
            if (type == 1) {
                title = "Suggestion";
                run=false;
            } else if (type == 2) {
                title = "Complaint";
                run=false;
            } else if (type == 3) {
                title = "Pass Suspension";
                run=false;
            } else {
                System.out.println("Please enter a valid option");
            }
        }
        System.out.println("Enter Description:");
        description = scanner.nextLine();
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~Feedback~~~~~~~~~~");
        System.out.println("Feedback ID \t\t\t\t : "+id);
        System.out.println("Title \t\t\t\t : "+title);
        System.out.println("Description \t\t\t\t : "+description);
        System.out.println("Type \t\t\t\t : "+type);
        System.out.println("Raised By \t\t\t\t : "+raisedBy);
        System.out.println("Created On \t\t\t\t : "+createdOn);
    }

    @Override public String toString() {
        return "Feedback [id=" + id + ", userId=" + userId + ", title=" + title + ", description=" + description
                + ", type=" + type + ", raisedBy=" + raisedBy + ", createdOn=" + createdOn + "]";
    }
}
