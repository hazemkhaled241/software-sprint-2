package com.example.demo.softwareproject.model;

public class User {
    private String userName;
    private String email="";
    private String password;
    public User(String userName, String password){
        this.userName=userName;
        this.password=password;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
