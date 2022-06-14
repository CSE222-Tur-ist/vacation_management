package com;
import java.io.*;
import java.util.*;
import com.datastructures.*;

/**
 * Utilities class allows us to use certain features of the vacation_management program.
 */
public abstract class Utilities {

    /**for user inputs we use scanner */
    public static Scanner input = new Scanner(System.in);

    /**Keeps Hotel Managers inside */
    static ArrayList<HotelManager> hotelManagers = new ArrayList<>();

    /**Keeps Tour Managers inside */
    static ArrayList<TourManager> tourManagers = new ArrayList<>();

    /**Keeps Existing Tours in */
    static ArrayList<Tour> tours = new ArrayList<>(); // tours confirmed by admin

    /**Keeps Existing Hotels in */
    static ArrayList<Hotel> hotels = new ArrayList<>(); // hotels confirmed by admin

    /** Keeps Location names in alphabetical order, to be used in listing by location */
    static BinarySearchTree<Location> locations = new BinarySearchTree<Location>();

    /**Keeps Locations and distance between them into a graph*/
    static Graph map;

    /**It keeps the location information in a way that its indexes are related to the IDs of our map graph.*/
    static ArrayList<String> locationTableforMap = new ArrayList<String>();

    /** It is used to create our graph called map*/
    public Utilities(){
        map = new ListGraph(8,true);
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("map.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) { // for vertices
                String[] data = row.split(";");
                String source = data[0];
                if(!locationTableforMap.contains(source)) {
                    locationTableforMap.add(source);
                }
            }
            csvReader.close();
            locationTableforMap.remove(0); // ilki source basliği
            /*ListIterator<String> iter = locationTableforMap.listIterator();
            while(iter.hasNext())
                System.out.println(iter.next());*/

            BufferedReader csvReader_ = new BufferedReader(new FileReader("map.csv"));
            String row_;
            while ((row_ = csvReader_.readLine()) != null) { // for edges
                String[] data = row_.split(";");
                String source = data[0];
                String dest = data[1];
                int distance = Integer.parseInt(data[2]);
                int ind1 = indexLocation(source);
                int ind2 = indexLocation(dest);
                if(ind1!=-1&&ind2!=-1)map.insert(new Edge(ind1,ind2,distance));
            }
            csvReader_.close();
        }catch (Exception e){
            System.out.println("Error while file reading !");
        }
    }

    /**
     * This method takes the location information and returns ID(in graph) - index(in ArrayList) value
     * @param loc location information
     * @return int - index of the given location information
     */
    private int indexLocation(String loc){ // graph vertices are represented by ints but they actually string(location)
        for(int i=0;i<locationTableforMap.size();i++){ // find the index of location in locationTableforMap
            if(locationTableforMap.get(i).equals(loc)) return i;
        }
        return -1;
    }

    /**
     * This method is used to sort the Tours in different ways and print them on the screen.
     */
    public void searchTours() {
        int choose;

        //if(input.hasNextLine()) input.nextLine(); // clear buffer

        //System.out.println("------------------------");
        System.out.println("1. List the Tours\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: listTours(); break;
            case 2: sortbyRateT();break;
            case 3: sortbyAlphabeticT(); break;
            case 4: sortbyPriceT(); break;
            default: System.out.println("Your choice is not correct !"); break;
        }
    }

    /**
     * This method is used to print the Tours on the screen by looking at the order they were added.
     */
    protected static void listTours(){
        int i=1;
        if(tours.size()==0) {
            System.out.println("No tours!");
            return;
        }
        for(Tour nextTour : tours){
            System.out.println(i + ".  "+ nextTour);
            i++;
        }
    }

    /**
     * This method is used to print Tours by looking at their rates.
     */
    private void sortbyRateT() {
        if(tours.size()==0) {
            System.out.println("No tours!\n\n");
            return;
        }
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

    /**
     * This method is used to print the tours in alphabetical order.
     */
    private void sortbyAlphabeticT() {
        if(tours.size()==0) {
            System.out.println("No tours!\n\n");
            return;
        }
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

    /**
     * This method is used to print tours by looking at their prices.
     */
    private void sortbyPriceT() {
        if(tours.size()==0) {
            System.out.println("No tours!\n\n");
            return;
        }
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

    /**
     * This method is used to sort the Hotels in different ways and print them on the screen.
     */
    public void searchHotels(){
        int choose;

        if(input.hasNextLine()) input.nextLine(); // clear buffer

        System.out.println("------------------------");
        System.out.println("1. List the Hotels\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: listHotels(); break;
            case 2: sortbyRate(); break;
            case 3: sortbyAlphabetic(); break;
            case 4: sortbyPrice(); break;
            default: System.out.println("Your choice is not correct !"); break;
        }
    }

    /**
     * This method is used to print the Hotels on the screen by looking at the order they were added.
     */
    protected static void listHotels(){
        int i=1;
        if(hotels.size()==0) {
            System.out.println("No hotels!\n\n");
            return;
        }
        for(Hotel nextHotel : hotels){
            System.out.println(i + ".  "+ nextHotel);
            i++;
        }
    }

    /**
     * This method is used to print Hotels by looking at their rates.
     */
    private void sortbyRate() {
        if(hotels.size()==0) {
            System.out.println("No hotels!\n\n");
            return;
        }
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

    /**
     * This method is used to print the Hotels in alphabetical order.
     */
    public void sortbyAlphabetic() {
        if(hotels.size()==0) {
            System.out.println("No hotels!\n\n");
            return;
        }
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

    /**
     * This method is used to print Hotels by looking at their prices.
     */
    private void sortbyPrice() {
        if(hotels==null) return;
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

    /**
     * This method is used to print the 5 closest hotels to the desired hotel if the hotel requested by the user is not available.
     * @param loc location information
     */
    protected void nearHotels(String loc){
        if(hotels.size()==0) {
            System.out.println("No hotels!\n\n");
            return;
        }
        double count = 0;
        Hotel tempH;
        ArrayList<Hotel> tempHotels = new ArrayList<>(hotels);
        PriorityQueue<Pair> nearHotelQ = new PriorityQueue<>(8);

        for (int i = 0 ; i < hotels.size() ; i++){
            double minDist = Double.POSITIVE_INFINITY;
            tempH = null;
            for(Hotel nextHotel : tempHotels){
                double distance = map.getEdge(indexLocation(loc),indexLocation(nextHotel.location)).getWeight();
                if(minDist>distance) {
                    minDist = distance;
                    tempH = nextHotel;
                }
            }
            nearHotelQ.offer(new Pair(count , tempH));
            tempHotels.remove(tempH);
            count++;
        }

        int i = 1;
        for (Pair nextPair : nearHotelQ) {
            if (nextPair.hotel.numberofRooms != 0){
                System.out.println(i + ".  " + nextPair.hotel);
                i++;
            }
            if (i > 5)break;
        }
    }

}

/**
 * The pair class consists of a hotel and a key. It is used in the nearHotels method.
 */
class Pair implements Comparable<Pair>{

    /**Priority of the Pair*/
    double key;
    /**Hotel reference*/
    Hotel hotel;

    /**Constructs a Pair object by given parameters
     * @param key - priority value
     * @param hotel - hotel reference
     */
    public Pair(double key , Hotel hotel){
        this.key = key;
        this.hotel = hotel;
    }

    /**
     * compareTo method
     * @param o2 other Pair reference
     * @return Returns 1; if first pair's key value is bigger
     *                 0; if both pair has same key value
     *                -1; if first pair's key value is lower
     */
    @Override
    public int compareTo(Pair o2) {
        return Double.compare(this.key, o2.key);
    }
}