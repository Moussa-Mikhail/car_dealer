package cardealer;

/**
 * @author Moussa
 */
public class Transaction {

    private final String buyerName;

    private final String info;

    private final int price;

    public Transaction(String buyerName, String info, int price) {
        this.buyerName = buyerName;
        this.info = info;
        this.price = price;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public String getInfo() {
        return info;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, $%d", buyerName, info, price);
    }
}
