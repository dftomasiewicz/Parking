package com.dft;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ticket {
    private int nr = 0;
    private LocalDateTime timeOfEntry;
    private LocalDateTime timeOfExit;
    private Tariff tariff;
    private String placeID;
    private double parkingFee;

    public Ticket() {
        nr++;
        tariff = new Tariff();
    }

    public int getNr() {
        return nr;
    }

    public LocalDateTime getTimeOfEntry() {
        return timeOfEntry;
    }

    public LocalDateTime getTimeOfExit() {
        return timeOfExit;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public String getPlaceID() {
        return placeID;
    }

    public double getParkingFee() {
        return parkingFee;
    }

    public void setTimeOfEntry(LocalDateTime timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public void setTimeOfExit(LocalDateTime timeOfExit) {
        this.timeOfExit = timeOfExit;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    public void setPlaceID(String placeID) {
        this.placeID = placeID;
    }

    public void setParkingFee(double parkingFee) {
        this.parkingFee = parkingFee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return nr == ticket.nr &&
                Double.compare(ticket.parkingFee, parkingFee) == 0 &&
                Objects.equals(timeOfEntry, ticket.timeOfEntry) &&
                Objects.equals(timeOfExit, ticket.timeOfExit) &&
                Objects.equals(tariff, ticket.tariff) &&
                Objects.equals(placeID, ticket.placeID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nr, timeOfEntry, timeOfExit, tariff, placeID, parkingFee);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "nr=" + nr +
                ", timeOfEntry=" + timeOfEntry +
                ", timeOfExit=" + timeOfExit +
                ", tariff=" + tariff +
                ", placeID='" + placeID + '\'' +
                ", parkingFee=" + parkingFee +
                '}';
    }
}
