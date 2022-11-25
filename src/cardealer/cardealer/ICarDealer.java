package cardealer.cardealer;

import cardealer.carinfo.CarInfo;
import cardealer.buyer.IBuyer;

import java.util.Set;

/**
 * @author Moussa
 */
public interface ICarDealer {
    /**
     * @param carInfo the car info
     * @return true if the car is available, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean hasCar(CarInfo carInfo);

    /**
     * If the car is not available then it returns -1.
     *
     * @param carInfo the car info
     * @return the price of the car
     */
    int getPrice(CarInfo carInfo);

    /**
     * @param buyerName the name of the buyer
     * @param carInfo   the car info
     */
    void sellCar(String buyerName, CarInfo carInfo);

    /**
     * sells car to buyer
     * @param buyer the buyer
     */
    void sellCar(IBuyer buyer);

    /**
     * @param buyer the buyer
     */
    void sellWarranty(IBuyer buyer);

    /**
     * prints all transactions.
     */
    void printTransactions();

    /**
     * @return the price of an extended warranty.
     */
    int getWarrantyPrice();

    /**
     * @return the total sales.
     */
    int getTotalSales();

    /**
     * @return returns the set of available makes.
     */
    Set<CarInfo> getAvailableCars();


}