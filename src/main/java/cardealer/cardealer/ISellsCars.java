package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;

import java.util.Set;

/**
 * @author Moussa
 */
public interface ISellsCars {
    /**
     * Checks if the cardealer has the input car
     *
     * @param carInfo the car info
     * @return true if the car is available, false otherwise.
     */
    @SuppressWarnings({"BooleanMethodIsAlwaysInverted", "unused"})
    boolean hasCar(CarInfo carInfo);

    /**
     * If the car is not available then it returns -1.
     *
     * @param carInfo the car info
     * @return the price of the car
     */
    int getPrice(CarInfo carInfo);

    /**
     * Sells the car to buyer
     *
     * @param buyer the buyer
     */
    void sellCar(IBuyer buyer);

    /**
     * Prints all transactions in a formatted table.
     */
    void printTransactions();

    /**
     * Gets the total sales.
     *
     * @return the total sales.
     */
    int getTotalSales();

    /**
     * Returns the set of available cars.
     *
     * @return the set of available cars.
     */
    Set<CarInfo> getAvailableCars();
}
