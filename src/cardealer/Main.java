package cardealer;

import cardealer.buyer.*;
import cardealer.cardealer.ICarDealer;
import cardealer.cardealer.LuxuryCarDealer;
import cardealer.cardealer.StandardCarDealer;
import cardealer.carinfo.CarInfo;

import java.util.Scanner;
import java.util.stream.Collectors;

import static cardealer.GetRandom.RANDOM_GEN;

/**
 * @author Moussa
 */
public class Main {

    public static void main(String... args) {

        boolean keepGoing;

        do {
            System.out.println("Welcome to the car dealership.");

            Scanner scanner = new Scanner(System.in);

            System.out.println("What is your name?");
            String name = scanner.nextLine();

            System.out.println("We have two types of cars: standard and luxury.");
            System.out.println("What kind of car are you looking for?");
            int choice = getChoice("1. Standard\n2. Luxury", scanner, 2);

            boolean isLuxury = choice == 2;
            ICarDealer carDealer = isLuxury ? new LuxuryCarDealer() : new StandardCarDealer();

            CarInfo carInfo = getSelection(carDealer, scanner);

            System.out.printf("You have selected a %s.%n", carInfo.printableString());

            int price = carDealer.getPrice(carInfo);

            System.out.printf("The price of this car is $%d.%n", price);

            choice = getChoice("Would you like to buy this car?\n1. Yes\n2. No", scanner, 2);

            if (choice == 1) {
                IBuyer buyer = isLuxury ? new LuxuryBuyer(name, carInfo) : new StandardBuyer(name, carInfo);
                carDealer.sellCar(buyer);
                System.out.printf("Congratulations %s, you have bought a %s for $%d.%n", name, carInfo.printableString(), price);
            }

            choice = getChoice("Would you like to buy another car?\n1. Yes\n2. No", scanner, 2);


            keepGoing = choice == 1;

        } while (keepGoing);
    }

    private static CarInfo getSelection(ICarDealer carDealer, Scanner scanner) {
        var carOptions = carDealer.getAvailableCars();

        var availableMakes = carOptions.stream().map(CarInfo::getMake).distinct().toArray(String[]::new);
        String make = presentOptions("We have the following makes available:", availableMakes, scanner);
        carOptions = carOptions.stream().filter(car -> car.getMake().equals(make)).collect(Collectors.toSet());

        var availableModels = carOptions.stream().map(CarInfo::getModel).distinct().toArray(String[]::new);
        String model = presentOptions("We have the following models available:", availableModels, scanner);
        carOptions = carOptions.stream().filter(car -> car.getModel().equals(model)).collect(Collectors.toSet());

        var availableColors = carOptions.stream().map(CarInfo::getColor).distinct().toArray(String[]::new);
        String color = presentOptions("We have the following colors available:", availableColors, scanner);
        carOptions = carOptions.stream().filter(car -> car.getColor().equals(color)).collect(Collectors.toSet());

        var availableYears = carOptions.stream().map(CarInfo::getYear).map(String::valueOf).distinct().toArray(String[]::new);
        int year = Integer.parseInt(presentOptions("We have the following years available:", availableYears, scanner));

        return new CarInfo(make, model, year, color);
    }

    private static String presentOptions(String prompt, String[] options, Scanner scanner) {
        System.out.println(prompt);
        int choice = getChoice(options, scanner);
        return options[choice - 1];
    }

    public static int getChoice(String prompt, Scanner scanner, int max) {
        System.out.println(prompt);
        System.out.println("Enter the number of your choice:");

        int choice = scanner.nextInt();

        while (choice < 1 || choice > max) {
            System.out.println("Invalid choice. Please try again.");
            choice = scanner.nextInt();
        }

        System.out.println();

        return choice;
    }

    public static int getChoice(String[] options, Scanner scanner) {
        StringBuilder prompt = new StringBuilder();

        for (int i = 0; i < options.length; i++) {
            prompt.append(i + 1).append(". ").append(options[i]).append("\n");
        }

        return getChoice(prompt.toString(), scanner, options.length);
    }

    @SuppressWarnings("unused")
    public static void simulateDealership(String... args) {

        var numDeals = Integer.parseInt(args[0]);

        boolean isLuxury;

        if (args.length > 1) {
            isLuxury = "-luxury".equals(args[1]);
        } else {
            isLuxury = false;
        }

        ICarDealer carDealer;

        if (isLuxury) {
            carDealer = new LuxuryCarDealer();
        } else {
            carDealer = new StandardCarDealer();
        }

        for (int i = 0; i < numDeals; i++) {

            System.out.printf("Deal #%d: %n", i + 1);

            IBuyer buyer;

            if (isLuxury) {
                buyer = RandomBuyerGenerator.generateLuxuryBuyer();
            } else {
                buyer = RandomBuyerGenerator.generateStandardBuyer();
            }

            var carInfo = buyer.getWantedCar();

            String buyerName = buyer.getName();

            System.out.printf("The buyer %s wants a %s.%n", buyerName, carInfo.printableString());

            if (!carDealer.hasCar(carInfo)) {

                System.out.printf("The dealership does not have a %s.%n%n", carInfo.printableString());

                continue;

            }

            int price = carDealer.getPrice(carInfo);

            System.out.printf("The dealership has a %s for $%d.%n", carInfo.printableString(), price);

            carDealer.sellCar(buyerName, carInfo);

            System.out.printf("The dealership sold a %s to %s for $%d.%n", carInfo.printableString(), buyerName, price);

            System.out.printf("The dealership also offers an extended warranty for $%d.%n", carDealer.getWarrantyPrice());

            if (RANDOM_GEN.nextBoolean()) {

                carDealer.sellWarranty(buyer);

                System.out.printf("The dealership sold an extended warranty to %s for $%d.%n", buyerName, carDealer.getWarrantyPrice());

            } else {

                System.out.printf("%s did not want an extended warranty.%n", buyerName);
            }

            System.out.println();
        }

        System.out.println();

        carDealer.printTransactions();

        System.out.printf("Total sales: $%d.%n", carDealer.getTotalSales());

        System.out.println();

    }
}