package cardealer.transaction;

import static cardealer.cardealer.CarDealer.WARRANTY_INFO;

/**
 * @author Moussa
 */
public class WarrantyTransaction extends Transaction {

    public WarrantyTransaction(String buyerName, int price) {
        super(buyerName, WARRANTY_INFO, price);
    }

    @Override
    public String toString() {
        return "WarrantyTransaction{} " + super.toString();
    }
}
