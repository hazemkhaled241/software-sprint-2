package com.example.demo.softwareproject.model;

import java.util.ArrayList;

public class Driver extends User {
    String nationalId;
    String license;
    Boolean isAvailable = true;
    ArrayList<String> favouriteArea = new ArrayList<>();
    ArrayList<Ride> requestedRides = new ArrayList<>();
    ArrayList<Integer> userRate = new ArrayList<>();
    Boolean driverStatus = false;
    Ride currentRide = null;
    Integer numberOfSeats = null;

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }


    public void setCurrentRide(Ride currentRide) {
        this.currentRide = currentRide;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
    }

    public void setFavouriteArea(ArrayList<String> favouriteArea) {
        this.favouriteArea = favouriteArea;
    }

    public void setRequestedRides(ArrayList<Ride> requestedRides) {
        this.requestedRides = requestedRides;
    }


    public Ride getCurrentRide() {
        return currentRide;
    }

    public ArrayList<Integer> getUserRate() {
        return userRate;
    }

    public void setUserRate(ArrayList<Integer> userRate) {
        this.userRate = userRate;
    }

    public Driver(String userName, String password) {
        super(userName, password);
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public boolean setIsAvailable(Boolean available) {
        return this.isAvailable = available;
    }

    public Boolean getDriverStatus() {
        return driverStatus;
    }

    public boolean setDriverStatus(Boolean driverStatus) {
        return this.driverStatus = driverStatus;
    }


    public boolean addFavouriteArea(String place) {
        return favouriteArea.add(place);
    }

    public ArrayList<String> getFavouriteArea() {
        return favouriteArea;
    }

    public ArrayList<Ride> getRequestedRides() {

        return requestedRides;
    }


}
