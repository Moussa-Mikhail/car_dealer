package cardealer.transaction;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author Moussa
 */
public class StandardTransaction implements ITransaction {
    private final String buyerName;
    private final String description;
    private final int price;

    public StandardTransaction(String buyerName, String description, int price) {
        this.buyerName = buyerName;
        this.description = description;
        this.price = price;
    }

    @Override
    public @NotNull String toString() {
        return "StandardTransaction{" +
                "buyerName='" + buyerName + '\'' +
                ", info='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        StandardTransaction that = (StandardTransaction) other;
        if (price != that.price) {
            return false;
        }
        if (!buyerName.equals(that.buyerName)) {
            return false;
        }
        return description.equals(that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(buyerName, description, price);
    }

    @Override
    public String getBuyerName() {
        return buyerName;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getPrice() {
        return price;
    }
}
