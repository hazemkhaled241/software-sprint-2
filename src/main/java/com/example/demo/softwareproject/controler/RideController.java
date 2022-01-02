package com.example.demo.softwareproject.controler;



import com.example.demo.softwareproject.model.DataBaseSystem;
import com.example.demo.softwareproject.model.Ride;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class RideController {
    DataBaseSystem dataBaseSystem = DataBaseSystem.getInstance();

    @PostMapping("/checkDestinationWithDiscountAreas")
    public boolean checkDestinationWithDiscountAreas(@RequestBody Ride ride){
       if (dataBaseSystem.getDiscountAreas().contains(ride.getDestination())){
           return true;
       }
       return false;
    }
    @PostMapping("/tenPercentageDiscount")
    public double tenPercentageDiscount(@RequestBody Ride ride){
        return ride.getPrice() * 0.9 ;
    }
    @PostMapping("/applyTenPercentageDiscountForFirstTrip/{price}")
    public double applyTenPercentageDiscountForFirstTrip(@PathVariable double price) {
        return   price * 0.9 ;
    }
    @PutMapping("/isClientFirstTrip")
    public boolean isClientFirstTrip(@RequestBody Ride ride) {
        if ( ride.getClient ().isFirstTrip () ) {
            return true;
        }
        else {
            return false;
        }
    }

    @PutMapping("/applyTenPercentageDiscountForClientBirthDay/{price}")
    public double applyTenPercentageDiscountForClientBirtDay(@PathVariable double price) {
        return price* 0.9 ;
    }

    @PutMapping("/isClientBirthDate")
    public boolean isClientBirthDate (@RequestBody Ride ride ) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("dd/MM");
        LocalDateTime now = LocalDateTime.now ( );
        String[]  FullDate = ride.getClient ().getBirthDate ().split("/");

        if (FullDate[0].length () == 1) {
            FullDate[0] = "0" + FullDate[0];
        }
        if (FullDate[1].length () == 1) {
            FullDate[1] = "0" + FullDate[1];
        }
        String date = FullDate[0] + "/" + FullDate[1];

        if (date.equals (dtf.format(now))) {
            return true;
        }
        return false;
    }

    @PutMapping("/applyFivePercentageDiscountForPublicHoliday/{price}")
    public double applyFivePercentageDiscountForPublicHoliday (@PathVariable double price )
    {
        return price* 0.95 ;
    }

    @GetMapping("/idPublicHoliday")
    public boolean idPublicHoliday() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern ("dd/MM");
        LocalDateTime now = LocalDateTime.now ( );

        for (int i = 0 ; i < dataBaseSystem.getPublicHolidays ().size () ; i++)
        {
            if (dataBaseSystem.getPublicHolidays ().get ( i ).equals (dtf.format(now)))
            {
                return true;
            }
        }
        return false;
    }

    @PutMapping("/applyFivePercentageDiscountForExistMoreThanOneUser/{price}")
    public double applyFivePercentageDiscountForExistMoreThanOneUser (@PathVariable double price ) {
        return price* 0.95 ;
    }

    @PutMapping("/checkExistOfMoreThanOneUser")
    public boolean checkExistOfMoreThanOneUser(@RequestBody Ride ride) {
       if (ride.getNumberOfPassengers() == 2 ) {
           return true;
       }
       return false ;
    }

}
