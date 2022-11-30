package cardealer.cardealer;

import cardealer.buyer.IBuyer;
import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public interface ISellsWarranty {
    int WARRANTY_PRICE = 2000;

    /**
     * @param buyer   the buyer
     * @param carInfo the car info
     */
    void sellWarranty(IBuyer buyer, CarInfo carInfo);

    /**
     * @param carInfo the car info
     * @return the price of an extended warranty.
     */
    default int calcWarrantyPrice(@SuppressWarnings("unused") CarInfo carInfo) {
        return WARRANTY_PRICE;
    }
}
