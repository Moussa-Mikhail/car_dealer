package cardealer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moussa
 */
public class CarDealer {

    public static final int INITIAL_NUM_CARS = 300;
    public static final int WARRANTY_PRICE = 2000;
    private final Map<CarInfo, Integer> carInfoToNumber = new HashMap<>();
    private final Map<CarInfo, Integer> carInfoToPrice = new HashMap<>();
    private final Transactions transactions = new Transactions();
    private int numCarsSold = 0;
    private int totalSales = 0;

    public CarDealer() {

        populateInventory();

        setPrices();
    }

    private void populateInventory() {

        for (int i = 0; i < INITIAL_NUM_CARS; i++) {

            final var carInfo = CarInfo.generateRandomCarInfo();

            addCar(carInfo);
        }
    }

    private void setPrices() {

        for (final var carInfo : carInfoToNumber.keySet()) {

            final var priceInThousands = GetRandom.RANDOM_GEN.nextInt(30) + 40;

            final var price = priceInThousands * 1000;

            carInfoToPrice.put(carInfo, price);
        }
    }

    public int getNumCarsSold() {
        return numCarsSold;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public List<Transaction> getTransactions() {
        return transactions.getTransactionsList();
    }

    public void addCar(CarInfo carInfo) {

        carInfoToNumber.merge(carInfo, 1, Integer::sum);
    }

    public int getQuantity(CarInfo carInfo) {
        return carInfoToNumber.getOrDefault(carInfo, 0);
    }

    public boolean hasCar(CarInfo carInfo) {
        return getQuantity(carInfo) > 0;
    }

    public int getPrice(CarInfo carInfo) {
        return carInfoToPrice.getOrDefault(carInfo, 0);
    }

    public void sellCarTo(String buyerName, CarInfo carInfo) {

        if (!hasCar(carInfo)) {

            return;
        }

        final var price = getPrice(carInfo);

        final var transaction = new Transaction(buyerName, carInfo.toString(), price);

        transactions.addTransaction(transaction);

        removeCar(carInfo);

        totalSales += price;

        numCarsSold++;
    }

    public void sellWarranty(Buyer buyer) {

        final var transaction = new Transaction(buyer.getName(), "Extended Warranty", WARRANTY_PRICE);

        transactions.addTransaction(transaction);

        totalSales += WARRANTY_PRICE;
    }

    private void removeCar(CarInfo carInfo) {

        carInfoToNumber.merge(carInfo, -1, Integer::sum);
    }

    public void printTransactions() {

        transactions.printTransactions();
    }
}
