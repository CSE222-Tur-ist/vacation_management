package com;

import java.util.*;

public class HotelManager extends User {

    public static Scanner input = new Scanner(System.in);

    public HotelManager(String username,String name, String surName, String password,User.userType role,String email) {
        super(username,name, surName, password,role,email);
    }

    public HotelManager() {

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
            if(nextHotel.name.equals(newhotel.name)) System.out.print("This name already exists !");
            else hotels.add(newhotel);
        }
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
            if(updateHotel.name.equals(hotelName)){
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
        System.out.print("Name of Hotel : ");
        hotelName = input.nextLine();

        if(deleteHotel(hotelName)) System.out.println("\nHotel removed Succesfully.");
        else System.out.println("\nHotel remove is unsuccesfull.");
    }
    private boolean deleteHotel(String hotelName){
        for(Hotel deleteHotel : hotels) {
            if(deleteHotel.name.equals(hotelName)){
                hotels.remove(deleteHotel);
                return true;
            }
        }
        return false;
    }

    protected void hotelManagerMenu(){
        
        int option;
        System.out.println("------------------");
        System.out.println("Welcome to the Hotel Manager Menu..");

        do{
            System.out.println("1-> Add a Hotel");
            System.out.println("2-> Update Hotel Information");
            System.out.println("3-> Delete a Hotel");

            System.out.println("4-> Exit");
            System.out.print("Enter your choice: ");
            option= input.nextInt();

            switch (option){
                case 1: System.out.println("\n-----------------------"); addHotel(); break;
                case 2: System.out.println("\n-----------------------"); updateHotelInformation(); break;
                case 3: System.out.println("\n-----------------------"); deleteHotel(); break;
                case 4: System.out.println("\nExiting.."); break;

                default: System.out.println("Input is not valid! Try again.."); break;
            }
        }while (option != 4);
        
        //ssss
        input.close();
    }

}
