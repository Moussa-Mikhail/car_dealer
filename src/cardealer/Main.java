package cardealer;

import cardealer.buyer.*;
import cardealer.cardealer.ICarDealer;
import cardealer.cardealer.LuxuryCarDealer;
import cardealer.cardealer.StandardCarDealer;
import cardealer.carinfo.CarInfo;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

import static cardealer.GetRandom.RANDOM_GEN;

/**
 * @author Moussa
 */
public class Main {

    public static final String YES_OR_NO = "1. Yes\n2. No";

    public static void main(String... args) {

        boolean keepGoing;

        do {
            System.out.println("Welcome to the car dealership.");

            Scanner scanner = new Scanner(System.in);
            System.out.println("What is your name?");
            String name = scanner.nextLine();

            System.out.println("We have two types of cars: standard and luxury.");
            System.out.println("What kind of car are you looking for?");
            int choice = getChoice("1. Standard\n2. Luxury", 2);

            boolean isLuxury = choice == 2;
            ICarDealer carDealer = isLuxury ? new LuxuryCarDealer() : new StandardCarDealer();
            CarInfo carInfo = getSelection(carDealer);
            System.out.printf("You have selected a %s.%n", carInfo);

            int price = carDealer.getPrice(carInfo);
            System.out.printf("The price of this car is $%d.%n", price);
            System.out.println("Would you like to buy this car?");
            choice = getChoice(YES_OR_NO, 2);

            if (choice == 1) {
                IBuyer buyer = isLuxury ? new LuxuryBuyer(name, carInfo) : new StandardBuyer(name, carInfo);
                carDealer.sellCar(buyer);
                System.out.printf("Congratulations %s, you have bought a %s for $%d.%n", name, carInfo, price);

                System.out.printf("Would you like to buy an extended warranty for $%d?%n", carDealer.getWarrantyPrice());
                choice = getChoice(YES_OR_NO,2);

                if (choice == 1) {
                    carDealer.sellWarranty(buyer);
                    System.out.printf("Congratulations %s, you have bought an extended warranty for $%d.%n", name, carDealer.getWarrantyPrice());
                }
            }

            System.out.println("Would you like to select another car?");
            choice = getChoice(YES_OR_NO, 2);
            keepGoing = choice == 1;

            if (!keepGoing) {
                carDealer.printTransactions();
            }

        } while (keepGoing);


    }

    private static CarInfo getSelection(ICarDealer carDealer) {
        var carOptions = carDealer.getAvailableCars();

        getCarChoice("make", CarInfo::getMake, carOptions);

        getCarChoice("model", CarInfo::getModel, carOptions);

        getCarChoice("color", CarInfo::getColor, carOptions);

        getCarChoice("year", CarInfo::getYear, carOptions);

        assert carOptions.size() == 1;
        return carOptions.iterator().next();
    }

    private static void getCarChoice(String attribute, Function<CarInfo, Object> getAttribute, Set<CarInfo> carOptions) {
        
        String prompt = String.format("We have the following %ss available:", attribute);
        
        var availableAttributes = carOptions.stream().map(getAttribute).distinct().toArray(Object[]::new);
        String chosenAttribute = presentOptions(prompt, availableAttributes);
        carOptions.removeIf(carInfo -> !getAttribute.apply(carInfo).toString().equals(chosenAttribute));
    }

    private static String presentOptions(String prompt, Object[] options) {
        System.out.println(prompt);
        int choice = getChoice(options);
        return options[choice - 1].toString();
    }

    public static int getChoice(String prompt, int maxChoice) {
        System.out.println(prompt);
        System.out.println("Enter the number of your choice:");

        Scanner scanner = new Scanner(System.in);

        int choice = scanner.nextInt();

        while (choice < 1 || choice > maxChoice) {
            System.out.println("Invalid choice. Please try again.");
            choice = scanner.nextInt();
        }

        System.out.println();

        return choice;
    }

    public static int getChoice(Object[] options) {
        StringBuilder prompt = new StringBuilder();

        for (int i = 0; i < options.length; i++) {
            prompt.append(i + 1).append(". ").append(options[i]).append("\n");
        }

        return getChoice(prompt.toString(), options.length);
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

            System.out.printf("The buyer %s wants a %s.%n", buyerName, carInfo);

            if (!carDealer.hasCar(carInfo)) {

                System.out.printf("The dealership does not have a %s.%n%n", carInfo);

                continue;

            }

            int price = carDealer.getPrice(carInfo);

            System.out.printf("The dealership has a %s for $%d.%n", carInfo, price);

            carDealer.sellCar(buyerName, carInfo);

            System.out.printf("The dealership sold a %s to %s for $%d.%n", carInfo, buyerName, price);

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