package cardealer.transaction;

import java.util.Objects;

/**
 * @author Moussa
 */
public class StandardTransaction implements ITransaction {

    private final String buyerName;

    private final String info;

    private final int price;

    public StandardTransaction(String buyerName, String info, int price) {
        this.buyerName = buyerName;
        this.info = info;
        this.price = price;
    }

    @Override
    public String toString() {
        return "StandardTransaction{" +
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

        StandardTransaction that = (StandardTransaction) o;

        if (price != that.price) {
            return false;
        }
        if (!buyerName.equals(that.buyerName)) {
            return false;
        }
        return info.equals(that.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerName, info, price);
    }

    @Override
    public String getBuyerName() {
        return buyerName;
    }

    @Override
    public String getInfo() {
        return info;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
