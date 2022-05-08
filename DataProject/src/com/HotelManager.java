package com;

import java.util.*;

public class HotelManager extends User {

    public static Scanner input = new Scanner(System.in);

    public HotelManager(String username,String name, String surName, String ID, String password,User.userType role,String email) {
        super(username,name, surName, ID, password,role,email);
    }

    public void addHotel(){ // send request to admin for add hotel
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

        for(Hotel nextHotel : hotels){
            if(nextHotel.name.equals(newhotel.name))
                System.out.print("This name already exists !");
            else {
                hotels.add(newhotel);
                addLocation(newhotel);
            }
        }
    }

    public void updateHotelInformation(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Hotel for Updating : ");
        hotelName = input.nextLine();

        if(updateHotelInformation(hotelName)) System.out.println("\nUpdated Successfully.");
        else System.out.println("\nUpdate is unsuccessful.");
    }
    private boolean updateHotelInformation(String hotelName){
        int select;
        for(Hotel updateHotel : hotels){
            if(updateHotel.name.equals(hotelName)){
                if(input.hasNextLine()) input.nextLine(); // clear buffer

                do {
                    System.out.println("      Update Menu      ");
                    System.out.println("-----------------------");
                    System.out.println("1. Name\n2. Features\n3. Price\n4. Number of Rooms\n5. Exit");
                    System.out.print("Enter : ");
                    select = input.nextInt();
                    switch (select) {
                        case 1:
                            if(input.hasNextLine()) input.nextLine(); // clear buffer
                            System.out.print("Name of Hotel to be Updated: ");
                            String newname = input.nextLine();
                            for(Hotel nextHotel : hotels){
                                if(nextHotel.name.equals(newname)) System.out.print("This name already exists !");
                            else updateHotel.name = newname;
                            break;
                        }
                        case 2:
                            if(input.hasNextLine()) input.nextLine(); // clear buffer
                            System.out.print("Features of Hotel : ");
                            updateHotel.features = input.nextLine();
                            break;
                        case 3:
                            System.out.print("Price for One Night and One Person : ");
                            updateHotel.price = input.nextDouble();
                            break;
                        case 4:
                            System.out.print("Remaining Number of Rooms : ");
                            updateHotel.numberofRooms = input.nextInt();
                            break;
                        case 5:
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

        System.out.print("Name of Hotel to be Deleted: ");
        hotelName = input.nextLine();

        if(deleteHotel(hotelName)) System.out.println("\nHotel removed Successfully.");
        else System.out.println("\nHotel remove is unsuccessful.");
    }
    private boolean deleteHotel(String hotelName){
        for(Hotel deleteHotel : hotels) {
            if(deleteHotel.name.equals(hotelName)){
                removeLocation(deleteHotel);
                hotels.remove(deleteHotel);
                return true;
            }
        }
        return false;
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
        if (deleteLocation.hotelsList.size() == 1) // if there is only 1 hotel in the hotelsList remove the node
            locations.remove(deleteLocation);
        else
            deleteLocation.hotelsList.remove(deleteHotel);
    }

    public void findHotelbyLocation() {
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("FIND HOTEL BY LOCATION\nLocation of the Hotel : ");
        String search = input.nextLine();
        Location findLocation = locations.find(new Location(search));
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
