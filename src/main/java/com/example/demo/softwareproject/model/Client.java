package com.example.demo.softwareproject.model;

import java.util.ArrayList;

public class Client extends User {

    boolean firstTrip ;
    String birthDate = null;

    public boolean isFirstTrip ( ) {
        return firstTrip;
    }

    public void setFirstTrip ( boolean firstTrip ) {
        this.firstTrip = firstTrip;
    }



    public String getBirthDate ( ) {
        return birthDate;
    }

    public void setBirthDate ( String birthDate ) {
        this.birthDate = birthDate;
    }



    public ArrayList<Ride> getFinishedRides() {
        return finishedRides;
    }

    public boolean setFinishedRides(ArrayList<Ride> finishedRides) {
        this.finishedRides = finishedRides;
        return true;
    }

    public Boolean getClientStatus() {
        return clientStatus;
    }

    public boolean setClientStatus(Boolean clientStatus) {
        this.clientStatus = clientStatus;
        return true;
    }

    public int getCount() {
        return count;
    }

    ArrayList<Ride> finishedRides = new ArrayList<>();
    Boolean clientStatus=true;
    int count = 0 ;

    public boolean setCount(int count) {
        this.count = count;
        return true;
    }


    public Client(String userName, String password) {
        super(userName, password);
    }

    public Boolean getStatus() {
        return clientStatus;
    }

    public boolean setStatus ( Boolean status ) {
        return this.clientStatus = status;
    }


}