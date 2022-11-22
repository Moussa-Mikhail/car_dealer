package cardealer;

import static cardealer.GetRandom.RANDOM_GEN;

/**
 * @author Moussa
 */
public class Main {

    public static void main(String... args) {

        final var numDeals = Integer.parseInt(args[0]);

        final CarDealer carDealer = new LuxuryCarDealer();

        for (int i = 0; i < numDeals; i++) {

            System.out.printf("Deal #%d: %n", i + 1);

            final Buyer buyer = LuxuryBuyer.generateRandomBuyer();

            final var carInfo = buyer.getWantedCar();

            final String buyerName = buyer.getName();

            System.out.printf("The buyer %s wants a %s.%n", buyerName, carInfo.printableString());

            if (!carDealer.hasCar(carInfo)) {

                System.out.printf("The dealership does not have a %s.%n%n", carInfo.printableString());

                continue;

            }

            final var price = carDealer.getPrice(carInfo);

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