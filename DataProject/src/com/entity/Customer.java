package com.entity;

import java.util.Scanner;
import java.util.Stack;

public class Customer extends User {
    // private String name;
    // private String surname;
    // private String userName; bu bilgi User abstract classında var
    // private String password; bu bilgi User interfaceinde var
    // private String email; bu bilgi User interfaceinde var

    public static Scanner input = new Scanner(System.in);
    private String phoneNumber;
    private Stack<Reservation> reservations = new Stack<>();
    private Stack<Ticket> tickets = new Stack<>();
    private Stack<Hotel> favoriteHotels = new Stack<>();
    private Stack<Tour> favoriteTours = new Stack<>();

    public Customer(String username, String name, String surName, String password, User.userType role,
                    String phoneNumber, String email) {
        super(username, name, surName, password, role, email);
        this.phoneNumber = phoneNumber;
    }

    public void buyTicketForTour() {
        String tourName;
        if (input.hasNextLine())
            input.nextLine(); // clear buffer
        System.out.print("Enter tour name for buy a Ticket : ");
        tourName = input.nextLine();

        for (Tour ticketTour : tours) {
            if (ticketTour.name.equals(tourName)) {
                if (buyTicketForTourHelper(ticketTour))
                    System.out.println("\nTicket Bought Succesfully.");
                else
                    System.out.println("\nNo Tickets Left !");
            }
        }
    }

    // bilet almak için başka mekanizma eklenebebilir
    private boolean buyTicketForTourHelper(Tour chosenTour) {
        if (chosenTour.numberofTickets != 0) {
            return true;
        } else
            return false;

    }

    public boolean cancelTicket(Ticket tickets) {
        System.out.println("cancel Ticket");
        return true;
    }

    // ticketları görüntülemek için view function.
    private void viewTickets() {
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
