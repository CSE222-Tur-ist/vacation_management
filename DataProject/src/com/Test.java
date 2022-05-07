package com;


public class Test {

    public static void main(String[] args) {
        UserTests.Test_Admin();


    }
}

// -------------------------------------------------

class UserTests {
    static Admin admin = new Admin("A1", "Admin", "test", "12345", User.userType.ADMIN, "email@email.com");
    static HotelManager HM1 = new HotelManager("HM1", "HM", "test", "12345", User.userType.HOTEL_MANAGER,
            "email@email.com");
    static TourManager TM1 = new TourManager("TM1", "TM", "test", "12345", User.userType.TOUR_MANAGER,
            "email@email.com");
    static Customer C1 = new Customer("C1", "Customer", "test", "12345", User.userType.CUSTOMER, "0555000000",
            "email@email.com");

    public static void Test_Admin() {
        // admin.addHotelManager(HM1);
        // admin.addTourManager(TM1);
        // admin.getAllHotelManagers();
        // admin.getAllTourManagers();

        // admin.addHotelManager();
        // admin.addTourManager();
        admin.addHotel();
        admin.addHotel();
        admin.addHotel();
        print(admin.hotels.size());
        admin.sortbyAlphabetic();


        // admin.addTour();
        // admin.updateHotelManager(admin.getHotelManager("HM1"));
        // admin.updateTourManager(admin.getTourManager("TM1"));
        // admin.updateHotelInformation();
        // admin.updateTourInformation();
    }

    // -------------------------------------------------

    public static void Test_HotelManager() {
    }

    // -------------------------------------------------

    public static void Test_TourManager() {
    }

    // -------------------------------------------------

    public static void Test_Customer() {
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
