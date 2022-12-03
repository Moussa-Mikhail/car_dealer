package cardealer.buyer;

import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public interface IBuyer {
    /**
     * the name of the buyer
     *
     * @return the name of the buyer
     */
    String getName();

    /**
     * the car the buyer wants.
     *
     * @return the car info
     */
    CarInfo getWantedCar();
}
