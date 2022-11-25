package cardealer.buyer;

import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public interface Buyer {
    /**
     * @return the name of the buyer
     */
    String getName();

    /**
     * @return the car info
     */
    CarInfo getWantedCar();
}
