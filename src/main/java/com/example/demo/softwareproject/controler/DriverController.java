package com.example.demo.softwareproject.controler;



import com.example.demo.softwareproject.model.DataBaseSystem;
import com.example.demo.softwareproject.model.Driver;
import com.example.demo.softwareproject.model.Event;
import com.example.demo.softwareproject.model.Ride;
import com.example.demo.softwareproject.view.SignUp;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@RestController
public class DriverController {
    DataBaseSystem dataBaseSystem= DataBaseSystem.getInstance();
    SignUp signUp = new SignUp();

    @PostMapping("/addFavouriteArea/{driverFavouritePlace}")
    public boolean addFavouriteArea( @PathVariable String driverFavouritePlace, @RequestBody Driver driver) {
        driver.addFavouriteArea ( driverFavouritePlace );
        return true ;
    }
    @PutMapping("/showMatchedRides")
    public ArrayList<Ride> showMatchedRides(@RequestBody Driver driver) {
        return dataBaseSystem.showMatchedRides(driver);
    }
    @PutMapping("/showUserRate")
    public boolean showUserRate(@RequestBody Driver driver) {
        return dataBaseSystem.showUserRate(driver);
    }
    @PutMapping("/driverOffer/{driverOffer}")
    public boolean driverOffer(@RequestBody Ride ride,@RequestBody Driver driver, @PathVariable double driverOffer) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event driverOfferEvent = new Event("Driver offer price to ride",dtf.format(now));
        driverOfferEvent.setDriverName(driver.getUserName());
        driverOfferEvent.setRidePrice(driverOffer);

        ride.setPrice(driverOffer);
        dataBaseSystem.getRideEvents().add(driverOfferEvent);
        ride.setDriver(driver);
        driver.setCurrentRide(ride);
        return true;
    }
    @PutMapping("/DriverArrivedToUserDestinationEvent")
    public boolean DriverArrivedToUserDestinationEvent(@RequestBody Driver driver){
        // date and adding event
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Event DriverArrivedToUserDestinationEvent = new Event("driver arrived to user destination", dtf.format(now));
        DriverArrivedToUserDestinationEvent.setClientName(driver.getCurrentRide().getClient().getUserName());
       DriverArrivedToUserDestinationEvent.setDriverName(driver.getUserName());
        dataBaseSystem.getRideEvents().add(DriverArrivedToUserDestinationEvent);
        return true ;
    }
    @PostMapping("/driverSignUp/{userName}/{email}/{password}/{nationalId}/{driverLicense}/{numberOfSeats}")
    public boolean driverSignUp(@PathVariable String userName,@PathVariable String email ,@PathVariable String password
            ,@PathVariable String nationalId ,@PathVariable String driverLicense,@PathVariable Integer numberOfSeats){
        return signUp.driverSignUp(userName,email,password,nationalId,driverLicense,numberOfSeats);
    }
        }

