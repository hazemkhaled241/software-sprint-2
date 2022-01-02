package com.example.demo.softwareproject.model;

public class Ride {
    String source;
    String destination;
    Double price = 0.0;
    Driver driver = null;
    Client client = null;
    int numberOfPassengers = 0;

    public int getNumberOfPassengers( ) {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }



    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    Boolean isFinished = false;

    public Ride(String source, String destination) {
        this.source = source;
        this.destination = destination;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Driver getDriver() { return driver; }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ride{" +
                "source = '" + source + '\'' +
                ", destination = '" + destination + '\'' +
                ", NumberOfUsers = '" + numberOfPassengers + '\''+
                '}';
    }
}
