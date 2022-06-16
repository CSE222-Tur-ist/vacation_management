package com;

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
        print("Enter the personal informations of new Tour Manager:");
        String username = "";
        boolean isExist = false;

        do {
            if (input.hasNextLine())
                input.nextLine(); // clear buffer
            print("\n"
                    + "UserName : ");
            username = input.nextLine();
            for (TourManager tourManager : tourManagers) {
                if (tourManager.getUsername().equals(username)) {
                    isExist = true;
                    System.err.print("Username already given another tour manager.\n");
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
                print("\nThe tour manager " + username + " is removed from the system!");
                return true;
            }
        }
        print("User cannot found.");
        return false;
    }

    public boolean updateTourManager(TourManager tourManager) {
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
        print("Enter the personal informations of new hotel manager:");
        String username = "";
        boolean isExist = false;
        do {
            if (input.hasNextLine())
                input.nextLine(); // clear buffer
            print("Username : ");
            username = input.nextLine();
            for (HotelManager hm : hotelManagers) {
                if (hm.getUsername().equals(username)) {
                    isExist = true;
                    System.err.print("Username already given another hotel manager.\n");
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
                print("\nThe hotel manager " + username + " is removed from the system!\n");
                return true;
            }
        }
        print("User cannot found.");
        return false;
    }

    public boolean updateHotelManager(HotelManager hotelManager) {
        print("Current name: " + hotelManager.getName());
        System.out.println("New name : ");
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
                return manager;
            }
        }
        System.out.println("No hotel manager with this user name.\n");
        return manager;
    }

    public TourManager getTourManager(String username) {

        TourManager manager = new TourManager();
        for (TourManager tm : tourManagers) {
            if (tm.getUsername().equals(username)) {
                manager = tm;
                return manager;
            }
        }
        System.out.println("No tour manager with this user name.\n");
        return manager;
    }


    public void getAllHotelManagers() {

        System.out.println("List of added hotel managers:");
        for (HotelManager managers : hotelManagers) {
            System.out.println("  -Name&Surname of manager : " + managers.getName()+" "+managers.getSurName());
            System.out.println("  -Email of manager : " + managers.getEmail());
            System.out.println();
        }
    }

    public void getAllTourManagers() {

        System.out.println("List of added tour managers:");
        for (TourManager managers : tourManagers) {
            System.out.println("  -Name&Surname of manager : " + managers.getName()+" "+managers.getSurName());
            System.out.println("  -Email of manager : " + managers.getEmail());
            System.out.println();
        }

    }

    protected void adminMenu(){
        //maindendeki login methodu buraya yonlendiriyor
        int option;

        do{
            System.out.println("\n==> Welcome to the Admin Menu <==");
            for(int i=0;i<33;i++) System.out.print("\u2500");
            //System.out.println("---------------------------------\n");
            System.out.println("\n1\u27E9 Add Tour Manager to the system");
            System.out.println("2\u27E9 Remove Tour Manager from the system");
            System.out.println("3\u27E9 Update a Tour Manager in the system");
            System.out.println("4\u27E9 Add Hotel Manager to the system");
            System.out.println("5\u27E9 Remove Hotel Manager from the system");
            System.out.println("6\u27E9 Update a Hotel Manager in the system");
            System.out.println("7\u27E9 View added tour managers");
            System.out.println("8\u27E9 View added hotel managers");

            System.out.println("9\u27E9 Exit");
            System.out.print("\n\u22D9 Enter your choice: ");
            option= input.nextInt();

            switch (option){
                case 1: System.out.println("\n---------- Add Tour Manager to the system -----------------");
                    addTourManager();
                    System.out.println("-----------------------------------------------------------\n");
                    break;
                case 2:
                    if(input.hasNextLine()) input.nextLine();
                    System.out.println("\n---------- Remove Tour Manager from the system -----------------");
                    System.out.print("Username of tour manager: ");
                    String tManagerName = input.nextLine();
                    removeTourManager(tManagerName);
                    System.out.println("----------------------------------------------------------------\n");
                    break;
                case 3:
                    System.out.println("\n---------- Update a Tour Manager in the system -----------------");
                    if(input.hasNextLine()) input.nextLine();
                    System.out.print("Username of tour manager: ");
                    tManagerName = input.nextLine();
                    updateTourManager(getTourManager(tManagerName));
                    break;
                case 4:
                    System.out.println("\n---------- Add Hotel Manager to the system -----------------");
                    addHotelManager();
                    System.out.println("------------------------------------------------------------\n");
                    break;
                case 5:
                    if(input.hasNextLine()) input.nextLine();
                    System.out.println("\n---------- Remove Hotel Manager from the system -----------------");
                    System.out.print("Username of hotel manager: ");
                    tManagerName = input.nextLine();
                    removeHotelManager(tManagerName);
                    System.out.println("-----------------------------------------------------------------\n");
                    break;
                case 6:
                    System.out.println("\n---------- Update a Hotel Manager in the system -----------------");
                    if(input.hasNextLine()) input.nextLine();
                    System.out.print("Username of hotel manager: ");
                    tManagerName = input.nextLine();
                    updateHotelManager(getHotelManager(tManagerName));
                    break;
                case 7:
                    System.out.println("\n----------------- View added tour managers ----------------");
                    getAllTourManagers();
                    System.out.println("-------------------------------------------------------------------\n");
                    break;
                case 8:
                    System.out.println("\n----------------- View added hotel managers ----------------");
                    getAllHotelManagers();
                    System.out.println("-------------------------------------------------------------------\n");
                    break;

                case 9: break;

                default: System.out.println("Input is not valid! Try again.."); break;
            }
        }while (option != 9);
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
