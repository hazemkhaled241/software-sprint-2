package com.example.demo.softwareproject.view;


import com.example.demo.softwareproject.model.Client;
import com.example.demo.softwareproject.model.DataBaseSystem;
import com.example.demo.softwareproject.model.Driver;

public class SignUp {
    DataBaseSystem dataBaseSystem= DataBaseSystem.getInstance();
    public boolean clientSignUp(String userName, String email, String password , String birthDate ) {
        Client client1 = new Client ( userName ,password );
        client1.setEmail ( email );

        client1.setBirthDate ( birthDate );
        client1.setFirstTrip ( true );
        dataBaseSystem.addClient ( client1 );
        System.out.println ( "" );
        return true ;
    }
    public boolean driverSignUp(String userName, String email , String password
            , String nationalId , String driverLicense, Integer numberOfSeats ) {

        Driver driver1 = new Driver ( userName ,password);
        driver1.setNationalId(nationalId);
        driver1.setLicense(driverLicense);
        driver1.setNumberOfSeats(numberOfSeats);
        driver1.setEmail ( email );
        dataBaseSystem.addDriver ( driver1 ); // add to pending list
        return true ;
    }
}
