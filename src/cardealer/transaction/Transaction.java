package cardealer.transaction;

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
        return "Transaction{" +
                "buyerName='" + buyerName + '\'' +
                ", info='" + info + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Transaction that = (Transaction) o;

        if (getPrice() != that.getPrice()) {
            return false;
        }

        if (!getBuyerName().equals(that.getBuyerName())) {
            return false;
        }

        return getInfo().equals(that.getInfo());
    }

    @Override
    public int hashCode() {

        int result = getBuyerName().hashCode();

        result = 31 * result + getInfo().hashCode();

        result = 31 * result + getPrice();
        return result;
    }
}
