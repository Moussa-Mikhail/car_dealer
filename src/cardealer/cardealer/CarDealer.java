package cardealer.cardealer;

import cardealer.carinfo.CarInfo;
import cardealer.buyer.Buyer;

/**
 * @author Moussa
 */
public interface CarDealer {
    /**
     * @param carInfo the car info
     * @return true if the car is available, false otherwise.
     */
    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    boolean hasCar(CarInfo carInfo);

    /**
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
     * @param buyer the buyer
     */
    void sellWarranty(Buyer buyer);

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
}
