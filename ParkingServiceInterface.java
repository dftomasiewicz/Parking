package com.dft;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ParkingServiceInterface {

    void freePlaces();
    boolean takeTicket(String registration);
    void exit(String registration);
    Map<String, Integer> occupiedPlacesInMoth(Month month);
    double earnMoneyInMoth(Month month);

}
