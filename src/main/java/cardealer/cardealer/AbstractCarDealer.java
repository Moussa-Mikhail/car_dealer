package cardealer.cardealer;

import cardealer.TransactionLog;
import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;
import cardealer.transaction.CarTransaction;
import cardealer.utils.GetRandom;
import cardealer.utils.ModelsDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Moussa
 */
public abstract class AbstractCarDealer implements ISellsCars {
    protected static final int INITIAL_NUM_CARS = 400;
    protected static final int MIN_PRICE_IN_THOUSANDS = 40;
    protected static final int PRICE_RANGE_IN_THOUSANDS = 30;
    private static final Logger LOGGER = LogManager.getLogger();
    protected final Map<CarInfo, Integer> carInfoToNumber = new HashMap<>();
    protected final Map<CarInfo, Integer> carInfoToPrice = new HashMap<>();
    protected final TransactionLog transactions = new TransactionLog();
    protected int numCarsSold = 0;
    protected int totalSales = 0;

    protected AbstractCarDealer() {
        LOGGER.info("Abstract Car dealer created.");
    }

    protected void populateInventory(int initialNumCars, ModelsDataProvider modelsDataProvider, int minYear, int yearRange) {
        for (int i = 0; i < initialNumCars; i++) {
            CarInfo carInfo = CarInfo.generateRandomCarInfo(modelsDataProvider, minYear, yearRange);
            addCar(carInfo);
        }
        LOGGER.info("Inventory populated with {} cars.", initialNumCars);
    }

    protected void addCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, 1, Integer::sum);
        LOGGER.trace("1 {} has been added to the inventory.", carInfo);
    }

    protected void setPrices(int minPriceInThousands, int priceRangeInThousands) {
        for (CarInfo carInfo : carInfoToNumber.keySet()) {
            int priceInThousands = GetRandom.RANDOM_GEN.nextInt(priceRangeInThousands) + minPriceInThousands;
            int price = priceInThousands * 1000;
            carInfoToPrice.put(carInfo, price);
            LOGGER.trace("Price of {} is set to {}.", carInfo, price);
        }
    }

    @Override
    public Set<CarInfo> getAvailableCars() {
        return new HashSet<>(carInfoToNumber.keySet());
    }

    @Override
    public int getTotalSales() {
        return totalSales;
    }

    @Override
    public void sellCar(IBuyer buyer) {
        CarInfo carInfo = buyer.getWantedCar();
        if (!hasCar(carInfo)) {
            return;
        }
        int price = getPrice(carInfo);
        String name = buyer.getName();
        CarTransaction transaction = new CarTransaction(name, carInfo, price);
        removeCar(carInfo);
        transactions.addTransaction(transaction);
        totalSales += price;
        numCarsSold++;
        LOGGER.info("{} bought a {} for ${}", name, carInfo, price);
    }

    protected void removeCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, -1, Integer::sum);
        if (carInfoToNumber.get(carInfo) == 0) {
            carInfoToNumber.remove(carInfo);
        }
        LOGGER.info("1 {} has been removed from the inventory.", carInfo);
    }

    @Override
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public boolean hasCar(CarInfo carInfo) {
        return getQuantity(carInfo) > 0;
    }

    public int getQuantity(CarInfo carInfo) {
        return carInfoToNumber.getOrDefault(carInfo, 0);
    }

    @Override
    public int getPrice(CarInfo carInfo) {
        int price = carInfoToPrice.getOrDefault(carInfo, -1);
        LOGGER.info("Price of {} is ${}", carInfo, price);
        return price;
    }

    @Override
    public void printTransactions() {
        transactions.printTransactions();
    }
}
