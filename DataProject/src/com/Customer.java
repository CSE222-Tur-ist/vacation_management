package com;

import java.util.*;

public class Customer extends User {
    // private String name;
    // private String surname;
    //private String userName;  bu bilgi User interfaceinde var
    //private String password; bu bilgi User interfaceinde var
    //private String email; bu bilgi User interfaceinde var
    private String phoneNumber;


    // reservation -> STACK
    private Stack<Reservation> reservations = new Stack<>();

    private Stack<Ticket> tickets = new Stack<>();

    //
    // STACK.
    private Stack<Hotel> favoriteHotels = new Stack<>();

    // tarihi en yakın olan turu önce gösterecek şekilde => araya ekleme
    // yapılabilmesi için
    // bst olamaz çünkü aynı tarihli turları birbiri üzerine yazar.
    // heap olabilir belki???
    // PRIORITY QUEUE.
    private Stack<Tour> favoriteTours = new Stack<>();
    public static Scanner input = new Scanner(System.in);

    public Customer(String username,String name, String surName, String ID, String password, String role, String phoneNumber, String email) {
        super(username,name, surName, ID, password,role,email);
        this.phoneNumber = phoneNumber;
    }

    // veri tutma şekli değişebilir.
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
    //tur için
    private void listTours(){
        int i=1;
        for(Tour nextTour : tours){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }
    //tur için
    private  void sortbyRateT() {
        PriorityQueue<Tour> pqR = new PriorityQueue<Tour>(hotels.size(), new rateComparatorT());
        for(Tour nextTour : tours){
            pqR.add(nextTour);
        }
        int i=1;
        for(Tour nextTour : pqR){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }
    //tur için
    private  void sortbyAlphabeticT() {
        PriorityQueue<Tour> pqA = new PriorityQueue<Tour>(hotels.size(), new nameComparatorT());
        for(Tour nextTour : tours){
            pqA.add(nextTour);
        }
        int i=1;
        for(Tour nextTour : pqA){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }
    //tur için
    private  void sortbyPriceT(){
        PriorityQueue<Tour> pqP = new PriorityQueue<Tour>(hotels.size(), new priceComparatorT());
        for(Tour nextTour : tours){
            pqP.add(nextTour);
        }
        int i=1;
        for(Tour nextTour : pqP){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }

    public void buyTicketForTour() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Enter tour name for buy a Ticket : ");
        tourName = input.nextLine();

        for(Tour ticketTour : tours) {
            if (ticketTour.getClass().getName().equals(tourName)) {
                if(buyTicketForTour(ticketTour)) System.out.println("\nTicket Bought Succesfully.");
                else System.out.println("\nNo Tickets Left !");
            }
        }
    }

    // bilet almak için başka mekanizma eklenebebilir
    private boolean buyTicketForTour(Tour chosenTour) {
        if(chosenTour.numberofTickets!=0){
            return true;
        }
        else return false;
    }

    public boolean cancelTicket(Ticket tickets) {
        System.out.println("cancel Ticket");
        return true;
    }

    // ticketları görüntülemek için view function.
    private void viewTickets() {
    }

    // priority queue kullanıldı kendi oluşturduğum Comparator ile
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
    private void listHotels(){
        int i=1;
        for(Hotel nextHotel : hotels){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }
    private  void sortbyRate() {
        PriorityQueue<Hotel> pqR = new PriorityQueue<Hotel>(hotels.size(), new rateComparatorH());
        for(Hotel nextHotel : hotels){
            pqR.add(nextHotel);
        }
        int i=1;
        for(Hotel nextHotel : pqR){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }
    private  void sortbyAlphabetic() {
        PriorityQueue<Hotel> pqA = new PriorityQueue<Hotel>(hotels.size(), new nameComparatorH());
        for(Hotel nextHotel : hotels){
            pqA.add(nextHotel);
        }
        int i=1;
        for(Hotel nextHotel : pqA){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }
    private  void sortbyPrice(){
        PriorityQueue<Hotel> pqP = new PriorityQueue<Hotel>(hotels.size(), new priceComparatorH());
        for(Hotel nextHotel : hotels){
            pqP.add(nextHotel);
        }
        int i=1;
        for(Hotel nextHotel : pqP){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }


    public boolean makeReservationForHotel(Hotel chosenHotel) {
        return false;
        // check for availability
        // information entries
        // return true.
    }

    public void viewReservations() {
    }

    public boolean cancelReservation(Reservation reservation) {
        return false;
    }

    // public boolean register(){}

    public void printPerformanceReviews() {
    }

    public boolean addToMyFavoriteHotels(Hotel hotel) {
        return false;
    }

    public boolean addToMyFavoriteTours(Tour tour) {
        return false;
    }

    public void viewMyFavs() {
    }

    // buyTicket veya makeReservation fonksiyonları içinde çağrılacak.
    private boolean makePayment() {
        return true;
    }

    private void rateHotel() {
        // cancel edilmemiş rezervasyonlardan otel bilgilerini çekip
        // sıralayarak hangisini puanlayacağını seçsin.

        // hotel classının içindeki data fieldda.
    }

    public void leaveCommentToHotel() {
        // cancel edilmemiş rezervasyonlardan otel bilgilerini çekip
        // sıralayarak hangisine yorum bırakacağını seçsin.

        // hotel classının içindeki data fieldda.
    }

    private void rateTour() {
        // cancel edilmemiş ve geçmişte kalmış ticketlardan tur bilgilerini çekip
        // sıralayarak hangisini puanlayacağını seçsin.

        // tur classının içindeki data fieldda.
    }

    public void leaveCommentToTour() {
        // cancel edilmemiş ticketlardan tur bilgilerini çekip
        // sıralayarak hangisini yorumlayacağını seçsin.

        // hotel classının içindeki data fieldda.
    }

    public void report() {
    }

    // public void aboutUs(){}
    // public void viewFAQ(){}

}
