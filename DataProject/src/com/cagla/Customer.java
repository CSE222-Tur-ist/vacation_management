package com.cagla;

public class Customer {
    // private String name;
    // private String surname;
    private String userName;
    private String password;
    private String phoneNumber;
    private String email;

    // reservation -> STACK
    private Reservation[] reservations;

    private Ticket[] tickets;

    //
    // STACK.
    private Hotel[] favoriteHotels;

    // tarihi en yakın olan turu önce gösterecek şekilde => araya ekleme
    // yapılabilmesi için
    // bst olamaz çünkü aynı tarihli turları birbiri üzerine yazar.
    // heap olabilir belki???
    // PRIORITY QUEUE.
    private Tour[] favoriteTours;

    // veri tutma şekli değişebilir.
    public Tour[] searchTours() {
        // searchHotels ile aynı işliyor.
    }

    public boolean buyTicketForTour(Tour chosenTour) {
    }

    public boolean cancelTicket(Ticket tickets) {
        System.out.println("cancel Ticket");
        return true;
    }

    // ticketları görüntülemek için view function.
    private void viewTickets() {
    }

    // veri tutma şekli değişebilir.
    public Hotel[] searchHotels() {
        // priotiy queue burada kullanabilir.
        Hotel[] hotels;
        System.out.println("type 1 to see most popular hotels." +
                "type 2 to a-z" +
                "type 3 to price." +
                " else 0.");
        // input
        int choose;
        choose = 1;
        // switch case ile sort algoritmasını yönetme
        /*
         * if(choose){
         * System.out.println("most populars:");
         * // popülerlik için sort.
         * // priority queue.
         * return hotels;
         * }
         * else{
         * System.out.println("default order.");
         * return hotels;
         * }
         */
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
