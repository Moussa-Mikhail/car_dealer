package cardealer.transaction;

import cardealer.carinfo.CarInfo;
import org.jetbrains.annotations.NotNull;

/**
 * @author Moussa
 */
public final class CarTransaction extends StandardTransaction {
    public CarTransaction(String buyerName, @NotNull CarInfo carInfo, int price) {
        super(buyerName, carInfo.toString(), price);
    }

    @Override
    public @NotNull String toString() {
        return "CarTransaction{} " + super.toString();
    }
}
