package com;
import java.util.*;

public class Main {

    // Keeps users
    static ArrayList<User> users = new ArrayList<>();
    static int userIndex = 0; //use in login method

    public static Scanner input = new Scanner(System.in);


    public static void main(String[] args) {
        int menu;
        // turları bst ile tutma.
        do{
            System.out.println("\n\n    TURISTICH    ");
            System.out.println("------------------");
            System.out.println("0. Exit"); // sona gelicek şuanlık 0 kalsın
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Enter : ");
            menu = input.nextInt();

            switch (menu){
                case 1: System.out.println("\n-----------------------"); register(); break;
                case 2: System.out.println("\n-----------------------"); login(); break;
                default: System.out.println("Your choice is not correct !"); break;
            }
        }while(menu!=0); // exit sayısı değişecek

        input.close();
    }


    private static boolean login() {
        String email_username,password;

        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.print("Email or Username(name+username) : ");
        email_username = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();

        User.userType loginAs = login(email_username,password);
        if(loginAs != null) {
            System.out.println("\nLogin as "+loginAs+" is succesfull.");

            // buraya kim olarak giriş yapıldıysa o classın menusunu çagır (her classın kendi menusu olmalı)
            switch (loginAs){
//                case ADMIN: ((Admin)users.get(userIndex))adminMenu(); break;
                case CUSTOMER: ((Customer)users.get(userIndex)).customerMenu(); break;
//                case HOTEL_MANAGER: ((HotelManager)users.get(userIndex))hotelManagerMenu(); break;
//                case TOUR_MANAGER: ((TourManager)users.get(userIndex))tourManagerMenu(); break;
            }

            return true;
        } // if username, email and password is paired correctly for a user on system.
        else {
            System.out.println("\nUsername,Email or Password is incorrect !");
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
        for(User loginUser : users){
            counter++;
            if(loginUser.getEmail().equals(email_username)){
                if(loginUser.getPassword().equals(password)){ // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }
            if(loginUser.getUsername().equals(email_username)){
                if(loginUser.getPassword().equals(password)){ // email and password paired correctly
                    userIndex = counter;
                    return loginUser.getRole(); // role of user to print menu of that role
                }
            }
        }
        return null;
    }

    public static boolean register() {
        int choosenRole;

        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.println("Register as Customer");
        //System.out.println("Choose role to register");
        System.out.println("-----------------------");
        //System.out.println("1. Customer\n2. Tour Manager\n3. Hotel Manager");
        //System.out.print("Enter : ");
        //choosenRole = input.nextInt();
        choosenRole = 1;
        switch (choosenRole){
            case 1: return registerCustomer();
            //case 2: return registerTourManager();
            //case 3: return registerHotelManager();
            default: System.out.println("Your choice is not correct !"); return false;
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
        System.out.print("ID : ");
        ID = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        System.out.print("Phone Number : ");
        phoneNumber = input.nextLine();
        System.out.print("email : ");
        email = input.nextLine();

        if(email_inUse(email)){
            System.out.println("\nThis email is already taken ! Registration is unsuccesfull");
            return false;
        }
        else{
            users.add(new Customer(username,name,surName,ID,password, User.userType.CUSTOMER,phoneNumber,email));
            System.out.println("\nRegistration is succesfull.");
            return true;
        }
    }

    // tour manager classı içinde gerekli olan bilgiler için variable oluştur. bu fonk.da diğer gerekli olan bilgileri iste
    private static boolean registerTourManager(){
        String name,surName,ID,password,email,username;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name : ");
        name = input.nextLine();
        System.out.print("Surname : ");
        surName = input.nextLine();
        username = name+surName; // username created automatically
        System.out.print("ID : ");
        ID = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        System.out.print("Email : ");
        email = input.nextLine();

        if(email_inUse(email)){
            System.out.println("\nThis email is already taken ! Registration is unsuccesfull");
            return false;
        }
        else{
            users.add(new TourManager(username,name,surName,ID,password, User.userType.TOUR_MANAGER,email));
            System.out.println("\nRegistration is succesfull.");
            return true;
        }
    }

// hotel manager classı içinde gerekli olan bilgiler için variable oluştur. bu fonk.da diğer gerekli olan bilgileri iste

    private static boolean registerHotelManager(){
        String name,surName,ID,password,email,username;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Name : ");
        name = input.nextLine();
        System.out.print("Surname : ");
        surName = input.nextLine();
        username = name+surName; // username created automatically
        System.out.print("ID : ");
        ID = input.nextLine();
        System.out.print("Password : ");
        password = input.nextLine();
        System.out.print("Email : ");
        email = input.nextLine();

        if(email_inUse(email)){
            System.out.println("\nThis email is already taken ! Registration is unsuccesfull");
            return false;
        }
        else{
            users.add(new HotelManager(username,name,surName,ID,password, User.userType.HOTEL_MANAGER,email));
            System.out.println("\nRegistration is succesfull.");
            return true;
        }
    }

    // if email is already in use, new account wont be created -> test caseslara eklenebilir
    private static boolean email_inUse(String email){
        for(User loginUser : users) {
            if (loginUser.getEmail().equals(email)) return true;
        }
        return false;
    }

}
