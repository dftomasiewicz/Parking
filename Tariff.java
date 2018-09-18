package com.dft;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;

public class Tariff {
    private double payment;
    private TypeOfTariff type;

    public Tariff() {
        if ( (LocalTime.now().getHour() >=6) && (LocalTime.now().getHour() < 18)){
//        if (LocalTime.now().isAfter(LocalTime.of(6,0)) && (LocalTime.now().isBefore(LocalTime.of(18,0)))){
            this.type = TypeOfTariff.DAY;
            payment = 5.5;
        } else {
            this.type = TypeOfTariff.NIGHT;
            payment = 1.5;
        }
    }

    public double getPayment() {
        return payment;
    }

    public TypeOfTariff getType() {
        return type;
    }

}
