package com.myinnovation.customer;

public class Customer {

    private  String Email, location, Phone_no, mess_name, monthlyPrice, owner_name, specialDishes;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone_no() {
        return Phone_no;
    }

    public void setPhone_no(String phone_no) {
        Phone_no = phone_no;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getMess_name() {
        return mess_name;
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

    public Customer(String email, String location, String phone_no, String mess_name, String monthlyPrice, String owner_name, String specialDishes) {
        Email = email;
        this.location = location;
        Phone_no = phone_no;
        this.mess_name = mess_name;
        this.monthlyPrice = monthlyPrice;
        this.owner_name = owner_name;
        this.specialDishes = specialDishes;
    }
}
