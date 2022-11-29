package cardealer;

import cardealer.buyer.IBuyer;
import cardealer.buyer.LuxuryBuyer;
import cardealer.buyer.StandardBuyer;
import cardealer.cardealer.ISellsCars;
import cardealer.cardealer.ISellsWarranty;
import cardealer.cardealer.LuxuryCarAndWarrantyDealer;
import cardealer.cardealer.StandardCarAndWarrantyDealer;
import cardealer.carinfo.CarInfo;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Moussa
 */
public class Main {
    public static final ISellsCars LUXURY_CAR_DEALER = new LuxuryCarAndWarrantyDealer();
    public static final ISellsCars STANDARD_CAR_DEALER = new StandardCarAndWarrantyDealer();

    public static void main(String... args) {
        boolean keepGoing;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("What kind of car are you looking for?");
            int choice = getChoice("1. Standard\n2. Luxury", 2);
            boolean isLuxury = choice == 2;
            final ISellsCars carDealer = isLuxury ? LUXURY_CAR_DEALER : STANDARD_CAR_DEALER;

            System.out.println("What is your name?");
            String name = scanner.nextLine();

            CarInfo carInfo = getSelection(carDealer);
            System.out.printf("You have selected a %s.%n", carInfo);

            int price = carDealer.getPrice(carInfo);
            System.out.printf("The price of this car is $%d.%n", price);
            choice = getYesOrNo("Would you like to buy this car?");

            if (choice == 1) {
                IBuyer buyer = isLuxury ? new LuxuryBuyer(name, carInfo) : new StandardBuyer(name, carInfo);
                carDealer.sellCar(buyer);
                System.out.printf("Congratulations %s, you have bought a %s for $%d.%n", name, carInfo, price);

                if (carDealer instanceof ISellsWarranty) {

                    ISellsWarranty warrantyDealer = (ISellsWarranty) carDealer;

                    offerWarranty(warrantyDealer, buyer);
                }

            }

            choice = getYesOrNo("Would you like to select another car?");
            keepGoing = choice == 1;
        } while (keepGoing);

        System.out.println("Standard Car Dealer Transactions:");
        STANDARD_CAR_DEALER.printTransactions();
        System.out.printf("Standard Car Dealer Total Sales: $%d%n%n", STANDARD_CAR_DEALER.getTotalSales());

        System.out.println("Luxury Car Dealer Transactions:");
        LUXURY_CAR_DEALER.printTransactions();
        System.out.printf("Luxury Car Dealer Total Sales: $%d%n", LUXURY_CAR_DEALER.getTotalSales());
    }

    private static int getYesOrNo(String prompt) {
        System.out.println(prompt);
        return getChoice("1. Yes\n2. No", 2);
    }

    private static void offerWarranty(ISellsWarranty carDealer, IBuyer buyer) {
        CarInfo carInfo = buyer.getWantedCar();
        String name = buyer.getName();

        int choice;
        int warrantyPrice = carDealer.calcWarrantyPrice(carInfo);

        choice = getYesOrNo(String.format("Would you like to buy an extended warranty for $%d?%n", warrantyPrice));

        if (choice == 1) {
            carDealer.sellWarranty(buyer, carInfo);
            System.out.printf("Congratulations %s, you have bought an extended warranty for $%d.%n", name, warrantyPrice);
        }
    }

    private static CarInfo getSelection(ISellsCars carDealer) {
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
        Object chosenAttribute = presentOptions(prompt, availableAttributes);
        carOptions.removeIf(carInfo -> !getAttribute.apply(carInfo).equals(chosenAttribute));
    }

    private static Object presentOptions(String prompt, Object[] options) {
        System.out.println(prompt);
        int choice = getChoice(options);
        return options[choice - 1];
    }

    public static int getChoice(Object[] options) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < options.length; i++) {
            stringBuilder.append(i + 1).append(". ").append(options[i]).append("\n");
        }

        return getChoice(stringBuilder.toString(), options.length);
    }

    public static int getChoice(String optionStr, int maxChoice) {
        System.out.println(optionStr);
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
}