package com.example.ccc.login;

public class User {

    public String firstName,email, password, phoneNumber="", picture = null;

    public User(){

    }

    public String getFirstName() {
        return firstName;
    }

    public User(String firstName, String email, String password){
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }
    public User(String firstName, String email, String password,String phoneNumber){
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
    }
}
