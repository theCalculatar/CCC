package com.example.ccc.login;

public class User {

    public String firstName, lastName, email, password, picture = null;

    public User(){

    }
    public User(String firstName, String email, String password){
        this.firstName = firstName;
        this.email = email;
        this.password = password;
    }
    public User(String firstName, String lastName, String email, String password, String picture){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.picture = picture;
    }
}
