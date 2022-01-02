package com.example.demo.softwareproject.view;


import com.example.demo.softwareproject.controler.AdminController;
import com.example.demo.softwareproject.controler.ClientController;
import com.example.demo.softwareproject.controler.DriverController;
import com.example.demo.softwareproject.model.Admin;
import com.example.demo.softwareproject.model.Client;
import com.example.demo.softwareproject.model.DataBaseSystem;
import com.example.demo.softwareproject.model.Driver;

public class SignIn {
    DataBaseSystem dataBaseSystem= DataBaseSystem.getInstance();

    public Client clientSignIN(String userName, String password)
    {
        Client client = dataBaseSystem.searchForClient ( new Client ( userName ,password ) );
        if ( client.getUserName ( ).equals ( "-1" ) ) {

            return null;
        }
        else
        {
            return client;
        }
    }
    public Driver driverSignIN(String userName, String password)
    {
        Driver driver = dataBaseSystem.searchForDriver ( new Driver ( userName ,password ) );
        if ( dataBaseSystem.searchForUser ( driver ) ) {

            return driver;
        }
        else {

            return null;
        }
    }
    public Admin adminSignIN (String userName, String password ) {
        Admin admin = new Admin ( userName ,password );
        if ( dataBaseSystem.searchForUser ( admin ) ) {
            return admin ;
        }
        else {
            System.out.println ( "not found" );
            return null;
        }
    }
}
