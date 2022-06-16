package com;

import java.io.IOException;
import java.util.*;


public class Main extends Utilities{
    //abc
    // Keeps users
    static ArrayList<User> users = new ArrayList<>();

    static int userIndex = 0; //use in login method

    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) throws IOException {
        LoadCSV loadCSV = new LoadCSV();
        loadCSV.loadHotels();
        loadCSV.loadTours();
        loadCSV.loadHotelManagers();
        loadCSV.loadTourManagers();
        users.add(new Customer("user1", "user", "1", "abc", User.userType.CUSTOMER, "123123", "asd@asd.com"));
        users.add(new Admin("admin", "admin", "admin", "admin", User.userType.ADMIN,"admin@turistich.com"));
        int menu;
        try {
            do {
                System.out.print("\n\n\u250C");
                for(int i=0;i<17;i++)System.out.print("\u2500");
                System.out.println("\u2510");
                System.out.print("|    TURISTICH    |\n\u2514");
                for(int i=0;i<17;i++)System.out.print("\u2500");
                System.out.println("\u2518\n");
                System.out.println("0. Exit");
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.print("Enter : ");
                menu = input.nextInt();
                switch (menu) {
                    case 1:
                        System.out.println("\n-----------------------------------");
                        register();
                        break;
                    case 2:
                        System.out.println("\n-----------------------------------");
                        login();
                        break;
                    case 0:
                        System.out.println("\nExiting the system.. We are waiting for you again :)"); break;
                    default:
                        System.err.println("Your choice is not correct !");
                        break;
                }
            } while (menu != 0);
        }
        catch (Exception e){
        }
        input.close();
    }


    private static boolean login() {
        String email_username, password;

        if (input.hasNextLine())
            input.nextLine(); // clear buffer

        System.out.print("\u2826 Email or Username(name+surname) : ");

        email_username = input.nextLine();
        System.out.print("\u2826 Password : ");
        password = input.nextLine();

        User.userType loginAs = login(email_username, password);

        if (loginAs != null) {
            System.out.println("\nLogin as " + loginAs + " is succesfull.");

            // calls the menu of each user type
            switch (loginAs) {
                case ADMIN: ((Admin)users.get(userIndex)).adminMenu(); break;
                case CUSTOMER:
                    ((Customer) users.get(userIndex)).customerMenu();
                    break;
                case HOTEL_MANAGER: ((HotelManager) hotelManagers.get(0)).hotelManagerMenu();
                    break;
                case TOUR_MANAGER:
                    ((TourManager) tourManagers.get(0)).tourManagerMenu();
                    break;
                default:
                    System.err.println("Failed!");break;
            }

            return true;
        } // if username, email and password is paired correctly for a user on system.
        else {
            System.err.println("\nUsername,Email or Password is incorrect !");
            return false;
        }
    }

    /***
     * choose who you are, then input your information.
     * @param email_username
     * @param password
     * @return
     */
    private static User.userType login(String email_username, String password) {
        int counter = -1;

        for(User loginUser : users){ // checks the customers
            counter++;
            if(loginUser.getEmail().equals(email_username)){
                if(loginUser.getPassword().equals(password)){ // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }

            if (loginUser.getUsername().equals(email_username)) {
                if (loginUser.getPassword().equals(password)) { // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }
        }
        counter = -1;
        for(User loginUser : tourManagers){ // checks the tour managers
            counter++;
            if(loginUser.getEmail().equals(email_username)){
                if(loginUser.getPassword().equals(password)){ // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }

            if (loginUser.getUsername().equals(email_username)) {
                if (loginUser.getPassword().equals(password)) { // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }
        }
        counter = -1;
        for(User loginUser : hotelManagers){ // checks the hotel managers
            counter++;
            if(loginUser.getEmail().equals(email_username)){
                if(loginUser.getPassword().equals(password)){ // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }

            if (loginUser.getUsername().equals(email_username)) {
                if (loginUser.getPassword().equals(password)) { // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }
        }
        return null;
    }

    public static boolean register() {
        int choosenRole;

        //if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.println("Register as Customer");
        System.out.println("--------------------");
        choosenRole = 1;
        switch (choosenRole){
            case 1: return registerCustomer();
            default: System.err.println("Your choice is not correct !"); return false;
        }
    }

    private static boolean registerCustomer(){
        String name,surName,ID,password,phoneNumber,email,username;
        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.print("Name : ");
        name = input.nextLine();
        System.out.print("Surname : ");
        surName = input.nextLine();

        username = name+surName; // username created automatically

        System.out.print("Password : ");
        password = input.nextLine();
        System.out.print("Phone Number : ");
        phoneNumber = input.nextLine();
        System.out.print("email : ");
        email = input.nextLine();


        if(email_inUse(email)){
            System.err.println("\nThis email is already taken ! Registration is unsuccesfull");
            return false;
        }
        else{
            users.add(new Customer(username,name,surName,password, User.userType.CUSTOMER,phoneNumber,email));

            System.out.println("\nRegistration is succesfull.");
            return true;
        }
    }


    // if email is already in use, new account wont be created -> test caseslara
    // eklenebilir
    private static boolean email_inUse(String email) {
        for (User loginUser : users) {
            if (loginUser.getEmail().equals(email))
                return true;
        }
        return false;
    }
}

