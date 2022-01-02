package com.example.demo.softwareproject.model;

import java.util.ArrayList;

public class DataBaseSystem  {

    ArrayList<Ride> rideRequests = new ArrayList<>();
    ArrayList<Client> clients = new ArrayList<>();
    ArrayList<Driver> activeDrivers = new ArrayList<>();
    ArrayList<Driver> pendingDrivers = new ArrayList<>();
    ArrayList<Admin> currAdmins = new ArrayList<>();
    ArrayList<Event> rideEvents = new ArrayList<>();
    ArrayList<String> discountAreas = new ArrayList<>();

    public ArrayList<String> getPublicHolidays ( ) {
        return publicHolidays;
    }

    public void setPublicHolidays ( ArrayList<String> publicHolidays ) {
        this.publicHolidays = publicHolidays;
    }

    ArrayList<String> publicHolidays = new ArrayList<>();

    public void addPublicHolidays() {
        publicHolidays.add ( new String( "01/01" ) );
        publicHolidays.add ( new String( "02/01" ) );
        publicHolidays.add ( new String( "04/01" ) );
    }

    public ArrayList<String> getDiscountAreas() {
        return discountAreas;
    }

    public void setDiscountAreas(ArrayList<String> discountAreas) {
        this.discountAreas = discountAreas;
    }

    public ArrayList<Event> getRideEvents() {
        return rideEvents;
    }

    public void setRideEvents(ArrayList<Event> rideEvents) {
        this.rideEvents = rideEvents;
    }

    public ArrayList<Ride> getRideRequests() {
        return rideRequests;
    }

    public void setRideRequests(ArrayList<Ride> rideRequests) {
        this.rideRequests = rideRequests;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public ArrayList<Driver> getActiveDrivers() {
        return activeDrivers;
    }

    public void setActiveDrivers(ArrayList<Driver> activeDrivers) {
        this.activeDrivers = activeDrivers;
    }

    public ArrayList<Driver> getPendingDrivers() {
        return pendingDrivers;
    }

    public void setPendingDrivers(ArrayList<Driver> pendingDrivers) {
        this.pendingDrivers = pendingDrivers;
    }

    public ArrayList<Admin> getCurrAdmins() {
        return currAdmins;
    }

    public void setCurrAdmins(ArrayList<Admin> currAdmins) {
        this.currAdmins = currAdmins;
    }

    public ArrayList<Ride> getFinishedRides() {
        return finishedRides;
    }

    public void setFinishedRides(ArrayList<Ride> finishedRides) {
        this.finishedRides = finishedRides;
    }

    public ArrayList<Ride> getActiveRides() {
        return activeRides;
    }

    public void setActiveRides(ArrayList<Ride> activeRides) {
        this.activeRides = activeRides;
    }

    ArrayList<Ride> finishedRides = new ArrayList<>();
    ArrayList<Ride> activeRides = new ArrayList<>();
    private static DataBaseSystem dataBaseSystem = null;


    private DataBaseSystem() {
    }

    public static DataBaseSystem getInstance() {
        if (dataBaseSystem == null)
            dataBaseSystem = new DataBaseSystem();


        return dataBaseSystem;
    }

    public void addClient(Client client) {
        dataBaseSystem.clients.add(client);

    }

    public void addDriver(Driver driver) {
        dataBaseSystem.pendingDrivers.add(driver);

    }

    public void acceptDriver(Driver driver) {
        driver.setDriverStatus(true);
        dataBaseSystem.activeDrivers.add(driver);
    }

    public Boolean searchForUser(User user) {
        if (user.getClass().equals(Client.class)) {
            for (int i = 0; i < clients.size(); i++) {
                if (clients.get(i).getUserName().equals(user.getUserName())
                        && clients.get(i).getPassword().equals(user.getPassword())
                ) {
                    return true;
                }
            }
        } else if (user.getClass().equals(Driver.class)) {
            for (int i = 0; i < activeDrivers.size(); i++) {
                if (activeDrivers.get(i).getUserName().equals(user.getUserName())
                        && activeDrivers.get(i).getPassword().equals(user.getPassword())
                ) {
                    return true;
                }
            }
        } else if (user.getClass().equals(Admin.class)) { //admin
            for (int i = 0; i < currAdmins.size(); i++) {
                if (currAdmins.get(i).getUserName().equals(user.getUserName())
                        && currAdmins.get(i).getPassword().equals(user.getPassword())
                ) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addAdmin() {
        dataBaseSystem.currAdmins.add(new Admin("hazem", "123456"));
        dataBaseSystem.currAdmins.add(new Admin("mohamed nasr", "123456"));
        dataBaseSystem.currAdmins.add(new Admin("seif", "123"));
        dataBaseSystem.currAdmins.add(new Admin("moataz", "123456"));
    }

    public boolean showPendingRequests() {
        if (pendingDrivers.isEmpty()) {
            System.out.println(" There are no pending requests ");
            return false;
        } else {
            for (int i = 0; i < pendingDrivers.size(); i++) {
                System.out.print(i + 1 + "-" + pendingDrivers.get(i).getUserName() + " ");
                System.out.print(pendingDrivers.get(i).getPassword() + " ");
                System.out.print(pendingDrivers.get(i).getEmail() + " ");
                System.out.print(pendingDrivers.get(i).getNationalId() + " ");
                System.out.println(pendingDrivers.get(i).getLicense() + " ");
                System.out.println("\n");
            }
            return true;
        }
    }

    public ArrayList<Client> showClientsList() {
        if (clients.isEmpty()) {
            System.out.println(" There are no clients yet ");
            return clients;
        } else {
            for (int i = 0; i < clients.size(); i++) {
                System.out.print(i + 1 + "-" + clients.get(i).getUserName() + " ");
                System.out.print(clients.get(i).getEmail() + " ");
                System.out.println(clients.get(i).getStatus() + "\n");
            }
            return clients;
        }
    }

    public ArrayList<Driver> showActiveDrivers() {
        if (activeDrivers.isEmpty()) {
            System.out.println(" There are no drivers yet ");
            return activeDrivers;
        } else {
            for (int i = 0; i < activeDrivers.size(); i++) {
                if (activeDrivers.get(i).getDriverStatus().equals(true)) {
                    System.out.print(i + 1 + "-" + activeDrivers.get(i).getUserName() + " ");
                    System.out.print(activeDrivers.get(i).getPassword() + " ");
                    System.out.print(activeDrivers.get(i).getEmail() + " ");
                    System.out.print(activeDrivers.get(i).getNationalId() + " ");
                    System.out.println(activeDrivers.get(i).getLicense() + " ");
                    System.out.println("\n");
                }
            }
            return activeDrivers;
        }
    }

    public boolean matchRidesWithDrivers() {
        if (activeDrivers.isEmpty()) {
            return false;
        }
        else {
            for (int i = 0; i < activeDrivers.size(); i++) {
                boolean driverSource;
                for (int j = 0; j < rideRequests.size(); j++) {
                    driverSource = (activeDrivers.get(i).getFavouriteArea().contains(rideRequests.get(j).getSource())
                    && activeDrivers.get(i).getNumberOfSeats() >= rideRequests.get(j).getNumberOfPassengers()) ;
                    if (driverSource) {
                        activeDrivers.get(i).getRequestedRides().add(rideRequests.get(j));

                    }
                }
            }
            return true;
        }
    }

    public ArrayList<Ride> showMatchedRides (Driver driver ) { // show matched rides to a specific driver
        for (int i = 0; i < driver.getRequestedRides().size(); i++) {
            System.out.println(i + 1 + "-" + driver.getRequestedRides().get(i).toString());
        }
        return driver.getRequestedRides();
    }

    public void addRideRequest(Ride ride, Client client) {
        ride.setClient(client);
        rideRequests.add(ride);
        //matchRidesWithDrivers();

    }

    public void showDriverFavouritePlaces(Driver driver) {
        if (driver.getFavouriteArea().isEmpty()) {
            System.out.println("There are no favourite areas yet");
        } else {
            for (int i = 0; i < driver.getFavouriteArea().size(); i++) {
                System.out.println(i + 1 + "-" + driver.getFavouriteArea().get(i));
            }
        }
    }

    public Client searchForClient(Client client) {
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getUserName().equals(client.getUserName())
                    && clients.get(i).getPassword().equals(client.getPassword())) {
                return clients.get(i);
            }
        }
        return new Client("-1", "-1");
    }

    public Driver searchForDriver(Driver driver) {
        for (int i = 0; i < activeDrivers.size(); i++) {
            if (activeDrivers.get(i).getUserName().equals(driver.getUserName())
                    && activeDrivers.get(i).getPassword().equals(driver.getPassword())) {
                return activeDrivers.get(i);
            }
        }
        return new Driver("-1", "-1");
    }

    public Ride searchForClientRide(Client client) {

        for (int i = 0; i < rideRequests.size(); i++) {
            if (rideRequests.get(i).getClient().getUserName().equals(client.getUserName())
                    && rideRequests.get(i).getClient().getPassword().equals(client.getPassword())) {
                return rideRequests.get(i);
            }
        }
        for (int i = 0; i < finishedRides.size(); i++) {
            if (finishedRides.get(i).getClient().getUserName().equals(client.getUserName())
                    && finishedRides.get(i).getClient().getPassword().equals(client.getPassword())) {
                return finishedRides.get(i);
            }
        }

        Ride emptyRide = new Ride("-1","-1");
        return emptyRide ;
    }

    public boolean showFinishedRides(Client client) {
        if (client.getFinishedRides().isEmpty()) {
            System.out.println(" You have no finished rides right now ");
            return false;
        } else {
            for (int i = 0; i < client.getFinishedRides().size(); i++) {
                System.out.println(client.getFinishedRides().get(i));
            }
            return true;
        }
    }

    public void addFinishedRide(Ride ride) {
        ride.getClient().getFinishedRides().add(ride);
    }

    public boolean showUserRate ( Driver driver ) {
        if (!driver.getUserRate().isEmpty()) {
            for (int i = 0; i < driver.getUserRate().size(); i++) {
                System.out.println(driver.getUserRate().get(i));
            }
        } else {
            System.out.println("there are no rate yet");
        }
        return false;
    }

    public boolean addDriverRate(int rate, Driver driver) {
        return driver.getUserRate().add(rate);
    }

    public double avgDriverRate(Driver driver) {
        int sum = 0;
        double avg = 0;

        for (int i = 0; i < driver.getUserRate().size(); i++) {
            sum += driver.getUserRate().get(i);
        }
        avg = sum / driver.getUserRate().size();
        return avg;
    }
    
}
