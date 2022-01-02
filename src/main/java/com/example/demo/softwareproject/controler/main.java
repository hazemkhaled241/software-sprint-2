package com.example.demo.softwareproject.controler;


import com.example.demo.softwareproject.model.*;
import com.example.demo.softwareproject.view.SignIn;
import com.example.demo.softwareproject.view.SignUp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.ParseException;
import java.util.Scanner;

@SpringBootApplication
public class main {


    public static void main(String[] args) throws InterruptedException, ParseException {
        SpringApplication.run(main.class, args);

        DataBaseSystem dataBaseSystem = DataBaseSystem.getInstance();

        AdminController adminController = new AdminController();
        DriverController driverController = new DriverController();
        ClientController clientController = new ClientController();
        RideController rideController = new RideController();
        SignUp signUpOption = new SignUp();
        SignIn signInOption = new SignIn();

        adminController.addAdminsAndPublicHolidays ();

        Scanner scan = new Scanner(System.in);
        while (true) {

            System.out.println("Choose \n 1-SingUp \n 2-SingIn");
            scan = new Scanner(System.in);
            String userChoice = scan.nextLine();
            if (userChoice.equals("1")) { // SignUp
                System.out.println("Enter as \n 1-Client \n 2-Driver ");
                String userChoice2 = scan.nextLine();
                if (userChoice2.equals("1")) {//client
                    System.out.println("Enter userName, email(optional), password \n" +
                                       "and your birth date in that format (dd/MM/yyyy): respectively");
                    String userName = scan.nextLine();
                    String email = scan.nextLine();
                    String password = scan.nextLine();
                    String birthDate = scan.nextLine ();
                    clientController.clientSignUp(userName, email, password , birthDate);

                    continue;
                    // enter the system
                } else if (userChoice2.equals("2")) { // Driver
                    System.out.println("Enter userName, email(optional), password, nationalId, driverLicense, number of seats respectively");
                    String userName = scan.nextLine();
                    String email = scan.nextLine();
                    String password = scan.nextLine();
                    String nationalId = scan.nextLine();
                    String driverLicense = scan.nextLine();
                    Integer numberOfSeats = scan.nextInt();
                    driverController.driverSignUp(userName, email, password, nationalId, driverLicense, numberOfSeats);
                    System.out.println("Registration request send successfully, please wait until reviewing your request");
                    continue;
                } else { // enter valid number
                    System.out.println("inValid Number");
                }
            }

            //                         ----SignIn----                          //

            else if (userChoice.equals("2")) {
                System.out.println("Enter as \n 1-Client \n 2-Driver \n 3-Admin");
                String userChoice2 = scan.nextLine();

                //                         AS client

                if (userChoice2.equals("1")) {

                    // enter to system as client   (client list)
                    System.out.println("Enter userName, password respectively");
                    String userName = scan.nextLine();
                    String password = scan.nextLine();
                    Client client = signInOption.clientSignIN(userName, password);
                    if (client != null) {
                        System.out.println(" Welcome " + client.getUserName());

                        while (true) {
                            System.out.println(" 1-Pick a ride ");
                            System.out.println(" 2-Notification");
                            System.out.println(" 3-Logout");
                            int clientChoice1 = scan.nextInt();

                            // when user press 1 to pick a ride we first make sure that
                            // there are no other active rides for that user
                            if (clientChoice1 == 1) {

                                if (client.getCount() == 0) {
                                    System.out.println("Enter source and destination , number of users of the ride respectively");
                                    scan = new Scanner(System.in);
                                    String sourceRide = scan.nextLine();
                                    String destination = scan.nextLine();
                                    int numberOfUsers = scan.nextInt ( );

                                    clientController.PickRide(sourceRide, destination,numberOfUsers ,client );


                                    continue;
                                } else {
                                    scan = new Scanner(System.in);
                                    System.out.println("There is another active ride please end that ride first ");
                                    System.out.println("1-Back");
                                    int clientChoose = scan.nextInt();

                                    if (clientChoose == 1) {
                                        continue;
                                    }
                                }

                            } else if (clientChoice1 == 2) {
                                scan = new Scanner(System.in);
                                Ride clientRide = clientController.searchForClientRide(client);
                                if (clientRide.getSource().equals("-1")) {
                                    System.out.println("No Notifications");
                                    continue;
                                }

                                if (clientRide.getDriver().getIsAvailable() && clientRide.getFinished()) {

                                    System.out.println("You have been arrived successfully");
                                    while (true) {
                                        System.out.println("1-Rate Driver");
                                        System.out.println("2-Show driver average rate");
                                        System.out.println("3-back to main menu");
                                        scan = new Scanner(System.in);
                                        String rateOption = scan.nextLine();
                                        if (rateOption.equals("1")) {

                                            System.out.println("Enter number from 1 to 5 ");
                                            int rateValue = scan.nextInt();
                                            if (rateValue > 0 && rateValue <= 5) {
                                                clientController.addDriverRate(rateValue, clientRide);

                                                continue;
                                            } else {
                                                System.out.println("Enter valid number");
                                                continue;
                                            }
                                        } else if (rateOption.equals("2")) {
                                            System.out.println(clientController.avgDriverRate(clientRide));
                                            continue;
                                        } else if (rateOption.equals("3")) {
                                            clientRide.getClient().setCount(0);
                                            dataBaseSystem.getFinishedRides().remove(clientRide);
                                            break;
                                        } else {
                                            System.out.println("Enter valid number");
                                        }
                                    }
                                } else {

                                    double priceToApplyDiscountsOnIt = clientRide.getPrice ();
                                    System.out.println ( "The driver offer you " + clientRide.getPrice() + " LE for your ride\n");

                                   if (rideController.checkDestinationWithDiscountAreas(clientRide)){
                                       double priceAfterDiscount = rideController.tenPercentageDiscount(clientRide)  ;
                                       priceToApplyDiscountsOnIt = priceAfterDiscount ;
                                       System.out.println( "after discount because of your destination is special  = "+ priceToApplyDiscountsOnIt);
                                   }

                                   if (rideController.isClientFirstTrip ( clientRide ))
                                   {
                                       double priceAfterDiscount = rideController.applyTenPercentageDiscountForFirstTrip ( priceToApplyDiscountsOnIt );
                                       priceToApplyDiscountsOnIt = priceAfterDiscount ;
                                       System.out.println( "after discount because it's your first ride : "+ priceToApplyDiscountsOnIt);
                                   }

                                   if (rideController.isClientBirthDate ( clientRide ))
                                   {
                                       double priceAfterDiscount = rideController.applyTenPercentageDiscountForClientBirtDay ( priceToApplyDiscountsOnIt );
                                       priceToApplyDiscountsOnIt = priceAfterDiscount ;
                                       System.out.println( "after discount because it's your birth date : "+ priceToApplyDiscountsOnIt);
                                   }

                                    if (rideController.idPublicHoliday ())
                                    {
                                        double priceAfterDiscount = rideController.applyFivePercentageDiscountForPublicHoliday ( priceToApplyDiscountsOnIt );
                                        priceToApplyDiscountsOnIt = priceAfterDiscount ;
                                        System.out.println( "after discount because it's a public holiday : "+ priceToApplyDiscountsOnIt);
                                    }

                                    if (rideController.checkExistOfMoreThanOneUser ( clientRide ))
                                    {
                                        double priceAfterDiscount = rideController.applyFivePercentageDiscountForExistMoreThanOneUser ( priceToApplyDiscountsOnIt );
                                        priceToApplyDiscountsOnIt = priceAfterDiscount ;
                                        System.out.println( "after discount because you share the ride with another user : "+ priceToApplyDiscountsOnIt);
                                    }

                                    System.out.println("press 1 to accept, press any else key to reject");
                                    String choose = scan.nextLine();

                                    // client accept the driver's offer

                                    if (choose.equals("1")) {
                                        clientController.acceptOffer(clientRide);


                                        continue;
                                        /////////////////////////////////////////////////////////////////////
//                                        scan = new Scanner(System.in);
//                                        String rateChoice = scan.nextLine();
                                        ////////////////////////////////////////////////////////////////////////
                                        // we will add another option to client to be able to list all of finished rides so he can rate them or delete them
                                        // average rate for driver
                                        // create arraylist in driver to be able to show all users rates
                                        // handle menu
                                        // in admin menu add reject driver request feature
                                        // sequence diagram
                                        // trello pic
                                        // github link
                                        // sprint 1 doc
                                    } else {
                                        clientController.removeRideRequestFromDriverRides(clientRide);
                                        continue;
                                    }
                                }


                            } else if (clientChoice1 == 3) {
                                break;

                                // client is waiting for the requested ride being accepted
                                // by a driver that will set offer for this ride

                            } else {
                                System.out.println("not found");
                                break;
                            }

                        }
                    } else {
                        System.out.println("user not found");
                    }
                } else if (userChoice2.equals("2")) { // Driver
                    System.out.println("Enter userName, password respectively");
                    String userName = scan.nextLine();
                    String password = scan.nextLine();
                    Driver driver = signInOption.driverSignIN(userName, password);
                    if (driver != null) {
                        {
                            System.out.println("Welcome driver " + userName); // enter to system as driver

                            while (true) {
                                System.out.println("1-show favourite places");
                                System.out.println("2-add favourite place");
                                System.out.println("3-show requested rides that matches your favourite place");
                                System.out.println("4-show clients rate");
                                System.out.println("5-logout");
                                int driverChoice1 = scan.nextInt();


                                if (driverChoice1 == 1) { // show favourite places
                                    dataBaseSystem.showDriverFavouritePlaces(driver);
                                    System.out.println("1- back to main menu");
                                    int driverChoice2 = scan.nextInt();
                                    if (driverChoice2 == 1) {
                                        continue;
                                    }
                                } else if (driverChoice1 == 2) {
                                    while (true) {
                                        scan = new Scanner(System.in);
                                        System.out.println("Enter your favourite area");
                                        String driverFavouritePlace = scan.nextLine();

                                        driverController.addFavouriteArea(driverFavouritePlace,driver);
                                        System.out.println("Added successfully");

                                        System.out.println("1- Back to main menu");
                                        System.out.println("2- Add another favorite area");
                                        int driverChoice2 = scan.nextInt();
                                        if (driverChoice2 == 1) {
                                            break;
                                        } else if (driverChoice2 == 2) {
                                            continue;
                                        }
                                    }
                                } else if (driverChoice1 == 3) {
                                    //dataBaseSystem.matchRidesWithDrivers();
                                    if (driver.getIsAvailable() == false) {
                                        scan = new Scanner(System.in);
                                        System.out.println("offer Accepted ");
                                        System.out.println("Ride begin...");
                                        System.out.println("To end Ride Enter 1");
                                        String rideStatus = scan.nextLine();
                                        if (rideStatus.equals("1")) {
                                            driver.setIsAvailable(true);
                                            driverController.DriverArrivedToUserDestinationEvent(driver);

                                            System.out.println("ride ended successfully");
                                        }
                                    } else {
                                        if (!driver.getRequestedRides().isEmpty()) {
                                            driverController.showMatchedRides(driver);
                                            System.out.println("choose ride");
                                            int rideNumber = scan.nextInt();
                                            Ride ride = driver.getRequestedRides().get(rideNumber - 1);
                                            System.out.println("1-Set offer to ride");
                                            System.out.println("2-Reject Ride");
                                            System.out.println("3-Back to view.main menu");
                                            int driverChoice2 = scan.nextInt();
                                            if (driverChoice2 == 1) {
                                                System.out.println("Enter ride offer:");
                                                double driverOffer = scan.nextDouble();
                                                driverController.driverOffer(ride, driver, driverOffer);
                                                System.out.println("offer set and send to user successfully");


                                            } else if (driverChoice2 == 2) {
                                                driver.getRequestedRides().remove(ride);
                                                System.out.println("Ride Rejected");
                                            } else if (driverChoice2 == 3) {

                                            }
                                        } else {
                                            System.out.println("There are not any requested rides");
                                            continue;
                                        }
                                    }
                                } else if (driverChoice1 == 4) {
                                    driverController.showUserRate(driver);
                                } else if (driverChoice1 == 5) {

                                    break;
                                }
                            }
                        }
                    } else {
                        System.out.println("Driver not found");
                    }

                } else if (userChoice2.equals("3")) { //admin
                    Admin admin;
                    System.out.println(" Enter userName , password respectively");
                    String userName = scan.nextLine();
                    String password = scan.nextLine();

                    admin = adminController.adminSignIN(userName, password);

                    if (admin != null) {
                        System.out.println("welcome"); // enter to system as driver

                        while (true) {
                            System.out.println("1-show list of driver pending requests");
                            System.out.println("2-Suspend / unSuspend client");
                            System.out.println("3-Suspend / unSuspend driver");
                            System.out.println("4-show List of Ride Events");
                            System.out.println("5-Add discount Areas");
                            System.out.println("6-LogOut");

                            String userChoice3 = scan.nextLine();
                            if (userChoice3.equals("1")) {
                                scan = new Scanner(System.in);
                                if (dataBaseSystem.showPendingRequests()) {
                                    System.out.println("choose driver number to accept or reject his request");
                                    Integer userChoice4 = scan.nextInt();
                                    System.out.println("1- Accept request ");
                                    System.out.println("2- Reject request ");

                                    scan = new Scanner(System.in);
                                    String adminDecision = scan.nextLine();


                                    // admin choose to accept driver's request.

                                    if (adminDecision.equals("1")) {
                                        adminController.acceptDriverRequest(userChoice4);
                                        continue;
                                    }

                                    // admin choose to reject driver's request

                                    else if (adminDecision.equals("2")) {
                                        adminController.rejectDriverRequest(userChoice4);
                                        continue;
                                    }

                                } else {
                                    continue;
                                }
                            } else if (userChoice3.equals("2")) {
                                if (!adminController.showClientList().isEmpty()) {
                                    System.out.println(" Choose a client to be 1-Suspended / 2-unSuspended");
                                    int adminChoice = scan.nextInt();

                                    if (adminChoice == 1) {
                                        System.out.println(" Enter client number ");
                                        int clientID = scan.nextInt();
                                        adminController.suspendClient(clientID);
                                        continue;
                                    } else if (adminChoice == 2) {
                                        System.out.println(" Enter client number ");
                                        int clientID = scan.nextInt();
                                        adminController.unSuspendClient(clientID);
                                        continue;
                                    }
                                } else {
                                    continue;
                                }
                            } else if (userChoice3.equals("3")) {
                                if (!adminController.showActiveDrivers().isEmpty()) {
                                    System.out.println(" Choose a client to be 1-Suspended / 2-unSuspended");
                                    int adminChoice = scan.nextInt();

                                    if (adminChoice == 1) {
                                        System.out.println(" Enter Driver number ");
                                        int driverID = scan.nextInt();
                                        adminController.suspendDriver(driverID);
                                        continue;
                                    } else if (adminChoice == 2) {
                                        System.out.println(" Enter client number ");
                                        int driverID = scan.nextInt();
                                        adminController.unSuspendDriver(driverID);
                                        continue;
                                    }
                                }
                            }
                            else if (userChoice3.equals("4")) {
                               if(adminController.showRideEvents().isEmpty()){
                                   System.out.println("There are no Ride Events for now !!");
                               }
                            }
                            else if (userChoice3.equals("5")) {
                                scan = new Scanner(System.in);
                                System.out.println("Enter your Discount Area Name");
                                String discountAreaName = scan.nextLine();
                               if(adminController.addToDiscountAreas(discountAreaName)){
                                   System.out.println("Area Added Successfully");
                               }
                               else{
                                   System.out.println("Error occurred");
                               }

                            }
                            else if(userChoice3.equals("6")){
                                break;
                            }
                            else { // enter valid number
                                System.out.println("Enter a valid number ");
                            }
                        }
                    }
                    else {
                        System.out.println("not found");
                    }
                }
            }
        }
    }
}



