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

    /**

     * This will store the hotels acoording their ratings in a navigable treemap

     */

    public static NavigableMap<Integer, List<Hotel>> HotelsNavigableMap = new TreeMap<>();

    /**

     * This will store the hotels acoording their price in a navigable treemap

     */

    public static AVLTree<Hotel> HotelsAVL = new AVLTree<>();
    /** It is used to create our graph called map*/
    public Utilities(){
        map = new ListGraph(8,true);
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\map.csv"));
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

            BufferedReader csvReader_ = new BufferedReader(new FileReader("C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\map.csv"));
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
            System.err.println("Error while file reading !");
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
    public boolean searchTours() {
        int choose;

        //if(input.hasNextLine()) input.nextLine(); // clear buffer

        //System.out.println("------------------------");
        System.out.println("1. List the Tours\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: return listTours();
            case 2: return sortbyRateTInsertion();
            case 3: return sortbyAlphabeticT();
            case 4: return sortbyPriceT();
            default:
                System.err.println("Your choice is not correct !");
                return false;
        }
    }

    /**
     * This method is used to print the Tours on the screen by looking at the order they were added.
     */
    protected static boolean listTours(){
        int i=1;
        if(tours.size()==0) {
            System.err.println("No tours!");
            return false;
        }
        for(Tour nextTour : tours){
            System.out.println(i + ". "+ nextTour);
            i++;
        }
        return true;
    }

    /**
     * This method is used to print Tours by looking at their rates.
     */
    private void sortbyRateT() {
        if(tours.size()==0) {
            System.err.println("No tours!\n\n");
            return;
        }
        Tour.type = Tour.compareType.RATE;
        PriorityQueue<Tour> pqR = new PriorityQueue<Tour>(tours.size(), new Tour());
        for (Tour nextTour : tours) {
            pqR.add(nextTour);
        }
        int i = 1;
        for (Tour nextTour : pqR) {
            System.out.println(i + ".  " + nextTour);
            i++;
        }
    }



    private boolean sortbyRateTInsertion() {
        if(tours.size()==0) {
            System.err.println("No tours!\n\n");
            return false;
        }
        System.out.println("\n\n");
        Tour.type = Tour.compareType.RATE;
        InsertionSort.sort(tours);
        int i = 1;
        for (Tour nextTour : tours) {
            System.out.println(i + ".  " + nextTour);
            i++;
        }
        return true;
    }

    /**
     * This method is used to print the tours in alphabetical order.
     */
    private boolean sortbyAlphabeticT() {
        if(tours.size()==0) {
            System.err.println("No tours!\n\n");
            return false;
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
        return true;
    }

    /**
     * This method is used to print tours by looking at their prices.
     */
    private boolean sortbyPriceT() {
        if(tours.size()==0) {
            System.err.println("No tours!\n\n");
            return false;
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
        return true;
    }

    /**
     * This method is used to sort the Hotels in different ways and print them on the screen.
     */
    public boolean searchHotels(){
        int choose;

        //if(input.hasNextLine()) input.nextLine(); // clear buffer

        //System.out.println("------------------------");
        System.out.println("1. List the Hotels\n2. Most Populars\n3. Sort by a-z\n4. Sort by Price\n5. List of Choosen Rate Hotels");
        System.out.print("Enter : ");
        choose = input.nextInt();
        switch (choose){
            case 1: return listHotels();
            case 2: return sortbyRate();
            case 3: return sortbyAlphabetic_withSkipList();
            case 4: return sortbyPriceAVL();
            case 5: return choosebyRateNavigableMap();
            default:
                System.err.println("Your choice is not correct !");
                return false;
        }
    }

    private void sortbyRateNavigable() {
        createHotelsNavigableMap(hotels);
        for(Integer nextRate : HotelsNavigableMap.navigableKeySet()) {
            System.out.println(HotelsNavigableMap.get(nextRate));
        }
    }

    private boolean choosebyRateNavigableMap() {
        System.out.print("Enter Rate of Hotel (0-5): ");
        int rate=input.nextInt();
        createHotelsNavigableMap(hotels);

        NavigableSet<Hotel> temp = getByRating(rate);
        if (temp.size() == 0){
            System.out.println("There is no Hotel with this Rate!");
            return false;
        }
        else{
            System.out.println(temp);
            return true;
        }
    }

    private boolean sortbyPriceAVL() {
        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
            return false;
        }
        Utilities.createHotelsPriceBalancedTree_AVL(hotels);
        System.out.println(HotelsAVL);
        return true;
    }

    /**
     * This method is used to print the Hotels on the screen by looking at the order they were added.
     */
    protected static boolean listHotels(){
        int i=1;
        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
            return false;
        }
        for(Hotel nextHotel : hotels){
            System.out.println(i + ". "+ nextHotel);
            i++;
        }
        return true;
    }

    /**
     * This method is used to print Hotels by looking at their rates.
     */
    private boolean sortbyRate() {
        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
            return false;
        }
        Hotel.type = Hotel.compareType.RATE;
        PriorityQueue<Hotel> pqR = new PriorityQueue<Hotel>(hotels.size(), new Hotel());
        for (Hotel nextHotel : hotels) {
            pqR.add(nextHotel);
        }
        int i = 1;
        for (Hotel nextHotel : pqR) {
            System.out.println(i + ".  " + nextHotel);
            i++;
        }
        return true;
    }

    /**
     * This method is used to print the Hotels in alphabetical order.
     */
    public void sortbyAlphabetic() {
        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
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


    public static boolean sortbyAlphabetic_withSkipList() {

        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
            return false;
        }
        SkipList<String, String> skipList = new SkipList<>();

        for (int i = 0; i < hotels.size(); i++) {
            skipList.add(hotels.get(i).name.toUpperCase(Locale.ROOT).toString(),"Name: "+ hotels.get(i).name.toUpperCase(Locale.ROOT).toString() + " / Location: "+hotels.get(i).location.toString() + " / Features: " + hotels.get(i).features.toString()+
                    " / Number of Rooms: " + hotels.get(i).numberofRooms + " / Price: " +hotels.get(i).price);
        }

        for (String i : skipList) {
        	System.out.println(skipList.get(i));
        }
        return true;
    }

    public static void sortbyPrice__withSkipList(){

        SkipList<Double, String> skipList = new SkipList<>();

        for (int i = 0; i < hotels.size(); i++) {
            skipList.add(hotels.get(i).price,"Name: "+ hotels.get(i).name.toString() + " / Location: "+hotels.get(i).location.toString() + " / Features: " + hotels.get(i).features.toString()+
                    " / Number of Rooms: " + hotels.get(i).numberofRooms + " / Price: " +hotels.get(i).price);
        }
        
        for (Double i : skipList) {
        	System.out.println(skipList.get(i));
        }

    }

    /**
     * This method is used to print the 5 closest hotels to the desired hotel if the hotel requested by the user is not available.
     * @param loc location information
     */
    protected void nearHotels(String loc){
        if(hotels.size()==0) {
            System.err.println("No hotels!\n\n");
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


    /**

     *

     * create Hotels with NavigableMap data structures as sorted access

     *

     * @param hotelList

     */

    public static void createHotelsNavigableMap(ArrayList<Hotel> hotelList) { // O(n)

        for (int i = 0; i <= 5; i++) { // O(1~n) = O(1)

            HotelsNavigableMap.put(i, new ArrayList<>());

        }

        for (Hotel hotel : hotelList) { // O(n)

            HotelsNavigableMap.get((int) hotel.getAveRate()).add(hotel);

        }

    }



    /**

     * Get the hotels by rating

     */

    public static NavigableSet<Hotel> getByRating(int rating) { // O(n)

        NavigableSet<Hotel> hotels = new TreeSet<>();

        for (Hotel hotel : HotelsNavigableMap.get(rating)) { // O(n)

            hotels.add(hotel);

        }

        return hotels;

    }



    /**

     * AVL trees provide faster lookups than Red Black Trees because they are more

     * strictly balanced.

     *

     * @param hotelList

     */

    public static void createHotelsPriceBalancedTree_AVL(ArrayList<Hotel> hotelList) { // O(nlog n)
        Hotel.type = Hotel.compareType.PRICE;

        for (Hotel hotel : hotelList) { // O(n)

            HotelsAVL.insert(hotel);// O(log n)

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