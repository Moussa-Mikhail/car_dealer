package cardealer.utils;

import static java.util.Locale.ENGLISH;

/**
 * @author Moussa
 */
public enum Color {
    /**
     * Black
     */
    BLACK,
    /**
     * Blue
     */
    BLUE,
    /**
     * Red
     */
    RED,
    /**
     * Silver
     */
    SILVER,
    /**
     * White
     */
    WHITE;

    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase(ENGLISH);
    }
}
