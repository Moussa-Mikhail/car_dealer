package cardealer.buyer;

import cardealer.carinfo.CarInfo;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * @author Moussa
 */
abstract class AbstractBuyer implements IBuyer {
    protected final String name;
    protected final CarInfo wantedCar;

    protected AbstractBuyer(String name, CarInfo wantedCar) {
        this.name = name;
        this.wantedCar = wantedCar;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public CarInfo getWantedCar() {
        return wantedCar;
    }

    @Override
    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        AbstractBuyer that = (AbstractBuyer) other;
        if (!name.equals(that.name)) {
            return false;
        }
        return wantedCar.equals(that.wantedCar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, wantedCar);
    }
}
