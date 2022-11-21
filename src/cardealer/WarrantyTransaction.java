package cardealer;

import static cardealer.CarDealer.WARRANTY_INFO;
import static cardealer.CarDealer.WARRANTY_PRICE;

/**
 * @author Moussa
 */
public class WarrantyTransaction extends Transaction {

    public WarrantyTransaction(String buyerName) {
        super(buyerName, WARRANTY_INFO, WARRANTY_PRICE);
    }

    @Override
    public String toString() {
        return "WarrantyTransaction{} " + super.toString();
    }
}
