package com;
import java.util.*;

public abstract class Utilities {
    public static Scanner input = new Scanner(System.in);

    static ArrayList<HotelManager> hotelManagers = new ArrayList<>();
    static ArrayList<TourManager> tourManagers = new ArrayList<>();

    static ArrayList<Tour> tours = new ArrayList<>(); // tours confirmed by admin
    static ArrayList<Hotel> hotels = new ArrayList<>(); // hotels confirmed by admin

    // for tours.
    public void searchTours() {
        int choose;

        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.println("------------------------");
        System.out.println("1. List the Tours\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: listTours();
            case 2: sortbyRateT();
            case 3: sortbyAlphabeticT();
            case 4: sortbyPriceT();
            default: System.out.println("Your choice is not correct !"); break;
        }
    }
    protected static void listTours(){
        int i=1;
        for(Tour nextTour : tours){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }
    private void sortbyRateT() {
        PriorityQueue<Tour> pqR = new PriorityQueue<Tour>(tours.size(), new Tour(Tour.compareType.RATE));
        for (Tour nextTour : tours) {
            pqR.add(nextTour);
        }
        int i = 1;
        for (Tour nextTour : pqR) {
            System.out.println(i + ".  " + nextTour);
            i++;
        }
    }

    private void sortbyAlphabeticT() {

        PriorityQueue<Tour> pqA = new PriorityQueue<Tour>(tours.size(), new Tour(Tour.compareType.NAME));
        for (Tour nextTour : tours) {
            pqA.add(nextTour);
        }
        int i = 1;
        for (Tour nextTour : pqA) {
            System.out.println(i + ".  " + nextTour);
            i++;
        }
    }

    private void sortbyPriceT() {
        PriorityQueue<Tour> pqP = new PriorityQueue<Tour>(tours.size(), new Tour(Tour.compareType.PRICE));
        for (Tour nextTour : tours) {
            pqP.add(nextTour);
        }
        int i = 1;
        for (Tour nextTour : pqP) {
            System.out.println(i + ".  " + nextTour);
            i++;
        }
    }

    // for hotels
    public void searchHotels(){
        int choose;

        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.println("------------------------");
        System.out.println("1. List the Hotels\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: listHotels();
            case 2: sortbyRate();
            case 3: sortbyAlphabetic();
            case 4: sortbyPrice();
            default: System.out.println("Your choice is not correct !"); break;
        }
    }
    protected static void listHotels(){
        int i=1;
        for(Hotel nextHotel : hotels){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }
    private void sortbyRate() {

        PriorityQueue<Hotel> pqR = new PriorityQueue<Hotel>(hotels.size(), new Hotel(Hotel.compareType.RATE));
        for (Hotel nextHotel : hotels) {
            pqR.add(nextHotel);
        }
        int i = 1;
        for (Hotel nextHotel : pqR) {
            System.out.println(i + ".  " + nextHotel);
            i++;
        }
    }

    public void sortbyAlphabetic() {
        PriorityQueue<Hotel> pqA = new PriorityQueue<Hotel>(hotels.size(), new Hotel(Hotel.compareType.NAME));
        for (Hotel nextHotel : hotels) {
            pqA.add(nextHotel);
        }
        int i = 1;
        for (Hotel nextHotel : pqA) {
            System.out.println(i + ".  " + nextHotel);
            i++;
        }
    }

    private void sortbyPrice() {

        PriorityQueue<Hotel> pqP = new PriorityQueue<Hotel>(hotels.size(), new Hotel(Hotel.compareType.PRICE));
        for (Hotel nextHotel : hotels) {
            pqP.add(nextHotel);
        }
        int i = 1;
        for (Hotel nextHotel : pqP) {
            System.out.println(i + ".  " + nextHotel);
            i++;
        }
    }



}