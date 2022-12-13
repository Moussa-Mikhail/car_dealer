package cardealer.transaction;

import cardealer.cardealer.AbstractCarAndWarrantyDealer;
import org.jetbrains.annotations.NotNull;

/**
 * @author Moussa
 */
public final class WarrantyTransaction extends StandardTransaction {
    public WarrantyTransaction(String buyerName, int price) {
        super(buyerName, AbstractCarAndWarrantyDealer.getWarrantyDescription(), price);
    }

    @Override
    public @NotNull String toString() {
        return "WarrantyTransaction{} " + super.toString();
    }
}
