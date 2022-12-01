package cardealer;

import cardealer.buyer.IBuyer;
import cardealer.buyer.LuxuryBuyer;
import cardealer.buyer.StandardBuyer;
import cardealer.cardealer.ISellsCars;
import cardealer.cardealer.ISellsWarranty;
import cardealer.cardealer.LuxuryCarAndWarrantyDealer;
import cardealer.cardealer.StandardCarAndWarrantyDealer;
import cardealer.carinfo.CarInfo;
import cardealer.utils.PromptUser;

import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

/**
 * @author Moussa
 */
public class Main {
    public static final ISellsCars LUXURY_CAR_DEALER = new LuxuryCarAndWarrantyDealer();
    public static final ISellsCars STANDARD_CAR_DEALER = new StandardCarAndWarrantyDealer();
    public static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String... args) {
        boolean keepGoing;
        do {
            String name = getName();
            System.out.printf("Hello %s!%n%n", name);

            String[] options = {"Standard", "Luxury"};
            String choice = PromptUser.getChoice("What kind of car are you looking for?", options);
            boolean isLuxury = "Luxury".equals(choice);
            final ISellsCars carDealer = isLuxury ? LUXURY_CAR_DEALER : STANDARD_CAR_DEALER;

            CarInfo carInfo = getCarSelection(carDealer);
            System.out.printf("You have selected a %s.%n", carInfo);

            int price = carDealer.getPrice(carInfo);
            System.out.printf("The price of this car is $%d.%n", price);
            boolean wantsToBuy = PromptUser.yesOrNoPrompt("Would you like to buy this car?");

            if (wantsToBuy) {
                IBuyer buyer = isLuxury ? new LuxuryBuyer(name, carInfo) : new StandardBuyer(name, carInfo);
                carDealer.sellCar(buyer);
                System.out.printf("Congratulations %s, you have bought a %s for $%d.%n", name, carInfo, price);

                if (carDealer instanceof ISellsWarranty) {
                    ISellsWarranty warrantyDealer = (ISellsWarranty) carDealer;
                    offerWarranty(warrantyDealer, buyer);
                }
            }
            keepGoing = PromptUser.yesOrNoPrompt("Would you like to buy another car?");
        } while (keepGoing);

        System.out.println("Standard Car Dealer Transactions:");
        STANDARD_CAR_DEALER.printTransactions();
        System.out.printf("Standard Car Dealer Total Sales: $%d%n%n", STANDARD_CAR_DEALER.getTotalSales());

        System.out.println("Luxury Car Dealer Transactions:");
        LUXURY_CAR_DEALER.printTransactions();
        System.out.printf("Luxury Car Dealer Total Sales: $%d%n", LUXURY_CAR_DEALER.getTotalSales());
    }

    private static String getName() {
        System.out.println("What is your name?");
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    private static void offerWarranty(ISellsWarranty carDealer, IBuyer buyer) {
        CarInfo carInfo = buyer.getWantedCar();
        String name = buyer.getName();
        int warrantyPrice = carDealer.calcWarrantyPrice(carInfo);
        String prompt = String.format("Would you like to buy a warranty for $%d", warrantyPrice);
        boolean wantsToBuyWarranty = PromptUser.yesOrNoPrompt(prompt);
        if (wantsToBuyWarranty) {
            carDealer.sellWarranty(buyer, carInfo);
            System.out.printf("Congratulations %s, you have bought an extended warranty for $%d.%n", name, warrantyPrice);
        }
    }

    private static CarInfo getCarSelection(ISellsCars carDealer) {
        Set<CarInfo> carOptions = carDealer.getAvailableCars();
        getCarChoice("make", CarInfo::getMake, carOptions);
        getCarChoice("model", CarInfo::getModel, carOptions);
        getCarChoice("color", CarInfo::getColor, carOptions);
        getCarChoice("year", CarInfo::getYear, carOptions);
        assert carOptions.size() == 1;
        return carOptions.iterator().next();
    }

    private static void getCarChoice(String attribute, Function<CarInfo, Object> getAttribute, Set<CarInfo> carOptions) {
        String prompt = String.format("We have the following %ss available:", attribute);
        Object[] availableAttributes = carOptions.stream().map(getAttribute).distinct().toArray(Object[]::new);
        Object chosenAttribute = PromptUser.getChoice(prompt, availableAttributes);
        carOptions.removeIf(carInfo -> !getAttribute.apply(carInfo).equals(chosenAttribute));
    }
}
