package com.dft;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Place {
    private String placeID;
    private PlaceStatus placeStatus;
    private Set<Ticket> tickets = new HashSet<>();

    public Place(String placeID) {
        this.placeID = placeID;
        placeStatus = PlaceStatus.FREE;
    }

    public boolean addTicket(Ticket ticket) {
        return tickets.add(ticket);
    }

    public String getPlaceID() {
        return placeID;
    }


    public PlaceStatus getPlaceStatus() {
        return placeStatus;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }


    public void setPlaceStatus(PlaceStatus placeStatus) {
        this.placeStatus = placeStatus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Place place = (Place) o;
        return Objects.equals(placeID, place.placeID) &&
                placeStatus == place.placeStatus &&
                Objects.equals(tickets, place.tickets);
    }

    @Override
    public int hashCode() {

        return Objects.hash(placeID, placeStatus, tickets);
    }

    @Override
    public String toString() {
        return "Place{" +
                "placeID='" + placeID + '\'' +//
                '}';
    }
}
