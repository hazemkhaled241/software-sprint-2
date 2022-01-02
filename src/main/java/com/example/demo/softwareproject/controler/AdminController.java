package com.example.demo.softwareproject.controler;


import com.example.demo.softwareproject.model.*;
import com.example.demo.softwareproject.view.SignIn;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class AdminController {

    DataBaseSystem dataBaseSystem = DataBaseSystem.getInstance();
    SignIn sign = new SignIn();

    @GetMapping("/showClients")
    public ArrayList<Client> showClientList() {
        return dataBaseSystem.showClientsList();
    }
    @GetMapping("/showActiveDrivers")
    public ArrayList<Driver> showActiveDrivers() {
        return dataBaseSystem.showActiveDrivers();
    }
    @PostMapping("/suspendClient/{index}")
    public boolean suspendClient(@PathVariable Integer index) {
        if(!dataBaseSystem.getClients().isEmpty()){
             dataBaseSystem.getClients().get(index - 1).setStatus(false);
            return true;
        }
        return false;
    }
    @PostMapping("/unSuspendClient/{index}")
    public boolean unSuspendClient(@PathVariable Integer index) {
        if(!dataBaseSystem.getClients().isEmpty()){
             dataBaseSystem.getClients().get(index - 1).setStatus(true);
            return true ;
        }
        return false ;
    }
    @PostMapping("/suspendDriver/{index}")
    public boolean suspendDriver(@PathVariable Integer index) {
        if(!dataBaseSystem.getActiveDrivers().isEmpty()){
             dataBaseSystem.getActiveDrivers().get(index - 1).setDriverStatus(false);
            return true ;
        }
        return false ;
    }
    @PostMapping("/unSuspendDriver/{index}")
    public boolean unSuspendDriver(@PathVariable Integer index) {
        if(!dataBaseSystem.getActiveDrivers().isEmpty()){
             dataBaseSystem.getActiveDrivers().get(index - 1).setDriverStatus(true);
            return true ;
        }
        return false ;
    }
    @PostMapping("/acceptDriverRequest/{index}")
    public boolean acceptDriverRequest(@PathVariable Integer index) {
        if(!dataBaseSystem.getPendingDrivers().isEmpty()){
            if (dataBaseSystem.getPendingDrivers().get(index - 1).setDriverStatus(true)) {
                dataBaseSystem.getActiveDrivers().add(dataBaseSystem.getPendingDrivers().get(index - 1));
                dataBaseSystem.getPendingDrivers().remove(index - 1);
                return true;
            }
        }
        return false;
    }
    @PostMapping("/rejectDriverRequest/{index}")
    public boolean rejectDriverRequest(@PathVariable Integer index) {
        if(!dataBaseSystem.getPendingDrivers().isEmpty()){
            dataBaseSystem.getPendingDrivers().remove(index - 1);
            return true;
        }
       return false ;
    }
    @GetMapping("/showRideEvents")
    public ArrayList<Event> showRideEvents() {
        if(dataBaseSystem.getRideEvents().isEmpty()){
            return dataBaseSystem.getRideEvents();
        }
            for (int i = 0; i < dataBaseSystem.getRideEvents().size(); i++) {
                System.out.println(++i + "- " + dataBaseSystem.getRideEvents().get(--i));
            }
       return dataBaseSystem.getRideEvents();
    }
    @PutMapping("/addToDiscountAreas/{areaName}")
    public boolean addToDiscountAreas(@PathVariable String areaName){
        dataBaseSystem.getDiscountAreas().add(areaName);
        return true;
    }
    @PutMapping("/addAdminsAndPublicHolidays")
    public boolean addAdminsAndPublicHolidays() { // used to add admins and public holidays for testing
        dataBaseSystem.addAdmin();
        dataBaseSystem.addPublicHolidays ();
        return true;
    }
    @GetMapping("/adminSignIn/{userName}/{password}")
    public Admin adminSignIN(@PathVariable String userName, @PathVariable String password){
        return sign.adminSignIN(userName, password);
    }

}