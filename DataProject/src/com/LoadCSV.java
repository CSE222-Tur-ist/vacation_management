package com;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LoadCSV extends Utilities {
    public LoadCSV() {
    }

    public void loadHotels() throws IOException {
        /**
         * csv storing
         */
        HotelManager hm = new HotelManager();

        //vacation_management/DataProject/src/com/
        String file = "C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\Hotel.csv";
        BufferedReader reader = null;
        String line = "";

        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            Hotel newHotel = new Hotel(row[0], row[1], Integer.parseInt(row[2]), row[3], Double.parseDouble(row[4]));
            //hotels.add(newHotel);
            hm.addHotel(newHotel);
        }/*
        for (int i = 0; i < hotels.size(); i++) {
            System.out.println(hotels.get(i).name);;
        }*/
        reader.close();
    }

    public void loadTours() throws IOException {
        /**
         * csv storing
         */
        String file = "C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\Tour.csv";
        BufferedReader reader = null;
        String line = "";


        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            String startDate = row[0];
            String endDate = row[1];
            String name = row[2];
            int numberofTickets = Integer.parseInt(row[3]);

            // step one : converting comma separate String to array of String
            //routes
            String[] r = row[4].split(",");
            // step two : convert String array to list of String
            List<String> fixedLenghtList = Arrays.asList(r);
            // step three : copy fixed list to an ArrayList
            LinkedList<String> routesfromCsv = new LinkedList<>(fixedLenghtList);

            double price = Double.parseDouble(row[5]);

            //comments
            String[] c = row[6].split(",");
            // step two : convert String array to list of String
            List<String> commentList = Arrays.asList(c);
            // step three : copy fixed list to an ArrayList
            Queue<String> commentsfromCsv = new LinkedList<>(commentList);

            //rates
            String [] ra = row[7].split(",");
            // step two : convert String array to list of String
            List<Integer> rateList = Arrays.asList(ra).stream().map(s -> Integer.parseInt(s.trim())).collect(Collectors.toList());
            // step three : copy fixed list to an ArrayList
            Queue<Integer> ratesfromCsv = new LinkedList<>(rateList);

            Tour newTour = new Tour(startDate, endDate, name, numberofTickets, routesfromCsv, price, commentsfromCsv, ratesfromCsv);
            tours.add(newTour);
        }

       /* for (int i = 0; i < tours.size(); i++) {
            System.out.println(tours.get(i).name);
        }*/

        reader.close();
    }


    public void loadHotelManagers() throws IOException {
        /**
         * csv storing
         */
        String file = "C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\HotelManager.csv";
        BufferedReader reader = null;
        String line = "";

        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            HotelManager newHotelManager = new HotelManager(row[0], row[1], row[2], row[3], User.userType.HOTEL_MANAGER, row[4]);
            hotelManagers.add(newHotelManager);
        }
        /*
        for (int i = 0; i < hotelManagers.size(); i++) {
            System.out.println(hotelManagers.get(i).getName());;
        }*/
        reader.close();
    }

    public void loadTourManagers() throws IOException {
        /**
         * csv storing
         */
        String file = "C:\\Users\\Ali Kaya\\Desktop\\dataproject\\vacation_management\\DataProject\\src\\com\\out\\production\\com\\com\\TourManager.csv";
        BufferedReader reader = null;
        String line = "";

        reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            String[] row = line.split(";");
            TourManager newTourManager = new TourManager(row[0], row[1], row[2], row[3], User.userType.TOUR_MANAGER, row[4]);
            tourManagers.add(newTourManager);
        }
        /*
        for (int i = 0; i < tourManagers.size(); i++) {
            System.out.println(tourManagers.get(i).getName());;
        }*/
        reader.close();
    }
}
