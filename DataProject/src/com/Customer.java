package com;
import java.util.*;

public class Customer extends User {
    // private String name;
    // private String surname;
    //private String userName;  bu bilgi User abstract classında var
    //private String password; bu bilgi User interfaceinde var
    //private String email; bu bilgi User interfaceinde var

    private String phoneNumber;

    private Stack<Reservation> reservations = new Stack<Reservation>();

    private Stack<Ticket> tickets = new Stack<Ticket>();

    private Stack<Hotel> favoriteHotels = new Stack<Hotel>();


    private Stack<Tour> favoriteTours = new Stack<Tour>();
    public static Scanner input = new Scanner(System.in);

    public Customer(String username,String name, String surName, String ID, String password, User.userType role, String phoneNumber, String email) {
        super(username,name, surName, ID, password,role,email);
        this.phoneNumber = phoneNumber;
    }

    public void buyTicketForTour() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Enter tour name for buy a Ticket : ");
        tourName = input.nextLine();

        for(Tour ticketTour : tours) {
            if (ticketTour.name.equals(tourName)) {
                if(buyTicketForTourHelper(ticketTour)) System.out.println("\nTicket Bought Successfully.");
                else System.out.println("\nNo Tickets Left !");
            }
        }
    }

    // bilet almak için başka mekanizma eklenebebilir
    private boolean buyTicketForTourHelper(Tour chosenTour) {

        int numberofParticipants;
        String name;

        if(chosenTour.numberofTickets!=0){ // ticket alınabilir
            Ticket newTicket = new Ticket();
            if(input.hasNextLine()) input.nextLine(); // clear buffer
            System.out.print("\nHow many tickets do you want to buy ?  :  ");
            numberofParticipants = input.nextInt();
            for(int i=0;i<numberofParticipants;i++){
                System.out.print("\nEnter "+i+1+". name");
                name = input.nextLine();
                newTicket.participants.push(name);
            }
            newTicket.ticketName = chosenTour.name;
            newTicket.isCanceled = false;
            newTicket.payment = chosenTour.price * numberofParticipants;
            tickets.push(newTicket); // new ticket added to the tickets of customer
            chosenTour.numberofTickets--;
            return true;
        }
        else return false;
    }

    public void cancelTicket() {
        int ticketnumber;
        viewTickets();
        System.out.print("\nSelect ticket to cancel : ");
        ticketnumber = input.nextInt();
        if(ticketnumber>tickets.size()) System.out.println("Cancel operation failed !");
        else {
            if(cancelTicket(tickets.get(ticketnumber))) System.out.println("Ticket is canceled successfully");
            else System.out.println("Cancel operation failed !");
        }
    }

    private boolean cancelTicket(Ticket ticket) {
        ticket.isCanceled = true;
        for(Tour nextTour : tours){
            if(ticket.ticketName.equals(nextTour.name)){
                nextTour.numberofTickets++;
                return true;
            }
        }
        return false;
    }


    // ticketları görüntülemek için view function.
    private void viewTickets() {
        int i=1;
        System.out.println("\nYour Tickets");
        System.out.println("------------");
        for(Ticket nextTicket : tickets){
            System.out.println(i+". Ticket : "+nextTicket.toString()+"\n");
            i++;
        }
    }

    public void makeReservationForHotel(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Enter hotel name to make reservation : ");
        hotelName = input.nextLine();

        for(Hotel reservationHotel : hotels) {
            if (reservationHotel.name.equals(hotelName)) {
                if(makeReservationForHotel(reservationHotel)) System.out.println("\nReservation made Successfully.");
                else System.out.println("\nNo Rooms Left or Dates are not Available !");
            }
        }
    }

    private boolean makeReservationForHotel(Hotel chosenHotel) {
        int numberofParticipants,startday,startmonth,endday,endmonth,numberofDays;
        String name,start,end;

        chosenHotel.printCalendar();
        System.out.print("\nReservation Start Date ( day/month -> 26/01 ) : ");
        start = input.nextLine();
        System.out.print("\nReservation End Date ( day/month -> 05/11 ) : ");
        end = input.nextLine();

        String[] partsstart = start.split("/");
        startday = Integer.parseInt(partsstart[0]);
        startmonth = Integer.parseInt(partsstart[1]);
        String[] partsend = end.split("/");
        endday = Integer.parseInt(partsend[0]);
        endmonth = Integer.parseInt(partsend[1]);

        numberofDays = ((endmonth*28)+endday)-((startmonth*28)+startday);

        if(chosenHotel.numberofRooms!=0 && checkDates(startday,startmonth,endday,endmonth,chosenHotel)){ // reservasyon yapılabilir
            Reservation newReservation = new Reservation();
            if(input.hasNextLine()) input.nextLine(); // clear buffer
            System.out.print("\nWhat type of room would you prefer ? (enter number of people) :  ");
            numberofParticipants = input.nextInt();
            for(int i=0;i<numberofParticipants;i++){
                System.out.print("\nEnter "+i+1+". name");
                name = input.nextLine();
                newReservation.participants.push(name);
            }
            newReservation.reservationName = chosenHotel.name;
            reservations.push(newReservation); // new reservation added to the reservations of customer
            chosenHotel.numberofRooms--;
            newReservation.payment = chosenHotel.price * numberofParticipants * numberofDays;
            adjustCalendarforReservation(startday,startmonth,endday,endmonth,chosenHotel,true);
            newReservation.startDate = startday+"/"+chosenHotel.months[startmonth-1];
            newReservation.endDate = endday+"/"+chosenHotel.months[endmonth-1];
            newReservation.startDay = startday;
            newReservation.startMonth = startmonth;
            newReservation.endDay = endday;
            newReservation.endMonth = endmonth;
            reservations.push(newReservation); // new ticket added to the tickets of customer

            return true;
        }
        else return false;
    }

    private boolean checkDates(int startDay,int startMonth,int endDay,int endMonth,Hotel chosenHotel){
        if(startDay<1||endDay<1||startDay>28||endDay>28||startMonth<1||endMonth<1||startMonth>12||endMonth>12||startMonth*28+startDay>endMonth*28+endDay)
            return false;
        // başlangıç ayı ve gününden o ayın sonuna kadar
        for(int i=startDay-1;i<28;i++){
                if(chosenHotel.calendar[startMonth].days[i].isFull) return false;
        }
        for(int m=startMonth+1; m<endMonth;m++){ // aradaki aylar ve 28 gün için
            for(int i=0;i<28;i++){
                if(chosenHotel.calendar[m].days[i].isFull) return false;
            }
        }
        // son ayın son gününe kadar
        for(int i=0;i<endDay;i++){
            if(chosenHotel.calendar[endMonth].days[i].isFull) return false;
        }
        return true;
    }

    private void adjustCalendarforReservation(int startDay,int startMonth,int endDay,int endMonth,Hotel chosenHotel,boolean isfull){
        // başlangıç ayı ve gününden o ayın sonuna kadar
        for(int i=startDay-1;i<28;i++){
            chosenHotel.calendar[startMonth].days[i].isFull = isfull;
        }
        for(int m=startMonth+1; m<endMonth;m++){ // aradaki aylar ve 28 gün için
            for(int i=0;i<28;i++){
                chosenHotel.calendar[m].days[i].isFull = isfull;
            }
        }
        // son ayın son gününe kadar
        for(int i=0;i<endDay;i++){
            chosenHotel.calendar[endMonth].days[i].isFull = isfull;
        }
    }

    public void viewReservations() {
        int i=1;
        System.out.println("\nYour Reservations");
        System.out.println("-----------------");
        for(Reservation nextReservation : reservations){
            System.out.println(i+". Reservation : "+nextReservation.toString()+"\n");
            i++;
        }
    }

    private void cancelReservation() {
        int reservationnumber;
        viewReservations();
        System.out.print("\nSelect reservation to cancel : ");
        reservationnumber = input.nextInt();
        if(reservationnumber>reservations.size()) System.out.println("Cancel operation failed !");
        else {
            if(cancelReservation(reservations.get(reservationnumber))) System.out.println("Reservation is canceled successfully");
            else System.out.println("Cancel operation failed !");
        }
    }
    private boolean cancelReservation(Reservation reservation) {
        for(Hotel nextHotel : hotels){
            if(reservation.reservationName.equals(nextHotel.name)){
                nextHotel.numberofRooms++;
                adjustCalendarforReservation(reservation.startDay,reservation.startMonth,reservation.endDay,reservation.endMonth,nextHotel,false);
                return true;
            }
        }
        return false;
    }

    public void addTomyFavoriteHotels(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Enter hotel name to add Favorites : ");
        hotelName = input.nextLine();

        for(Hotel nextHotel : hotels) {
            if (nextHotel.name.equals(hotelName)) {
                if(addToMyFavoriteHotels(nextHotel)) System.out.println("\nHotel added to favorites Successfully.");
                else System.out.println("\nAn Error Happened !");
            }
        }
    }
    private boolean addToMyFavoriteHotels(Hotel hotel) {
        favoriteHotels.add(hotel);
        return false;
    }

    public void addToMyFavoriteTours() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("Enter tour name to add Favorites : ");
        tourName = input.nextLine();

        for(Tour nextTour : tours) {
            if (nextTour.name.equals(tourName)) {
                if(addToMyFavoriteTours(nextTour)) System.out.println("\nTour added to favorites Successfully.");
                else System.out.println("\nAn Error Happened !");
            }
        }
    }
    private boolean addToMyFavoriteTours(Tour tour) {
        favoriteTours.add(tour);
        return false;
    }

    public void viewMyFavs() {
        int i=1;
        System.out.println("\nYour Favorites");
        System.out.println("--------------\n");
        for(Hotel nextHotel : favoriteHotels){
            System.out.println(i+") : "+nextHotel.toString()+"\n");
            i++;
        }
        for(Tour nextTour : favoriteTours){
            System.out.println(i+") : "+nextTour.toString()+"\n");
            i++;
        }
    }


    public void printPerformanceReviews() {
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

