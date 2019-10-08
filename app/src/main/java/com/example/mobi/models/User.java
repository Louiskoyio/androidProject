package com.example.mobi.models;

import java.util.Objects;

public class User {
    private String user_id;
    private String fname;
    private String lname;
    private String email;
    private String pword;
    private Double balance;
    private String phone_number;

    public User(String fname, String lname, String email, String pword, Double balance, String phone_number) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pword = pword;
        this.balance = balance;
        this.phone_number = phone_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return balance == user.balance &&
                Objects.equals(user_id, user.user_id) &&
                Objects.equals(fname, user.fname) &&
                Objects.equals(lname, user.lname) &&
                Objects.equals(email, user.email) &&
                Objects.equals(pword, user.pword) &&
                Objects.equals(phone_number, user.phone_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_id, fname, lname, email, pword, balance, phone_number);
    }
}
