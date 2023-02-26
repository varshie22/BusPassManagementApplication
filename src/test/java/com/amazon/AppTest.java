package com.amazon;

import com.amazon.DB.DB;
import com.amazon.DB.FeedbackDAO;
import com.amazon.DB.StopDAO;
import com.amazon.DB.VehicleDAO;
import com.amazon.Model.*;
import org.junit.Assert;
import org.junit.Test;
import com.amazon.Controller.AuthenticationService;
import java.util.List;
public class AppTest {

    AuthenticationService authService = AuthenticationService.getInstance();
    FeedbackDAO feedbackDAO=new FeedbackDAO();
    StopDAO stopDAO = new StopDAO();
    VehicleDAO vehicleDAO=new VehicleDAO();
    public AppTest(){
        DB.getInstance().loadDriver();
    }
    // UNIT TESTS

    @Test
    public void testUserLogin() {

        User user = new User();
        user.email = "qwe@gmail.com";
        user.password = "123";
        boolean result = authService.loginUser(user);

        // Assertion -> Either Test Cases Passes or It will Fail :)
        //(expected, what we are expecting)
        Assert.assertEquals(true, result);
        Assert.assertEquals(2, user.type);

    }
    @Test
    public void testAdminLogin() {

        User user = new User();
        user.email = "varsha@abc.com";
        user.password = "123";

        boolean result = authService.loginUser(user);

        // Assertion -> Either Test Cases Passes or It will Fail :)
        Assert.assertEquals(true, result);
        Assert.assertEquals(1, user.type); // 1 should be equal to 1

    }
    @Test
    public void testVehicleExistOnRoute(){
        int vehicleId=4;
        int routeId=4;
        String sql="select * from vehicle where id="+vehicleId+" and routeId="+routeId;
        List<Vehicle> vehicleList=vehicleDAO.retrieve(sql);
        Assert.assertEquals(false, vehicleList.isEmpty());

    }

    @Test
    public void routeWithMin2Stops(){
        int routeId=3;
        String sql="select * from stop where routeId="+routeId;
        List<Stop> stopList=stopDAO.retrieve(sql);

        Assert.assertEquals(true, stopList.size()>=2);

    }
    @Test
    public void testStopExistOnRoute(){
        int stopId=4;
        int routeId=4;
        String sql="select * from stop where id="+stopId+" and routeId="+routeId;
        List<Stop> stopList=stopDAO.retrieve(sql);
        Assert.assertEquals(false, stopList.isEmpty());

    }
    @Test
    public void userCanComplaint(){
        Feedback feedback=new Feedback();
//        feedback.id = 4;
        feedback.userId = 2;
        feedback.description = "description";
        feedback.type = 3;
        feedback.raisedBy="asdasd";
        int result=feedbackDAO.insert(feedback);
        Assert.assertEquals(true,result>0);

    }

}
