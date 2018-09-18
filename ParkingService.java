package com.dft;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class ParkingService implements ParkingServiceInterface {
    private Parking parking;
    private List<Ticket> allUsedTickets = new ArrayList<>();
    private Map<String, Ticket> currentTickets = new HashMap<>(); //<registration, ticket>

    public ParkingService(Parking parking) {
        this.parking = parking;
    }

    @Override
    public void freePlaces() {
        boolean isFree = false;
        for (Place p : parking.getPlaces()) {
            if (p.getPlaceStatus().equals(PlaceStatus.FREE)) {
                isFree = true;
                System.out.println("Wolne miesce nr: " + p.getPlaceID());
            }
        }
        if (!isFree) {
            System.out.println("Brak wolnych miejsc");
        }
    }

    @Override
    public boolean takeTicket(String registration) {
        boolean areFree = false;
        for (Place p : parking.getPlaces()) {
            if (p.getPlaceStatus().equals(PlaceStatus.FREE)) {
                areFree = true;
            }
        }
        if (areFree) {
            Ticket ticket = new Ticket();
            ticket.setTimeOfEntry(LocalDateTime.now());
            for(String s : currentTickets.keySet()){
                if(s.equals(registration)){
                    System.out.println("Takie auto juz stoi na parkingu");
                    return false;
                }
            }
            for (Place p : parking.getPlaces()) {
                if (p.getPlaceStatus().equals(PlaceStatus.FREE)) {
                    ticket.setPlaceID(p.getPlaceID());
                    p.addTicket(ticket);
                    currentTickets.put(registration, ticket);
                    p.setPlaceStatus(PlaceStatus.OCCUPIED);
                    System.out.println("Zapraszam na " + p.getPlaceID());
                    return true;
                }
            }
        }
        System.out.println("Brak wolnych miejsc");
        return false;

    }

    @Override
    public void exit(String registration) {
        if (currentTickets.containsKey(registration)) {
            System.out.println("Do zaplaty: ");
//            jak tworzyl sie obiekt ticket, na podstawie tamtego obecnego czasu wybrano taryfe i teraz wyliczy payment * ileRazyPowtorzyloSie10Min
//                FUNKCJA PONIZEJ POTRAFI CZASEM WYRZUCIC -1 SEK., WIEC DOBRZE DODAC +1
//            long sec = Duration.between(LocalDateTime.now(), currentTickets.get(registration).getTimeOfEntry()).toSeconds() + 1;
//                ChoronoUnit przy testowaniu, nigdy nie wyrzucil -1//
            long sec = ChronoUnit.SECONDS.between(currentTickets.get(registration).getTimeOfEntry(), LocalDateTime.now());
            double minutes = ((double) sec / 60);
            double charge = (minutes / 10) * currentTickets.get(registration).getTariff().getPayment();
            System.out.println(charge);
            currentTickets.get(registration).setTimeOfExit(LocalDateTime.now());
            currentTickets.get(registration).setParkingFee(charge);
            allUsedTickets.add(currentTickets.get(registration));
            for (Place p : parking.getPlaces()) {
                if (p.getPlaceID().equals(currentTickets.get(registration).getPlaceID())) {
                    p.setPlaceStatus(PlaceStatus.FREE);
                }
            }
            currentTickets.remove(registration);
        } else {
            System.out.println("Jeszcze nie pobrales biletu - takeTicket");
        }

    }

    @Override
    public Map<String, Integer> occupiedPlacesInMoth(Month month) {
        Map<String, Integer> howManyPlaceBeenOccupied = new TreeMap<>();
        for (Ticket t : allUsedTickets) {
            //auto licze jako obsluzone jesli wyjechalo w tym miesiacu
            if ((LocalDateTime.now().getYear() == t.getTimeOfExit().getYear()) && (t.getTimeOfExit().getMonth().equals(month))) {
                howManyPlaceBeenOccupied.put(t.getPlaceID(), +1);
            }
        }
        int sum = 0;
        for(String s : howManyPlaceBeenOccupied.keySet()){
            sum += howManyPlaceBeenOccupied.get(s);
        }
        System.out.println(howManyPlaceBeenOccupied);
        System.out.println("Lacznie miejsca parkingowe byly zajmowane: " + sum + " razy w miesiacu " + month);
        return howManyPlaceBeenOccupied;
    }

    @Override
    public double earnMoneyInMoth(Month month) {
        List<Double> cashList = new ArrayList<>();
        for (Ticket t : allUsedTickets) {
            if (t.getTimeOfExit().getYear() == LocalDateTime.now().getYear() && t.getTimeOfExit().getMonth().equals(month)) {
                cashList.add(t.getParkingFee());
            }
        }
        double money = 0;
        for (Double d : cashList) {
            money += d;
        }
        System.out.println("W miesiacu " + month + " Zarobionio: " + money );
        return money;
    }
}
