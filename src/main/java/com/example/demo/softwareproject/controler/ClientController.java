package com.example.demo.softwareproject.controler;



import com.example.demo.softwareproject.model.Client;
import com.example.demo.softwareproject.model.DataBaseSystem;
import com.example.demo.softwareproject.model.Event;
import com.example.demo.softwareproject.model.Ride;
import com.example.demo.softwareproject.view.SignUp;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ClientController {
    DataBaseSystem dataBaseSystem= DataBaseSystem.getInstance();
    SignUp singUp = new SignUp();

    @PostMapping("/pickRide/{sourceRide}/{destination}/{numberOfUsers}")
    public boolean PickRide(@PathVariable String sourceRide , @PathVariable String destination,
                            @PathVariable int numberOfUsers ,@RequestBody Client client) {
        Ride clientRide = new Ride ( sourceRide ,destination );
        clientRide.setNumberOfPassengers( numberOfUsers );
        dataBaseSystem.addRideRequest ( clientRide ,client );
        if ( dataBaseSystem.matchRidesWithDrivers ( ) ) {
            System.out.println ( " Looking for drivers " );
            return true;

        } else {
            System.out.println ( " There are no drivers available right now please try again later " );
            return false;
        }
            }
   @PutMapping("/searchForClientRide")
    public Ride searchForClientRide(@RequestBody Client client) {
        return dataBaseSystem.searchForClientRide ( client );
    }

    @PostMapping("/addDriverRate/{rateValue}")
    public boolean addDriverRate(@PathVariable int rateValue,@RequestBody Ride clientRide) {
        return dataBaseSystem.addDriverRate(rateValue, clientRide.getDriver());
    }
    @PutMapping("avgDriverRate")
    public Double avgDriverRate(@RequestBody Ride clientRide) {
        return(dataBaseSystem.avgDriverRate(clientRide.getDriver()));
    }
    @PutMapping("acceptOffer")
    public boolean acceptOffer(@RequestBody Ride clientRide) throws InterruptedException {
        clientRide.getClient().setCount(1);
        clientRide.getDriver().setIsAvailable(false); // assign driver to this ride
        clientRide.getDriver().getRequestedRides().remove(clientRide); // to delete the accepted ride from the driver list
        clientRide.getClient ().setFirstTrip ( false );
        System.out.println("Driver is coming to you..");

        // date and adding event
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event clientAcceptOfferEvent = new Event("client accepts driver offer", dtf.format(now));
        clientAcceptOfferEvent.setClientName(clientRide.getClient().getUserName());
        dataBaseSystem.getRideEvents().add(clientAcceptOfferEvent);

        Thread.sleep(5000);
        System.out.println("Driver arrived to your location");

        // date and adding event
        DateTimeFormatter dtf2 = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now2 = LocalDateTime.now();
        Event driverArrivedToUserLocationEvent = new Event("driver arrived to user location", dtf2.format(now2));
        driverArrivedToUserLocationEvent.setClientName(clientRide.getClient().getUserName());
        driverArrivedToUserLocationEvent.setDriverName(clientRide.getDriver().getUserName());
        dataBaseSystem.getRideEvents().add(driverArrivedToUserLocationEvent);


        clientRide.setFinished( true);
        dataBaseSystem.getFinishedRides().add(clientRide);
        dataBaseSystem.getRideRequests().remove(clientRide);
        return  true;
    }
    @PutMapping("/removeRideRequestFromDriverRides")
    public boolean removeRideRequestFromDriverRides(@RequestBody Ride clientRide) {
        return dataBaseSystem.getRideRequests().remove ( clientRide );

    }
    @PostMapping("clientSignUp/{userName}/{email}/{password}/{birthDate}")
    public boolean clientSignUp(@PathVariable String userName,@PathVariable String email
            ,@PathVariable String password ,@PathVariable String birthDate ){
        return singUp.clientSignUp(userName,  email, password, birthDate);
    }

}




