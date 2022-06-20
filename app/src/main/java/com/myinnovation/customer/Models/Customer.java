package com.myinnovation.customer.Models;

public class Customer {

    private  String mess_email, mess_location, Phone_no, mess_name, monthlyPrice, owner_name, specialDishes, customer_id;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getMess_name() {
        return mess_name;
    }

    public String getMess_email() {
        return mess_email;
    }

    public void setMess_email(String mess_email) {
        this.mess_email = mess_email;
    }

    public String getMess_location() {
        return mess_location;
    }

    public void setMess_location(String mess_location) {
        this.mess_location = mess_location;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public void setMess_name(String mess_name) {
        this.mess_name = mess_name;
    }

    public String getMonthlyPrice() {
        return monthlyPrice;
    }

    public void setMonthlyPrice(String monthlyPrice) {
        this.monthlyPrice = monthlyPrice;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getSpecialDishes() {
        return specialDishes;
    }

    public void setSpecialDishes(String specialDishes) {
        this.specialDishes = specialDishes;
    }

    public Customer(){}

    public Customer(String email, String location, String phone_no, String mess_name, String monthlyPrice, String owner_name, String specialDishes) {
        this.mess_email = email;
        this.mess_location = location;
        this.Phone_no = phone_no;
        this.mess_name = mess_name;
        this.monthlyPrice = monthlyPrice;
        this.owner_name = owner_name;
        this.specialDishes = specialDishes;
        this.customer_id = "";
    }
}
