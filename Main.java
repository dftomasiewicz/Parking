package com.dft;

import java.time.Month;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Place p1 = new Place("p1");
        Place p2 = new Place("p2");
        Place p3 = new Place("p3");
        Place p4 = new Place("p4");
        Place p5 = new Place("p5");
        Place p6 = new Place("p6");
        Place p7 = new Place("p7");
        Place p8 = new Place("p8");
        Place p9 = new Place("p9");

        Parking parkingJ = new Parking("Jerozolimskie9");
        parkingJ.addPlace(p1);
        parkingJ.addPlace(p2);
        parkingJ.addPlace(p3);
        parkingJ.addPlace(p4);
        parkingJ.addPlace(p5);
        parkingJ.addPlace(p6);
        parkingJ.addPlace(p7);
        parkingJ.addPlace(p8);
        parkingJ.addPlace(p9);

        ParkingService ps = new ParkingService(parkingJ);

        System.out.println("Welcome to our parking");
        boolean quit = false;
        int choice = 0;
        while (!quit) {
            printInstruction();
            System.out.println("Please enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    printInstruction();
                    break;
                case 1:
                    ps.freePlaces();
                    break;
                case 2:
                    ps.takeTicket(giveRegistration());
                    break;
                case 3:
                    ps.exit(giveRegistration());
                    break;
                case 4:
                    ps.occupiedPlacesInMoth(Month.of(giveMonth()));
                    break;
                case 5:
                    ps.earnMoneyInMoth(Month.of(giveMonth()));
                    break;
                case 6:
                    quit = true;
                    break;
            }
        }
    }

    public static void printInstruction() {
        System.out.println("\nPress ");
        System.out.println("\t 0 - To print choice options.");
        System.out.println("\t 1 - To show free places.");
        System.out.println("\t 2 - To take ticket.");
        System.out.println("\t 3 - To exit.");
        System.out.println("\t 4 - To show occupied palces in month.");
        System.out.println("\t 5 - To show earn money in month.");
        System.out.println("\t 6 - To quit the application.");
    }

    public static String giveRegistration() {
        System.out.println("Give your registration: ");
        String registration = scanner.nextLine();
        return registration;
    }

    public static int giveMonth() {
        System.out.println("Select number of month: ");
        int month = 0;
        while (month < 1 || month > 12) {
            month = scanner.nextInt();
            scanner.nextLine();
            if (month < 1 || month > 12) {
                System.out.println("This number month doesn't exist.");
                System.out.println("Please try again.");
            }
        }
        return month;
    }
}

