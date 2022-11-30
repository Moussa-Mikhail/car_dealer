package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;

import java.util.Set;

/**
 * @author Moussa
 */
public interface ISellsCars {
    /**
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
     * sells the car to buyer
     *
     * @param buyer the buyer
     */
    void sellCar(IBuyer buyer);

    /**
     * prints all transactions.
     */
    void printTransactions();

    /**
     * @return the total sales.
     */
    int getTotalSales();

    /**
     * @return returns the set of available makes.
     */
    Set<CarInfo> getAvailableCars();


}
