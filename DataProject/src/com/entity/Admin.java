package com.entity;

import java.util.Scanner;

public class Admin extends User {

    public static Scanner input = new Scanner(System.in);
    HotelManager adminHotelManager = new HotelManager();
    TourManager adminTourManager = new TourManager();

    public Admin(String username, String name, String surName, String password, User.userType role,
                 String email) {
        super(username, name, surName, password, role, email);
        // TODO Auto-generated constructor stub
    }


    // manage tour managers
    public void addTourManager() {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        print("Enter the personal informations of new Tour Manager:");
        String username = "";
        boolean isExist = false;

        do {
            print("UserName : ");
            username = input.nextLine();
            for (TourManager tourManager : tourManagers) {
                if (tourManager.getUsername().equals(username)) {
                    isExist = true;
                    System.err.print("Username  already given another tour manager.");
                    break;
                }
            }
        } while (isExist);

        print("Name : ");
        String name = input.nextLine();
        print("SurName : ");
        String surName = input.nextLine();
        print("Password : ");
        String password = input.nextLine();
        print("Email : ");
        String email = input.nextLine();
        TourManager tourManager = new TourManager(username, name, surName, password, userType.TOUR_MANAGER, email);
        tourManagers.add(tourManager);
    }

    public void addTourManager(TourManager tourManager) {
        tourManagers.add(tourManager);
    }

    public boolean removeTourManager(String username) {
        for (TourManager tourManager : tourManagers) {
            if (tourManager.getUsername().equals(username)) {
                tourManagers.remove(tourManager);
                print("The tour manager " + username + "is removed from the system.");
                return true;
            }
        }
        print("User cannot found.");
        return false;
    }

    public boolean updateTourManager(TourManager tourManager) {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        print("Current name: " + tourManager.getName());
        print("New name : ");
        tourManager.setName(input.nextLine());
        print("Current surname: " + tourManager.getSurName());
        print("New surname : ");
        tourManager.setSurName(input.nextLine());
        print("Current password: " + tourManager.getPassword());
        print("New password : ");
        tourManager.setPassword(input.nextLine());
        print("Current e-mail: " + tourManager.getEmail());
        print("New e-mail : ");
        tourManager.setEmail(input.nextLine());
        print("Tour Manager " + tourManager.getUsername() + " is updated.");
        return true;
    }

    // manage hotel managers
    public void addHotelManager() {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        print("Enter the personal informations of new hotel manager:");
        String username = "";
        boolean isExist = false;
        do {
            print("Username : ");
            username = input.nextLine();
            for (HotelManager hm : hotelManagers) {
                if (hm.getUsername().equals(username)) {
                    isExist = true;
                    System.err.print("Username already given another hotel manager.");
                }
            }
        } while (isExist);
        print("Name : ");
        String name = input.nextLine();
        print("Surname : ");
        String surname = input.nextLine();
        print("Password : ");
        String password = input.nextLine();
        print("E-mail : ");
        String email = input.nextLine();
        HotelManager newHotelManager = new HotelManager(username, name, surname, password, userType.HOTEL_MANAGER,
                email);
        hotelManagers.add(newHotelManager);

    }

    public void addHotelManager(HotelManager hotelManager) {
        hotelManagers.add(hotelManager);
    }

    public boolean removeHotelManager(String username) {
        for (HotelManager hotelManager : hotelManagers) {
            if (hotelManager.getUsername().equals(username)) {
                hotelManagers.remove(hotelManager);
                print("The hotel manager " + username + "is removed from the system.");
                return true;
            }
        }
        print("User cannot found.");
        return false;
    }

    public boolean updateHotelManager(HotelManager hotelManager) {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        print("Current name: " + hotelManager.getName());
        print("New name : ");
        hotelManager.setName(input.nextLine());
        print("Current surname: " + hotelManager.getSurName());
        print("New surname : ");
        hotelManager.setSurName(input.nextLine());
        print("Current password: " + hotelManager.getPassword());
        print("New password : ");
        hotelManager.setPassword(input.nextLine());
        print("Current e-mail: " + hotelManager.getEmail());
        print("New e-mail : ");
        hotelManager.setEmail(input.nextLine());
        print("Hotel Manager " + hotelManager.getUsername() + " is updated.");
        return true;
    }

    public void addHotel() {
        adminHotelManager.addHotel();
    }

    public void deleteHotel() {
        adminHotelManager.deleteHotel();
    }

    public void updateHotelInformation() {
        adminHotelManager.updateHotelInformation();
    }

    public void addTour() {
        adminTourManager.addTour();
    }

    public void deleteTour() {
        adminTourManager.deleteTour();
    }

    public void updateTourInformation() {
        adminTourManager.updateTourInformation();
    }

    public HotelManager getHotelManager(String username) {

        HotelManager manager = new HotelManager();
        for (HotelManager hm : hotelManagers) {
            if (hm.getUsername().equals(username)) {
                manager = hm;
            }
        }
        System.err.println("No hotel manager with this user name.");
        return manager;
    }

    public TourManager getTourManager(String username) {

        TourManager manager = new TourManager();
        for (TourManager tm : tourManagers) {
            if (tm.getUsername().equals(username)) {
                manager = tm;
            }
        }
        System.err.println("No tour manager with this user name.");
        return manager;
    }


    public void getAllHotelManagers() {
        hotelManagers.forEach(System.out::println);
    }

    public void getAllTourManagers() {
        tourManagers.forEach(System.out::println);
    }


    // -----------------------------------------------------

    /**
     * USED FOR PRINTING IN EFFICIENT WAY
     */
    static void print(Object... o) {
        if (o.length == 0) {
            System.out.println();
        } else {
            for (Object object : o) {
                System.out.println(object);
            }
        }
    }

    static void printLine(Object... o) {
        if (o.length == 0) {
            System.out.println();
        } else {
            for (Object object : o) {
                print(object + " ");
            }
        }
        System.out.println();
    }

    // -----------------------------------------------------
}
