package com.amazon.Controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import com.amazon.DB.BusPassDAO;
import com.amazon.Menus.BusPassSession;
import com.amazon.DB.FeedbackDAO;
import com.amazon.Model.BusPass;
import com.amazon.Model.Feedback;

public class FeedbackService {
    FeedbackDAO feedbackDAO = new FeedbackDAO();
    BusPassDAO busPassDAO=new BusPassDAO();
    // Create it as a Singleton
    private static FeedbackService feedbackService = new FeedbackService();

    Scanner scanner = new Scanner(System.in);
    public static FeedbackService getInstance() {
        return feedbackService;
    }
    private FeedbackService() {}

    // Handler for the Feedback
    public void createFeedback() throws NumberFormatException{
        Feedback feedback = new Feedback();
        feedback.getDetails();

        // Add the User ID Implicitly.
        feedback.userId = BusPassSession.user.id;
        feedback.raisedBy = BusPassSession.user.email;
        if(feedback.type==3){
            //if user selects 3 (request for suspension)
            //then here the pass is added implicitly at the end of the description
            System.out.print("Enter the pass Id which needs to be suspended: ");
            int passId=Integer.parseInt(scanner.nextLine());

            String sql="select * from buspass where id="+passId+" and uid="+BusPassSession.user.id;
            List<BusPass> busPassList=busPassDAO.retrieve(sql);
            if(busPassList.isEmpty()){
                System.out.println("Please enter a valid pass Id");
                return;
            }
            feedback.description+="| passId = "+passId;//pass id added implicitly at the end
        }
        int result = feedbackDAO.insert(feedback);
        String message = (result > 0) ? "Feedback Created Successfully" : "Creating Feedback Failed. Try Again..";
        System.out.println(message);
    }
    public void deleteFeedback() throws NumberFormatException{
        Feedback feedback = new Feedback();
        System.out.println("Enter Feedback ID to be deleted: ");
        feedback.id = Integer.parseInt(scanner.nextLine());
        int result = feedbackDAO.delete(feedback);
        String message = (result > 0) ? "Feedback Deleted Successfully" : "Deleting Feedback Failed. Try Again..";
        System.out.println(message);
    }
    public boolean viewFeedbacks() {
        List<Feedback> objects = feedbackDAO.retrieve();
        if(objects.isEmpty()){
            System.out.println("No feedbacks.");
            return false;
        }
        for(Feedback object : objects) {
            object.prettyPrint();
        }
        return true;
    }
    public void viewFeedbacksByUser(int uid) {

        String sql = "SELECT * from Feedback where userId = "+uid;

        List<Feedback> objects = feedbackDAO.retrieve(sql);

        if(objects.isEmpty()){
            System.out.println("No feedbacks.");
        }
        for(Feedback object : objects) {
            object.prettyPrint();
        }
    }
    public void passSuspendRequest() throws NumberFormatException{
        String sql = "SELECT * from Feedback where type = 3";

        List<Feedback> objects = feedbackDAO.retrieve(sql);
        if(objects.isEmpty()){
            System.out.println("No pass requests");
            return;
        }
        for(Feedback object : objects) {
            object.prettyPrint();
        }

        System.out.println("Enter FeedBack ID: ");
        int feedbackID=Integer.parseInt(scanner.nextLine());

        sql="select * from Feedback where id="+feedbackID+" and type=3";
        List<Feedback> feedbacks=feedbackDAO.retrieve(sql);
        if (feedbacks.isEmpty()){
            System.out.println("Enter valid feedback id");
            return;
        }

        //extracting the pass ID from the description
        String description=feedbacks.get(0).description;
        int passId=description.charAt(description.length()-1)-'0';

        String sql2="SELECT * from buspass where id= "+passId;
        List<BusPass> busPasses = busPassDAO.retrieve(sql2);

        if(busPasses.size()>0){
            BusPass busPass=busPasses.get(0);
            if(busPass.status==4){
                System.out.println("The pass is already suspended.");
                return;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date date1 = calendar.getTime();

            busPass.validTill = dateFormat.format(date1);
            busPass.status=4;//suspend status
            int result=busPassDAO.update(busPass);
            String message = (result > 0) ? "Pass suspended Successfully" : "Suspending Pass Failed. Try Again..";
            System.out.println(message);
        }
        else{
            System.out.println("User not found.");
        }
    }
}
