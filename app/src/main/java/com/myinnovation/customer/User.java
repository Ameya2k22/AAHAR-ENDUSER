package com.myinnovation.customer;

public class User {

    private  String Name, Email, Password, Phone_no, customer_id;

    public String getName() {
        return Name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    private User(){}

    public User(String name, String email, String password, String phone_no) {
        Name = name;
        Email = email;
        Password = password;
        Phone_no = phone_no;
    }
}
