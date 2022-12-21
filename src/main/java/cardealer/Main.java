package cardealer;

import cardealer.buyer.IBuyer;
import cardealer.buyer.LuxuryBuyer;
import cardealer.buyer.StandardBuyer;
import cardealer.cardealer.ISellsCars;
import cardealer.cardealer.ISellsWarranty;
import cardealer.carinfo.CarInfo;
import cardealer.enums.CarDealerType;
import cardealer.exceptions.MultipleCarOptionsRemainingException;
import cardealer.exceptions.NoCarOptionsRemainingException;
import cardealer.utils.PromptUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author Moussa
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final CarDealerType[] CARD_DEALER_TYPES = CarDealerType.values();

    public static void main(String... args) {
        try {
            boolean keepGoing;
            do {
                String name = PromptUser.getName();
                System.out.printf("Hello %s!%n%n", name);
                CarDealerType chosenDealerType = PromptUser.getChoice("What kind of car are you looking for?", CARD_DEALER_TYPES);
                LOGGER.info("{} chose: {}", name, chosenDealerType.getType());
                boolean isLuxury = "Luxury".equals(chosenDealerType.getType());
                final ISellsCars carDealer = chosenDealerType.getCarDealer();
                CarInfo carInfo = getCarSelection(carDealer);
                System.out.printf("You have selected a %s.%n", carInfo);
                LOGGER.info("{} selected a {}", name, carInfo);
                int price = carDealer.getPrice(carInfo);
                System.out.printf("The price of this car is $%d.%n", price);
                boolean wantsToBuy = PromptUser.yesOrNoPrompt("Would you like to buy this car?");
                LOGGER.info("User chose to buy the car: {}", wantsToBuy);

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
                LOGGER.info("User chose to keep going: {}", keepGoing);
            } while (keepGoing);

            for (CarDealerType carDealerType : CARD_DEALER_TYPES) {
                String type = carDealerType.getType();
                LOGGER.info("{} Car Dealer Transactions:", type);
                ISellsCars carDealer = carDealerType.getCarDealer();
                carDealer.printTransactions();
                LOGGER.info("{} Car Dealer Total Sales: ${}%n%n", type, carDealer.getTotalSales());
            }
        } catch (Exception e) {
            LOGGER.fatal("An uncaught exception occurred", e);
        }
    }

    private static void offerWarranty(@NotNull ISellsWarranty carDealer, @NotNull IBuyer buyer) {
        CarInfo carInfo = buyer.getWantedCar();
        String name = buyer.getName();
        int warrantyPrice = carDealer.calcWarrantyPrice(carInfo);
        String prompt = String.format("Would you like to buy a warranty for $%d", warrantyPrice);
        boolean wantsWarranty = PromptUser.yesOrNoPrompt(prompt);
        LOGGER.info("{} chose to buy a warranty: {}", name, wantsWarranty);
        if (wantsWarranty) {
            carDealer.sellWarranty(buyer, carInfo);
            System.out.printf("Congratulations %s, you have bought an extended warranty for $%d.%n", name, warrantyPrice);
        }
    }

    /**
     * @param carDealer the car dealer to get the car selection from.
     * @return the carInfo with the attributes the user selected.
     * @throws MultipleCarOptionsRemainingException if the selection process results in multiple options remaining.
     * @throws NoCarOptionsRemainingException       if the selection process results in no options remaining.
     */
    private static CarInfo getCarSelection(@NotNull ISellsCars carDealer) {
        Set<CarInfo> cars = carDealer.getAvailableCars();
        filterCarsByAttribute("make", CarInfo::getMake, cars);
        filterCarsByAttribute("model", CarInfo::getModel, cars);
        filterCarsByAttribute("color", CarInfo::getColor, cars);
        filterCarsByAttribute("year", CarInfo::getYear, cars);

        if (cars.isEmpty()) {
            throw new NoCarOptionsRemainingException("No car options remaining.");
        } else if (cars.size() > 1) {
            throw new MultipleCarOptionsRemainingException("Multiple car options remaining. There should only be one option remaining.");
        }
        return cars.iterator().next();
    }

    /**
     * @param attribute    the attribute to ask the user about.
     * @param getAttribute the getter for the attribute.
     * @param cars         the set of cars to filter.
     */
    private static <T> void filterCarsByAttribute(String attribute, @NotNull Function<CarInfo, T> getAttribute, @NotNull Set<CarInfo> cars) {
        String prompt = String.format("We have the following %ss available:", attribute);
        List<T> availableAttributes = cars.stream().map(getAttribute).distinct()
                .sorted(Comparator.comparing(Object::toString)).collect(Collectors.toList());

        T chosenAttribute;
        try {
            chosenAttribute = PromptUser.getChoice(prompt, availableAttributes);
        } catch (IllegalArgumentException e) {
            throw new NoCarOptionsRemainingException("No car options remaining.", e);
        }
        cars.removeIf(carInfo -> !getAttribute.apply(carInfo).equals(chosenAttribute));
    }
}
