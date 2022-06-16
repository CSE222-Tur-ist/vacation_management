package com;

import java.util.*;

public class HotelManager extends User {

    public static Scanner input = new Scanner(System.in);

    public HotelManager(String username,String name, String surName, String password,User.userType role,String email) {
        super(username,name, surName, password,role,email);
    }

    public HotelManager() {

    }

    public boolean addHotel(){
        Hotel newhotel = new Hotel();
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Hotel : ");
        newhotel.name = input.nextLine();
        System.out.print("Location of Hotel : ");
        newhotel.location = input.nextLine();
        System.out.print("Features of Hotel (not necessary) : ");
        newhotel.features = input.nextLine();
        System.out.print("Number of Suitable Rooms : ");
        newhotel.numberofRooms = input.nextInt();
        System.out.print("Price for One Night and One Person : ");
        newhotel.price = input.nextDouble();
        return addHotel(newhotel);
    }

    public boolean addHotel(Hotel newhotel){
        if (hotels.size() == 0) {
            hotels.add(newhotel);
            addLocation(newhotel);
            return true;
        }

        else {
            for (int i=0; i<hotels.size(); ++i) {
                if (hotels.get(i).name.toUpperCase(Locale.ROOT).equals(newhotel.name.toUpperCase(Locale.ROOT))) {
                    System.out.println("This name already exists !");
                    return false;
                }
            }
        }
        hotels.add(newhotel);
        addLocation(newhotel);
        return true;
    }

    public void updateHotelInformation(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Hotel for Updating : ");
        hotelName = input.nextLine();

        if(updateHotelInformation(hotelName)) System.out.println("\nUpdated Succesfully.");
        else System.out.println("\nUpdate is unsuccesfull.");
    }
    private boolean updateHotelInformation(String hotelName){
        int select;
        for(Hotel updateHotel : hotels){
            if(updateHotel.name.toUpperCase(Locale.ROOT).equals(hotelName.toUpperCase(Locale.ROOT))){
                if(input.hasNextLine()) input.nextLine(); // clear buffer

                do {
                    System.out.println("      Update Menu      ");
                    System.out.println("-----------------------");
                    System.out.println("1. Features\n2. Price\n3. Number of Rooms\n4. Exit");
                    System.out.print("Enter : ");
                    select = input.nextInt();
                    switch (select) {
                        case 1:
                            System.out.print("Features of Hotel : ");
                            updateHotel.features = input.nextLine();
                            break;
                        case 2:
                            System.out.print("Price for One Night and One Person : ");
                            updateHotel.price = input.nextDouble();
                            break;
                        case 3:
                            System.out.print("Remaining Number of Rooms : ");
                            updateHotel.numberofRooms = input.nextInt();
                            break;
                        case 4:
                            return true;
                        default:
                            System.out.println("Invalid input!");
                            break;
                    }
                }while(select!=6);
            }
        }
        return false;
    }

    public void deleteHotel(){
        String hotelName;

        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Hotel to be Deleted: ");
        hotelName = input.nextLine();

        if(deleteHotel(hotelName)) System.out.println("\n\nHotel removed Succesfully.");
        else System.out.println("\n\nHotel remove is unsuccesfull.");
    }
    private boolean deleteHotel(String hotelName){
        for(Hotel deleteHotel : hotels) {
            if(deleteHotel.name.toUpperCase(Locale.ROOT).equals(hotelName.toUpperCase(Locale.ROOT))){
                removeLocation(deleteHotel);
                hotels.remove(deleteHotel);
                return true;
            }
        }
        return false;
    }

    private void viewAddedHotels() {
        //System.out.println("List of Added Hotels:");
        System.out.println("\n\n");
        for (Hotel nextHotel : hotels) {
            /*System.out.println("Name of Hotel : " + nextHotel.name);
            System.out.println("Location : " + nextHotel.location);
            System.out.println("Number of Rooms : " + nextHotel.numberofRooms);
            System.out.println("Features : " + nextHotel.features);
            System.out.println("Price : " + nextHotel.price);
            System.out.println("-----------------------------------------------------");*/
            System.out.println(nextHotel);
        }
    }

    protected void hotelManagerMenu(){
        
        int select;

        do {
            System.out.println("\n==> Hotel manager Menu <==");
            for(int i=0;i<26;i++) System.out.print("\u2500");
            //System.out.println("--------------------------\n");
            System.out.println("\n1\u27E9 Add Hotel");
            System.out.println("2\u27E9 Update Hotel Information");
            System.out.println("3\u27E9 Delete Hotel");
            System.out.println("4\u27E9 View Added Hotels");
            System.out.println("5\u27E9 Find Hotel by Location");
            System.out.println("6\u27E9 Exit");

            System.out.print("\n\u22D9 Enter : ");
            select = input.nextInt();
            switch (select) {
                case 1:
                    addHotel();
                    break;
                case 2:
                    updateHotelInformation();
                    break;
                case 3:
                    deleteHotel();
                    break;
                case 4:
                    viewAddedHotels();
                    break;
                case 5:
                    findHotelbyLocation();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Your choice is not correct !");
                    break;
            }
        } while (select != 6);
    }

    private void addLocation(Hotel newhotel) {
        Location newlocation = new Location(newhotel.location); // create a location object with newhotel's location
        Location l = locations.find(newlocation); // create location l to check if locationName exists in BST or not
        if (l != null) // if locationName exists in BST
            l.hotelsList.add(newhotel); // just add hotel name to hotelsList in Location object
        else {
            newlocation.hotelsList.add(newhotel); // otherwise create new node for location and then add the hotel to hotelsList
            locations.add(newlocation);
        }
    }

    private void removeLocation(Hotel deleteHotel) {
        Location deleteLocation = locations.find(new Location(deleteHotel.location));
        if (deleteLocation != null) {
            if (deleteLocation.hotelsList.size() == 1) // if there is only 1 hotel in the hotelsList remove the node
                locations.remove(deleteLocation);
            else
                deleteLocation.hotelsList.remove(deleteHotel);
        }
    }

    public void findHotelbyLocation() {
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nFIND HOTEL BY LOCATION\nLocation of the Hotel : ");
        String search = input.nextLine();
        Location findLocation = locations.find(new Location(search));
        System.out.println("\n");
        if (findLocation != null) {
            int i=1;
            for(Hotel nextHotel : findLocation.hotelsList){
                System.out.println(i + ".  " + nextHotel);
                i++;
            }
            return;
        }
        System.out.println("Location could not found.");
    }

}
