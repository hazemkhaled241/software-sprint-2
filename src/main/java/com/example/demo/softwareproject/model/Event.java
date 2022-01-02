package com.example.demo.softwareproject.model;

public class Event {
    String eventName;
    String currentTime;
    String driverName = "";
    double ridePrice = 0;
    String clientName = "";

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCurrentTime() {
        return currentTime;
    }

    public void setCurrentTime(String currentTime) {
        this.currentTime = currentTime;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Event(String eventName, String currentTime) {
        this.eventName = eventName;
        this.currentTime = currentTime;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public double getRidePrice() {
        return ridePrice;
    }

    public void setRidePrice(double ridePrice) {
        this.ridePrice = ridePrice;
    }

    @Override
    public String toString() {
        String output = "";
        output+= "eventName = '" + eventName + '\'' ;
        output+= ", currentTime = '" + currentTime + '\''  ;

        if(!driverName.isEmpty()){
            output+= ", driverName = '" + driverName + '\'';
        }
        if(ridePrice != 0){
            output+= ", ridePrice = " + ridePrice ;
        }
        if(!clientName.isEmpty()){
            output+= ", clientName = '" + clientName + '\'';
        }
        return output ;
    }
}
