package com.amazon.Model;
//This class contains the attributes od User
// with get details and prettyPrint Method


/*
// SQL Query to create the Table of User in Data Base.
MYSQL:
  create table User(
      id INT PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(256),
      phone VARCHAR(256),
      email VARCHAR(256),
      password VARCHAR(256),
      address VARCHAR(256),
      department VARCHAR(256),
      type INT,
      createdOn DATETIME DEFAULT CURRENT_TIMESTAMP
  );
 
 */
public class User {
    public int id;
    public String name;
    public String phone;
    public String email;
    public String password;
    public String address;
    public String department;
    public int type;
    public String createOn;
    public User() {}
    public User(int iD, String name, String phone,String email, String password, String address, String department, int type,
                String createOn) {
        super();
        this.id = iD;
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.address = address;
        this.department = department;
        this.type = type;
        this.email=email;
        this.createOn = createOn;
    }
    public void prettyPrint() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Name \t\t\t : "+name);
        System.out.println("phone \t\t\t : "+phone);
        System.out.println("Email  \t\t\t : "+email);
        System.out.println("Address \t\t : "+address);
        System.out.println("Department \t\t : "+department);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password=" + password
                + ", address=" + address + ", department=" + department + ", type=" + type + ", createOn=" + createOn
                + "]";
    }

}
