package com;

import com.datastructures.Edge;
import com.datastructures.ListGraph;

import java.util.*;

public class TourManager extends User {

    public static Scanner input = new Scanner(System.in);
    public TourManager(String username,String name, String surName, String password,User.userType role,String email) {
        super(username,name, surName, password,role,email);
    }

    public TourManager() {

    }

    //buradaki rota için farklı data stru. kullanıalbilir
    public void addTour(){ // send request to admin for add tour
        Tour newTour = new Tour();
        String route;
        int counter = 0;

        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.print("Name of Tour : ");
        newTour.name = input.nextLine();
        System.out.print("Route of Tour(Ex: Istanbul-Ankara-Izmir) : ");
        route = input.nextLine();

        newTour.route.add(java.util.Arrays.toString(route.split("\\-")));

        System.out.print("Start Date of Tour : ");
        newTour.startDate = input.nextLine();
        System.out.print("End Date of Tour : ");
        newTour.endDate = input.nextLine();
        System.out.print("Number of Tickets for Tour : ");
        newTour.numberofTickets = input.nextInt();
        System.out.print("Price of Ticket : ");
        newTour.price = input.nextDouble();

        for(Tour nextTour : tours){
            if(nextTour.name.equals(newTour.name)) System.out.print("This name already exists !");
            else tours.add(newTour);
        }
    }


    public void updateTourInformation(){
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Tour for Updating : ");
        tourName = input.nextLine();

        if(updateTourInformation(tourName)) System.out.println("\nUpdated Succesfully.");
        else System.out.println("\nUpdate is unsuccesfull.");

    }
    private boolean updateTourInformation(String tourName){
        int select , counter = 0;
        String route;
        for(Tour updateTour : tours){
            if(updateTour.name.equals(tourName)){
                if(input.hasNextLine()) input.nextLine(); // clear buffer


                do {
                    System.out.println("      Update Menu      ");
                    System.out.println("-----------------------");
                    System.out.println("1. Number of Tickets\n2. Start Date\n3. End Date\n4. Price\n6. Exit");
                    System.out.print("Enter : ");
                    select = input.nextInt();
                    switch (select) {
                        case 1:
                            System.out.print("Remaining Number of Tickets for Tour : ");
                            updateTour.numberofTickets = input.nextInt();
                            break;
                        case 2:
                            System.out.print("Start Date of Tour : ");
                            updateTour.startDate = input.nextLine();
                            break;
                        case 3:
                            System.out.print("End Date of Tour : ");
                            updateTour.endDate = input.nextLine();
                            break;
                        case 4:
                            System.out.print("Price for Ticket : ");
                            updateTour.price = input.nextDouble();
                            break;
                        case 5:
                            System.out.print("Route of Tour : ");
                            route = input.nextLine();
                            updateTour.route.clear();
                            updateTour.route.add(java.util.Arrays.toString(route.split("\\-")));

                            ListIterator<String> iter = updateTour.route.listIterator();

                            break;

                        case 6: return true;

                        default:
                            System.out.println("Your choice is not correct !");
                            break;
                    }

                }while(select!=6);

            }
        }
        return false;
    }


    public void deleteTour(){
        String tourName;

        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Tour : ");
        tourName = input.nextLine();

        if(deleteTour(tourName)) System.out.println("\nTour removed Succesfully.");
        else System.out.println("\nTour remove is unsuccesfull.");
    }
    private boolean deleteTour(String tourName){
        for(Tour deleteTour : tours) {
            if(deleteTour.name.equals(tourName)){
                tours.remove(deleteTour);
            return true;
            }
        }
        return false;
    }
  

    private void viewAddedTours() {
        System.out.println("List of added tours");
        for (Tour nextTour : tours) {
            System.out.println("Name of Tour : " + nextTour.name);
            // System.out.println("Route of Tour : " + nextTour.route);
            System.out.println("Start Date of Tour : " + nextTour.startDate);
            System.out.println("End Date of Tour : " + nextTour.endDate);
            System.out.println("Number of Tickets for Tour : " + nextTour.numberofTickets);
            System.out.println("Price of Ticket : " + nextTour.price);
            for (String nextRoute : nextTour.route) {
                System.out.println(nextRoute);
            }
            System.out.println("-----------------------------------------------------");
        }
    }

    public void tourManagerMenu() {
        int select;
        do {
            System.out.println("\n\n      Tour Manager Menu      ");
            System.out.println("-----------------------");
            System.out.println("1. Add Tour");
            System.out.println("2. Update Tour Information");
            System.out.println("3. Delete Tour");
            System.out.println("4. View Added Tours");
            System.out.println("5. Exit");

            System.out.print("Enter : ");
            select = input.nextInt();
            switch (select) {
                case 1:
                    addTour();
                    break;
                case 2:
                    updateTourInformation();
                    break;
                case 3:
                    deleteTour();
                    break;
                case 4:
                    viewAddedTours();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Your choice is not correct !");
                    break;
            }
        } while (select != 5);
    }
}
