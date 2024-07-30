package com.example.ccc.login;

public class User {

    public String firstName,email, password, picture = null;

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
}
