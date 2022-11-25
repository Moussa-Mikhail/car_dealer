package cardealer.cardealer;

import cardealer.carinfo.CarInfo;
import cardealer.GetRandom;
import cardealer.MakesAndModels;
import cardealer.TransactionLog;
import cardealer.buyer.Buyer;
import cardealer.transaction.CarTransaction;
import cardealer.transaction.WarrantyTransaction;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Moussa
 */
public class AbstractCarDealer implements CarDealer {
    public static final String WARRANTY_INFO = "Extended Warranty";
    protected static final int INITIAL_NUM_CARS = 600;
    protected static final int MIN_PRICE_IN_THOUSANDS = 40;
    protected static final int PRICE_RANGE_IN_THOUSANDS = 30;
    private static final int WARRANTY_PRICE = 2000;
    protected final Map<CarInfo, Integer> carInfoToNumber = new HashMap<>();
    protected final Map<CarInfo, Integer> carInfoToPrice = new HashMap<>();
    protected final TransactionLog transactions = new TransactionLog();
    protected int numCarsSold = 0;
    protected int totalSales = 0;

    protected void populateInventory(int initialNumCars, MakesAndModels makesAndModels) {

        carInfoToNumber.clear();

        for (int i = 0; i < initialNumCars; i++) {
            final var carInfo = CarInfo.generateRandomCarInfo(makesAndModels);
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

    @Override
    public int getTotalSales() {
        return totalSales;
    }

    private void addCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, 1, Integer::sum);
    }

    public int getQuantity(CarInfo carInfo) {
        return carInfoToNumber.getOrDefault(carInfo, 0);
    }

    @Override
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasCar(CarInfo carInfo) {
        return getQuantity(carInfo) > 0;
    }

    @Override
    public int getPrice(CarInfo carInfo) {
        return carInfoToPrice.getOrDefault(carInfo, 0);
    }

    @Override
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

    @Override
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

    @Override
    public void printTransactions() {
        transactions.printTransactions();
    }

    @Override
    public int getWarrantyPrice() {
        return WARRANTY_PRICE;
    }
}
