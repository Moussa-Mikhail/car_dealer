package cardealer.cardealer;

import cardealer.CarInfo;
import cardealer.GetRandom;
import cardealer.Models;
import cardealer.TransactionLog;
import cardealer.buyer.Buyer;
import cardealer.transaction.CarTransaction;
import cardealer.transaction.Transaction;
import cardealer.transaction.WarrantyTransaction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Moussa
 */
public class CarDealer {

    public static final String WARRANTY_INFO = "Extended Warranty";
    private static final int INITIAL_NUM_CARS = 600;
    private static final int MIN_PRICE_IN_THOUSANDS = 40;
    private static final int PRICE_RANGE_IN_THOUSANDS = 30;
    private static final int WARRANTY_PRICE = 2000;
    private static final Models MODELS = Models.standardModels();
    private final Map<CarInfo, Integer> carInfoToNumber = new HashMap<>();
    private final Map<CarInfo, Integer> carInfoToPrice = new HashMap<>();
    private final TransactionLog transactions = new TransactionLog();
    private int numCarsSold = 0;
    private int totalSales = 0;

    public CarDealer() {
        populateInventory(INITIAL_NUM_CARS, MODELS);
        setPrices(MIN_PRICE_IN_THOUSANDS, PRICE_RANGE_IN_THOUSANDS);
    }

    @Override
    public String toString() {
        return "CarDealer{" +
                "carInfoToNumber=" + carInfoToNumber +
                ", carInfoToPrice=" + carInfoToPrice +
                ", transactions=" + transactions +
                ", numCarsSold=" + numCarsSold +
                ", totalSales=" + totalSales +
                '}';
    }

    protected void populateInventory(int initialNumCars, Models models) {

        carInfoToNumber.clear();

        for (int i = 0; i < initialNumCars; i++) {
            final var carInfo = CarInfo.generateRandomCarInfo(models);
            addCar(carInfo);
        }
    }

    protected void setPrices(int minPriceInThousands, int priceRangeInThousands) {

        carInfoToPrice.clear();

        for (var carInfo : carInfoToNumber.keySet()) {
            var priceInThousands = GetRandom.RANDOM_GEN.nextInt(priceRangeInThousands) + minPriceInThousands;
            var price = priceInThousands * 1000;
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

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasCar(CarInfo carInfo) {
        return getQuantity(carInfo) > 0;
    }

    public int getPrice(CarInfo carInfo) {
        return carInfoToPrice.getOrDefault(carInfo, 0);
    }

    public void sellCar(String buyerName, CarInfo carInfo) {

        if (!hasCar(carInfo)) {
            return;
        }

        var price = getPrice(carInfo);
        var transaction = new CarTransaction(buyerName, carInfo, price);
        transactions.addTransaction(transaction);

        removeCar(carInfo);

        totalSales += price;
        numCarsSold++;
    }

    public void sellWarranty(Buyer buyer) {
        sellWarranty(buyer, WARRANTY_PRICE);
    }

    protected void sellWarranty(Buyer buyer, int price) {
        var transaction = new WarrantyTransaction(buyer.getName(), price);
        transactions.addTransaction(transaction);
        totalSales += price;
    }

    protected void removeCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, -1, Integer::sum);
    }

    public void printTransactions() {
        transactions.printTransactions();
    }

    public int getWarrantyPrice() {
        return WARRANTY_PRICE;
    }
}
