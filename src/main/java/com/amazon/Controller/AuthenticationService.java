package com.amazon.Controller;

import com.amazon.Model.User;
import com.amazon.DB.*;
import java.util.*;
public class AuthenticationService {
    private static AuthenticationService auth= new AuthenticationService();
    UserDAO dao=new UserDAO();
    private AuthenticationService() {}
    public boolean loginUser(User user) {
        String sql= "SELECT * FROM User WHERE email = '"+user.email+"' AND password = '"+user.password+"'";
        List<User> users = dao.retrieve(sql);

        if(users.size()>0) {
            User u = users.get(0);// to access first element of the list
            user.id = u.id;
            user.name=u.name;
            user.phone=u.phone;
            user.email=u.email;
            user.address=u.address;
            user.department=u.department;
            user.type=u.type;
            user.createOn=u.createOn;
            return true;
        }
        return false;
    }
    public boolean registerUser(User user) {
        int result = dao.insert(user);
        return result > 0;
    }
    public boolean updateUser(User user) {
        int result = dao.update(user);
        return result > 0;
    }
    public static AuthenticationService getInstance() {
        return auth;
    }
}
