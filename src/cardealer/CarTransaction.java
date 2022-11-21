package cardealer;

/**
 * @author Moussa
 */
public class CarTransaction extends Transaction{

    public CarTransaction(String buyerName, CarInfo carInfo, int price) {

        super(buyerName, carInfo.toString(), price);
    }

    @Override
    public String toString() {
        return "CarTransaction{} " + super.toString();
    }
}
