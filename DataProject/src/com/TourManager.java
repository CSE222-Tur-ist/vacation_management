package com;

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
        Tour newtour = new Tour();
        String route;
        int flag=0;
        System.out.println("\n----------------- Add tour ----------------");
        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.print("Name of Tour : ");
        newtour.name = input.nextLine();
        System.out.print("Route of Tour(istanbul-ankara-izmir) : ");
        route = input.nextLine();
        newtour.route.add(java.util.Arrays.toString(route.split("\\-")));
        System.out.print("Start Date of Tour : ");
        newtour.startDate = input.nextLine();
        System.out.print("End Date of Tour : ");
        newtour.endDate = input.nextLine();
        System.out.print("Number of Tickets for Tour : ");
        newtour.numberofTickets = input.nextInt();
        System.out.print("Price of Ticket : ");
        newtour.price = input.nextDouble();

        System.out.println("------------------------------------------\n");
        for(Tour nextTour : tours){
            if(nextTour.name.toUpperCase(Locale.ROOT).equals(newtour.name.toUpperCase(Locale.ROOT))) {
                flag = 1;
                System.err.print("This name already exists !");}

        }
        if(flag==0) {
            tours.add(newtour);
        }

    }


    public void updateTourInformation(){
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.println("\n----------------- Update tour Information ----------------");
        System.out.print("Name of Tour for Updating : ");
        tourName = input.nextLine();

        if(updateTourInformation(tourName)) System.out.println("\nUpdated Succesfully.");
        else System.out.println("\nUpdate is unsuccesfull.");
        System.out.println("-------------------------------------------------------\n");

    }
    private boolean updateTourInformation(String tourName){
        int select;
        String route;
        for(Tour updateTour : tours){
            if(updateTour.name.toUpperCase(Locale.ROOT).equals(tourName.toUpperCase(Locale.ROOT))){
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
        System.out.println("\n----------------- Delete tours ----------------");
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Tour : ");
        tourName = input.nextLine();

        if(deleteTour(tourName)) System.out.println("\nTour removed Succesfully.");
        else System.out.println("\nTour remove is unsuccesfull!\n");
        System.out.println("---------------------------------------------\n");
    }
    private boolean deleteTour(String tourName){
        for(Tour deleteTour : tours) {
            if(deleteTour.name.toUpperCase(Locale.ROOT).equals(tourName.toUpperCase(Locale.ROOT))){
                tours.remove(deleteTour);
                return true;
            }
        }
        return false;
    }


    private void viewAddedTours() {
        System.out.println("\n----------------- View added tours ----------------");
        System.out.println("List of added tours:");
        for (Tour nextTour : tours) {
           /* System.out.println("  -Name of Tour : " + nextTour.name);
            // System.out.println("Route of Tour : " + nextTour.route);
            System.out.println("  -Start Date of Tour : " + nextTour.startDate);
            System.out.println("  -End Date of Tour : " + nextTour.endDate);
            System.out.println("  -Number of Tickets for Tour : " + nextTour.numberofTickets);
            System.out.println("  -Price of Ticket : " + nextTour.price);
            for (String nextRoute : nextTour.route) {
                System.out.println("  -Route of tour: " + nextRoute);
            }
            // System.out.println("****");
            System.out.println();*/
            System.out.println(nextTour);
        }
        System.out.println("------------------------------------------------\n");
    }

    public void tourManagerMenu() {
        int select;

        do {
            System.out.println("\n==> Tour manager Menu <==");
            for(int i=0;i<25;i++) System.out.print("\u2500");
            //System.out.println("-------------------------\n");
            System.out.println("\n1\u27E9 Add Tour");
            System.out.println("2\u27E9 Update Tour Information");
            System.out.println("3\u27E9 Delete Tour");
            System.out.println("4\u27E9 View Added Tours");
            System.out.println("5\u27E9 Exit");

            System.out.print("\n\u22D9 Enter : ");
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
