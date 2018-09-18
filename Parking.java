package com.dft;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Parking {
    private String adress;
    private Set<Place> places = new HashSet<>();

    public Parking(String adress) {
        this.adress = adress;
    }

    public Parking(String adress, Place place) {
        this.adress = adress;
        addPlace(place);
    }

    public Parking(String adress, Set<Place> placeSet) {
        this.adress = adress;
        addSetOfPlaces(placeSet);
    }


    public boolean addPlace(Place place) {
        if (places.contains(place)) {
            System.out.println("Takie miejsce juz istnieje na parkingu");
            return false;
        }
        return places.add(place);
    }

    public boolean addSetOfPlaces(Set<Place> placeSet){
        for(Place p : placeSet){
            places.add(p);
        }
        return true;
    }

    public String getAdress() {
        return adress;
    }

    public Set<Place> getPlaces() {
        return places;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPlaces(Set<Place> places) {
        this.places = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parking parking = (Parking) o;
        return Objects.equals(adress, parking.adress) &&
                Objects.equals(places, parking.places);
    }

    @Override
    public int hashCode() {

        return Objects.hash(adress, places);
    }

    @Override
    public String toString() {
        return "Parking{" +
                "adress='" + adress + '\'' +
                ", places=" + places +
                '}';
    }
}
