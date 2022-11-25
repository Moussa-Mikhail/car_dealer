package cardealer.transaction;

import cardealer.carinfo.CarInfo;

/**
 * @author Moussa
 */
public class CarTransaction extends StandardTransaction {

    public CarTransaction(String buyerName, CarInfo carInfo, int price) {
        super(buyerName, carInfo.printableString(), price);
    }

    @Override
    public String toString() {
        return "CarTransaction{} " + super.toString();
    }
}
