package com;
import java.util.*;

public class Customer extends User {

    /* fields for customer class */
    private String phoneNumber;

    /* stack for keeping reservations */
    private Stack<Reservation> reservations = new Stack<Reservation>();

    /* stack for keeping tickets */
    private Stack<Ticket> tickets = new Stack<Ticket>();

    /* stack for keeping favourite hotels */
    private Stack<Hotel> favoriteHotels = new Stack<Hotel>();

    /* stack for keeping favourite tours */
    private Stack<Tour> favoriteTours = new Stack<Tour>();
    private static Scanner input = new Scanner(System.in);

    /**
     * Constructor that creates a new customer with necessary fields
     */
    public Customer(String username,String name, String surName, String password, User.userType role, String phoneNumber, String email) {
        super(username,name, surName, password,role,email);
        this.phoneNumber = phoneNumber;
    }
    public Customer(){}

    /**
     * This method first lists the tours in the system  to the listing type of customer
     * Then asks for tour name for by a ticket for that tour
     * If that tour has a ticket left, the buying operation is successful.
     */
    private void buyTicketForTour() {
        searchTours();
        String tourName;

        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter tour name for buy a Ticket : ");
        tourName = input.nextLine();

        for(Tour ticketTour : tours) {
            if (ticketTour.name.toUpperCase(Locale.ROOT).equals(tourName)) {
                if(buyTicketForTourHelper(ticketTour)) System.out.println("\nTicket Bought Successfully.");
                else System.out.println("\nNo Tickets Left !");
            }
        }
    }

    /**
     * This method is helper method for buying a new ticket for tour.
     * @param chosenTour name of the tour for buy a ticket
     * @return true if the buy operation is successful, false otherwise
     */
    private boolean buyTicketForTourHelper(Tour chosenTour) {
        int numberofParticipants;
        String name;

        if(chosenTour.numberofTickets!=0){ // ticket can be bought
            Ticket newTicket = new Ticket();
            //if(input.hasNextLine()) input.nextLine(); // clear buffer
            System.out.print("\nHow many tickets do you want to buy ?  :  ");
            numberofParticipants = input.nextInt();
            if(input.hasNextLine()) input.nextLine(); // clear buffer
            for(int i=0;i<numberofParticipants;i++){
                System.out.print("\nEnter "+(i+1)+". name : ");
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

    /**
     * This method cancels a ticket from the tickets of customer
     * If ticket name is in tickets of customer, cancel operation is successful
     */
    private void cancelTicket() {
        int ticketnumber;
        viewTickets();
        if(tickets.size()!=0){
            System.out.print("\nSelect ticket to cancel : ");
            ticketnumber = input.nextInt();
            if (ticketnumber > tickets.size()) System.out.println("Cancel operation failed !");
            else {
                if (cancelTicket(tickets.get(ticketnumber))) System.out.println("Ticket is canceled successfully");
                else System.out.println("Cancel operation failed !");
            }
        }
    }

    /**
     * Helper method for canceling tickets
     * @param ticket name to be canceled
     * @return true if the cancel operation is successful, false otherwise
     */
    private boolean cancelTicket(Ticket ticket) {
        ticket.isCanceled = true;
        for(Tour nextTour : tours){
            if(ticket.ticketName.toUpperCase(Locale.ROOT).equals(nextTour.name)){
                nextTour.numberofTickets++;
                return true;
            }
        }
        return false;
    }


    /**
     * Lists the tickets of customer
     */
    private void viewTickets() {
        int i=1;
        System.out.println("\nYour Tickets");
        System.out.println("------------");
        if(tickets.size()==0) System.out.println("No tickets!");
        else {
            for (Ticket nextTicket : tickets) {
                System.out.println(i + ". Ticket : " + nextTicket.toString() + "\n");
                i++;
            }
        }
    }

    /**
     * This method first lists the hotels in the system according to the listing type of customer
     * Then asks for hotel name for by a ticket for that hotel
     * If that hotel has a room left and the dates are available, the reservation operation is successful.
     */
    private void makeReservationForHotel(){
        searchHotels();
        String hotelName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter hotel name to make reservation : ");
        hotelName = input.nextLine();

        for(Hotel reservationHotel : hotels) {
            if (reservationHotel.name.toUpperCase(Locale.ROOT).equals(hotelName)) {
                if(makeReservationForHotel(reservationHotel)) System.out.println("\nReservation made Successfully.");
                else System.out.println("\nNo Rooms Left or Dates are not Available !");
            }
        }
    }

    /**
     * This method is helper method for making a reservation for hotel.
     * Necessary inputs are taken and suitable date is chosen
     * @param chosenHotel name of the hotel to make a reservation
     * @return true if the reservation operation is successful, false otherwise
     */
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
        else{
            System.out.println("Chosen Hotel is not available! \n" +
                    "The closest available hotels to the chosen hotel are as follows :");
            nearHotels(chosenHotel.location);
            String hotelName;
            if(input.hasNextLine()) input.nextLine(); // clear buffer
            System.out.print("\nEnter hotel name to make reservation : ");
            hotelName = input.nextLine();

            for(Hotel reservationHotel : hotels) {
                if (reservationHotel.name.toUpperCase(Locale.ROOT).equals(hotelName)) {
                    if(makeReservationForHotel(reservationHotel)) System.out.println("\nReservation made Successfully.");
                    else System.out.println("\nNo Rooms Left or Dates are not Available !");
                }
            }
            return true;
        }
//        return false;
    }

    /**
     * This method checks for dates for making reservation
     * if dates are available for room, reservation operation is successful
     * @param startDay of reservation
     * @param startMonth  of reservation
     * @param endDay  of reservation
     * @param endMonth  of reservation
     * @param chosenHotel is the name of hotel
     * @return true if the dates are available in that reservation date, false otherwise
     */
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

    /**
     * This method is adjusts the dates after making reservation
     * @param startDay  of reservation
     * @param startMonth  of reservation
     * @param endDay  of reservation
     * @param endMonth  of reservation
     * @param chosenHotel  of reservation
     * @param isfull keeps the information of rooms avaibility
     */
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

    /**
     * Lists the reservations of customer
     */
    private void viewReservations() {
        int i=1;
        System.out.println("\nYour Reservations");
        System.out.println("-----------------");
        if(reservations.size()==0) System.out.println("No Reservations!");
        else {
            for (Reservation nextReservation : reservations) {
                System.out.println(i + ". Reservation : " + nextReservation.toString() + "\n");
                i++;
            }
        }
    }

    /**
     * This method cancels a reservation from the reservations of customer
     * If reservation name is in reservations of customer, cancel operation is successful
     */
    private void cancelReservation() {
        int reservationnumber;
        viewReservations();
        if(reservations.size()!=0) {
            System.out.print("\nSelect reservation to cancel : ");
            reservationnumber = input.nextInt();
            if (reservationnumber > reservations.size()) System.out.println("Cancel operation failed !");
            else {
                if (cancelReservation(reservations.get(reservationnumber)))
                    System.out.println("Reservation is canceled successfully");
                else System.out.println("Cancel operation failed !");
            }
        }
    }
    /**
     * Helper method for canceling reservations
     * @param reservation name to be canceled
     * @return true if the cancel operation is successful, false otherwise
     */
    private boolean cancelReservation(Reservation reservation) {
        for(Hotel nextHotel : hotels){
            if(reservation.reservationName.toUpperCase(Locale.ROOT).equals(nextHotel.name)){
                nextHotel.numberofRooms++;
                adjustCalendarforReservation(reservation.startDay,reservation.startMonth,reservation.endDay,reservation.endMonth,nextHotel,false);
                reservations.remove(reservation);
                return true;
            }
        }
        return false;
    }

    /**
     * This method adds a hotel to the favorites of customer
     */
    private void addToMyFavoriteHotels(){
        String hotelName;
     //   if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter hotel name to add Favorites : ");
        hotelName = input.nextLine();

        for(Hotel nextHotel : hotels) {
            if (nextHotel.name.toUpperCase(Locale.ROOT).equals(hotelName)) {
                if(addToMyFavoriteHotels(nextHotel)) System.out.println("\nHotel added to favorites Successfully.");
                else System.out.println("\nAn Error Happened !");
            }
        }
    }

    /**
     * Helper method for adding to the favorites
     * @param hotel name to be added
     * @return true if add operation is successful
     */
    private boolean addToMyFavoriteHotels(Hotel hotel) {
        favoriteHotels.add(hotel);
        return false;
    }

    /**
     * This method adds a tour to the favorites of customer
     */
    private void addToMyFavoriteTours() {
        String tourName;
        if(input.hasNextLine()) input.nextLine(); // clear buffer
        System.out.print("\nEnter tour name to add Favorites : ");
        tourName = input.nextLine();

        for(Tour nextTour : tours) {
            if (nextTour.name.toUpperCase(Locale.ROOT).equals(tourName)) {
                if(addToMyFavoriteTours(nextTour)) System.out.println("\nTour added to favorites Successfully.");
                else System.out.println("\nAn Error Happened !");
            }
        }
    }

    /**
     * Helper method for adding to the favorites
     * @param tour name to be added
     * @return true if add operation is successful
     */
    private boolean addToMyFavoriteTours(Tour tour) {
        favoriteTours.add(tour);
        return false;
    }

    /**
     * Lists the favorites of customer that added before
     * First lists the hotels
     * Then lists the tours
     */
    private void viewMyFavs() {
        int i=1;
        System.out.println("\nYour Favorites");
        System.out.println("--------------\n");
        if(favoriteHotels.size()==0) {
            System.out.println("No favs!");
            return;
        }
        for(Hotel nextHotel : favoriteHotels){
            System.out.println(i+") : "+nextHotel.toString()+"\n");
            i++;
        }
        for(Tour nextTour : favoriteTours){
            System.out.println(i+") : "+nextTour.toString()+"\n");
            i++;
        }
    }

    /**
     * Prints the reviews of tours
     */
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

    /**
     * Prints the reviews of hotels
     */
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

    /**
     * This method is used for rating the hotels between given scale
     */
    private void rateHotel() {
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

    /**
     * This method is used for leaving a comment to a hotel
     */
    private void leaveCommentToHotel() {
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

    /**
     * This method is used for rating the tours between given scale
     */
    private void rateTour() {

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

    /**
     * This method is used for leaving a comment to a tour
     */
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

    /**
     * This is the main menu for controlling the customer system
     * This menu is used by customers
     */
    protected void customerMenu(){
        int option;
        System.out.println("------------------");
        System.out.println("Welcome to the Customer Menu..");

        do{
            System.out.println("\n1-> Buy a Ticket for a Tour");
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

}

