package com;

import java.util.Scanner;

public class Admin extends User {

    HotelManager adminHotelManager = new HotelManager();
    TourManager adminTourManager = new TourManager();

    public static Scanner input = new Scanner(System.in);

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
                    System.err.println("Username  already given another tour manager.");
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

    public boolean removeTourManager(String username) {
        for (TourManager tourManager : tourManagers) {
            if (tourManager.getUsername().equals(username)) {
                tourManagers.remove(tourManager);
                System.out.println("The tour manager " + username + "is removed from the system.");
                return true;
            }
        }
        System.out.println("User cannot found.");
        return false;
    }

    public boolean updateTourManager(TourManager tourManager) {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        System.out.println("Current name: " + tourManager.getName());
        System.out.print("New name : ");
        tourManager.setName(input.nextLine());
        System.out.println("Current surname: " + tourManager.getSurName());
        System.out.print("New surname : ");
        tourManager.setSurName(input.nextLine());
        System.out.println("Current password: " + tourManager.getPassword());
        System.out.print("New password : ");
        tourManager.setPassword(input.nextLine());
        System.out.println("Current e-mail: " + tourManager.getEmail());
        System.out.print("New e-mail : ");
        tourManager.setEmail(input.nextLine());
        System.out.println("Tour Manager " + tourManager.getUsername() + " is updated.");
        return true;
    }

    // manage hotel managers
    public void addHotelManager() {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        System.out.println("Enter the personal informations of new hotel manager:");
        String username = "";
        boolean isExist = false;
        do {
            System.out.print("Username : ");
            username = input.nextLine();
            for (HotelManager hm : hotelManagers) {
                if (hm.getUsername().equals(username)) {
                    isExist = true;
                    System.err.println("Username already given another hotel manager.");
                }
            }
        } while (isExist);
        System.out.print("Name : ");
        String name = input.nextLine();
        System.out.print("Surname : ");
        String surname = input.nextLine();
        System.out.print("Password : ");
        String password = input.nextLine();
        System.out.print("E-mail : ");
        String email = input.nextLine();
        HotelManager newHotelManager = new HotelManager(username, name, surname, password, userType.HOTEL_MANAGER,
                email);
        hotelManagers.add(newHotelManager);

    }

    public boolean removeHotelManager(String username) {
        for (HotelManager hotelManager : hotelManagers) {
            if (hotelManager.getUsername().equals(username)) {
                hotelManagers.remove(hotelManager);
                System.out.println("The hotel manager " + username + "is removed from the system.");
                return true;
            }
        }
        System.out.println("User cannot found.");
        return false;
    }

    public boolean updateHotelManager(HotelManager hotelManager) {
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        System.out.println("Current name: " + hotelManager.getName());
        System.out.print("New name : ");
        hotelManager.setName(input.nextLine());
        System.out.println("Current surname: " + hotelManager.getSurName());
        System.out.print("New surname : ");
        hotelManager.setSurName(input.nextLine());
        System.out.println("Current password: " + hotelManager.getPassword());
        System.out.print("New password : ");
        hotelManager.setPassword(input.nextLine());
        System.out.println("Current e-mail: " + hotelManager.getEmail());
        System.out.print("New e-mail : ");
        hotelManager.setEmail(input.nextLine());
        System.out.println("Hotel Manager " + hotelManager.getUsername() + " is updated.");
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
