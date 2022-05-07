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
    private static Scanner input = new Scanner(System.in);

    public Customer(String username,String name, String surName, String ID, String password, User.userType role, String phoneNumber, String email) {
        super(username,name, surName, ID, password,role,email);
        this.phoneNumber = phoneNumber;
    }

    private void buyTicketForTour() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter tour name for buy a Ticket : ");
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

    private void cancelTicket() {
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

    private void makeReservationForHotel(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter hotel name to make reservation : ");
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

    private void viewReservations() {
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
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    private void addToMyFavoriteHotels(){
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter hotel name to add Favorites : ");
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

    private void addToMyFavoriteTours() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter tour name to add Favorites : ");
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

    private void viewMyFavs() {
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

    private void printPerformanceReviewsTour() {
        int inputNumber;

        listTours();
        System.out.print("\nSelect Tour to see Performance Reviews : ");
        inputNumber = input.nextInt();

        if(inputNumber>tours.size()) {
            System.out.println("Operation failed !");
        } else {
            System.out.println(tours.get(inputNumber-1).comments.toString());
        }
    }

    private void printPerformanceReviewsHotel() {
        int inputNumber;

        listHotels();
        System.out.print("\nSelect Hotel to see Performance Reviews : ");
        inputNumber = input.nextInt();

        if(inputNumber>hotels.size()) {
            System.out.println("Operation failed !");
        } else {
            System.out.println(hotels.get(inputNumber-1).comments.toString());
        }
    }

    // buyTicket veya makeReservation fonksiyonları içinde çağrılacak.
    private boolean makePayment() {
        return true;
    }

    private void rateHotel() {
        // cancel edilmemiş rezervasyonlardan otel bilgilerini çekip
        // sıralayarak hangisini puanlayacağını seçsin.
        // hotel classının içindeki data fieldda.

        int reservationNumber;
        int rateNumber;

        viewReservations();
        System.out.print("\nSelect Hotel to rate : ");
        reservationNumber = input.nextInt();

        System.out.print("\nPlease type your rate point (0-10 scale) : ");
        rateNumber = input.nextInt();

        if(reservationNumber>reservations.size() || (rateNumber < 0 || rateNumber > 10)) {
            System.out.println("Rate operation failed !");
        } else {
            for(Hotel nextHotel : hotels){
                if(reservations.get(reservationNumber).reservationName.equals(nextHotel.name)){
                    nextHotel.rates.add(rateNumber);
                    nextHotel.rateSize++;
                }
            }
            System.out.println("Hotel Rate operation done successfully");
        }
    }

    private void leaveCommentToHotel() {
        // cancel edilmemiş rezervasyonlardan otel bilgilerini çekip
        // sıralayarak hangisine yorum bırakacağını seçsin.
        // hotel classının içindeki data fieldda.

        int reservationNumber;
        String comment;

        viewReservations();
        System.out.print("\nSelect Hotel to comment : ");
        reservationNumber = input.nextInt();

        System.out.print("\nPlease type your comment : ");
        comment = input.nextLine();

        if(reservationNumber>reservations.size()) {
            System.out.println("Comment operation failed !");
        } else {
            for(Hotel nextHotel : hotels){
                if(reservations.get(reservationNumber).reservationName.equals(nextHotel.name)){
                    nextHotel.comments.add(comment);
                }
            }
            System.out.println("Hotel Comment operation done successfully");
        }
    }

    private void rateTour() {
        // cancel edilmemiş ve geçmişte kalmış ticketlardan tur bilgilerini çekip
        // sıralayarak hangisini puanlayacağını seçsin.
        // tur classının içindeki data fieldda.

        int tourNumber;
        int rateNumber;

        viewTickets();
        System.out.print("\nSelect Tour to rate : ");
        tourNumber = input.nextInt();

        System.out.print("\nPlease type your rate point (0-10 scale) : ");
        rateNumber = input.nextInt();

        if(tourNumber>tickets.size() || (rateNumber < 0 || rateNumber > 10)) {
            System.out.println("Rate operation failed !");
        } else {
            for(Tour nextTour : tours){
                if(tickets.get(tourNumber).ticketName.equals(nextTour.name)){
                    nextTour.rates.add(rateNumber);
                    nextTour.rateSize++;
                }
            }
            System.out.println("Tour Rate operation done successfully");
        }
    }

    private void leaveCommentToTour() {
        // cancel edilmemiş ticketlardan tur bilgilerini çekip
        // sıralayarak hangisini yorumlayacağını seçsin.
        // hotel classının içindeki data fieldda.

        int tourNumber;
        String comment;

        viewTickets();
        System.out.print("\nSelect Tour to comment : ");
        tourNumber = input.nextInt();

        System.out.print("\nPlease type your comment : ");
        comment = input.nextLine();

        if(tourNumber>tickets.size()) {
            System.out.println("Comment operation failed !");
        } else {
            for(Tour nextTour : tours){
                if(tickets.get(tourNumber).ticketName.equals(nextTour.name)){
                    nextTour.comments.add(comment);
                }
            }
            System.out.println("Tour Comment operation done successfully");
        }
    }

    protected void customerMenu(){
        //maindendeki login methodu buraya yonlendiriyor
        int option;
        System.out.println("------------------");
        System.out.println("Welcome to the Customer Menu..");

        do{
            System.out.println("1-> Buy a Ticket for a Tour");
            System.out.println("2-> Cancel a Ticket");
            System.out.println("3-> View your Tickets");
            System.out.println("4-> Add a Tour to my Favourites.");
            System.out.println("5-> View Performance Reviews of a Tour");
            System.out.println("6-> Rate a Tour");
            System.out.println("7-> Leave a comment to a Tour");

            System.out.println("8-> Make a Reservation for a Hotel");
            System.out.println("9-> Cancel a Reservation");
            System.out.println("10-> View your Reservations");
            System.out.println("11-> Add a Hotel to my Favourites.");
            System.out.println("12-> View Performance Reviews of a Hotel");
            System.out.println("13-> Rate a Hotel");
            System.out.println("14-> Leave a comment to a Hotel");

            System.out.println("15-> View My Favourites");

            System.out.println("16-> Exit");
            System.out.print("Enter your choice: ");
            option= input.nextInt();

            switch (option){
                case 1: System.out.println("\n-----------------------"); buyTicketForTour(); break;
                case 2: System.out.println("\n-----------------------"); cancelTicket(); break;
                case 3: System.out.println("\n-----------------------"); viewTickets(); break;
                case 4: System.out.println("\n-----------------------"); addToMyFavoriteTours(); break;
                case 5: System.out.println("\n-----------------------"); printPerformanceReviewsTour(); break;
                case 6: System.out.println("\n-----------------------"); rateTour(); break;
                case 7: System.out.println("\n-----------------------"); leaveCommentToTour(); break;

                case 8: System.out.println("\n-----------------------"); makeReservationForHotel(); break;
                case 9: System.out.println("\n-----------------------"); cancelReservation(); break;
                case 10: System.out.println("\n-----------------------"); viewReservations(); break;
                case 11: System.out.println("\n-----------------------"); addToMyFavoriteHotels(); break;
                case 12: System.out.println("\n-----------------------"); printPerformanceReviewsHotel(); break;
                case 13: System.out.println("\n-----------------------"); rateHotel(); break;
                case 14: System.out.println("\n-----------------------"); leaveCommentToHotel(); break;

                case 15: System.out.println("\n-----------------------"); viewMyFavs(); break;
                case 16: System.out.println("\nExiting.."); break;

                default: System.out.println("Input is not valid! Try again.."); break;
            }
        }while (option != 16);

        input.close();
    }

    private void report() {
    }

    // public void aboutUs(){}
    // public void viewFAQ(){}
}

