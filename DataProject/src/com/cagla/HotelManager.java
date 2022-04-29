package com;

import java.util.*;

public class HotelManager extends User {

    public static Scanner input = new Scanner(System.in);

    public HotelManager(String username,String name, String surName, String ID, String password,String role,String email) {
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
        System.out.print("Appropriate Start Date for Reservation : ");
        newhotel.startDate = input.nextLine();
        System.out.print("Appropriate End Date for Reservation : ");
        newhotel.endDate = input.nextLine();
        System.out.print("Price for One Night and One Person : ");
        newhotel.price = input.nextDouble();

        hotelrequest.add(newhotel); // new hotel request to admin
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
            if(updateHotel.getClass().getName().equals(hotelName)){
                if(input.hasNextLine()) input.nextLine(); // clear buffer

                do {
                    System.out.println("      Update Menu      ");
                    System.out.println("-----------------------");
                    System.out.println("1. Features\n2. Start Date\n3. End Date\n4. Price\n5. Number of Rooms\n6. Exit");
                    System.out.print("Enter : ");
                    select = input.nextInt();
                    switch (select) {
                        case 1:
                            System.out.print("Features of Hotel : ");
                            updateHotel.features = input.nextLine();
                            break;
                        case 2:
                            System.out.print("Appropriate Start Date for Reservation : ");
                            updateHotel.startDate = input.nextLine();
                            break;
                        case 3:
                            System.out.print("Appropriate End Date for Reservation : ");
                            updateHotel.endDate = input.nextLine();
                            break;
                        case 4:
                            System.out.print("Price for One Night and One Person : ");
                            updateHotel.price = input.nextDouble();
                            break;
                        case 5:
                            System.out.print("Remaining Number of Rooms : ");
                            updateHotel.numberofRooms = input.nextInt();
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

    public void deleteHotel(){
        String hotelName;

        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name of Hotel : ");
        hotelName = input.nextLine();

       if(deleteHotel(hotelName)) System.out.println("\nHotel removed Succesfully.");
       else System.out.println("\nHotel remove is unsuccesfull.");
    }
    private boolean deleteHotel(String hotelName){
        for(Hotel deleteHotel : hotels) {
            if(deleteHotel.getClass().getName().equals(hotelName)){
                hotels.remove(deleteHotel);
                return true;
            }
        }
        return false;
    }

}
