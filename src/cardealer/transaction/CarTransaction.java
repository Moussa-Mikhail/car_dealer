package cardealer.transaction;

import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public final class CarTransaction extends StandardTransaction {
    public CarTransaction(String buyerName, CarInfo carInfo, int price) {
        super(buyerName, carInfo.toString(), price);
    }

    @Override
    public String toString() {
        return "CarTransaction{} " + super.toString();
    }
}
