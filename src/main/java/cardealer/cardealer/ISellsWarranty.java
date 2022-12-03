package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public interface ISellsWarranty {
    int WARRANTY_PRICE = 2000;

    /**
     * Sells a warranty to a buyer.
     *
     * @param buyer   the buyer
     * @param carInfo the car info
     */
    void sellWarranty(IBuyer buyer, CarInfo carInfo);

    /**
     * Returns the price of the warranty based on the car.
     *
     * @param carInfo the car info
     * @return the price of an extended warranty.
     */
    @SuppressWarnings("unused")
    default int calcWarrantyPrice(CarInfo carInfo) {
        return WARRANTY_PRICE;
    }
}
