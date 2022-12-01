package cardealer.cardealer;

import cardealer.utils.GetRandom;
import cardealer.utils.ModelsDataProvider;
import cardealer.TransactionLog;
import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;
import cardealer.transaction.CarTransaction;

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
    protected final Map<CarInfo, Integer> carInfoToNumber = new HashMap<>();
    protected final Map<CarInfo, Integer> carInfoToPrice = new HashMap<>();
    protected final TransactionLog transactions = new TransactionLog();
    protected int numCarsSold = 0;
    protected int totalSales = 0;

    protected void populateInventory(int initialNumCars, ModelsDataProvider modelsDataProvider, int minYear, int yearRange) {
        for (int i = 0; i < initialNumCars; i++) {
            CarInfo carInfo = CarInfo.generateRandomCarInfo(modelsDataProvider, minYear, yearRange);
            addCar(carInfo);
        }
    }

    protected void addCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, 1, Integer::sum);
    }

    protected void setPrices(int minPriceInThousands, int priceRangeInThousands) {
        for (CarInfo carInfo : carInfoToNumber.keySet()) {
            int priceInThousands = GetRandom.RANDOM_GEN.nextInt(priceRangeInThousands) + minPriceInThousands;
            int price = priceInThousands * 1000;
            carInfoToPrice.put(carInfo, price);
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
        CarTransaction transaction = new CarTransaction(buyer.getName(), carInfo, price);
        transactions.addTransaction(transaction);
        removeCar(carInfo);
        totalSales += price;
        numCarsSold++;
    }

    protected void removeCar(CarInfo carInfo) {
        carInfoToNumber.merge(carInfo, -1, Integer::sum);

        if (carInfoToNumber.get(carInfo) == 0) {
            carInfoToNumber.remove(carInfo);
        }
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
        return carInfoToPrice.getOrDefault(carInfo, -1);
    }

    @Override
    public void printTransactions() {
        transactions.printTransactions();
    }
}
