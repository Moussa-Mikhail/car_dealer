package cardealer.buyer;

import cardealer.carinfo.CarInfo;

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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        StandardBuyer that = (StandardBuyer) o;

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
