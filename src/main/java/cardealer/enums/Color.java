package cardealer.enums;

import org.jetbrains.annotations.NotNull;

/**
 * @author Moussa
 */
public enum Color {
    /**
     * Black
     */
    BLACK("Black"),
    /**
     * Blue
     */
    BLUE("Blue"),
    /**
     * Red
     */
    RED("Red"),
    /**
     * Silver
     */
    SILVER("Silver"),
    /**
     * White
     */
    WHITE("White");
    private String colorName;

    Color(String colorName) {
        this.colorName = colorName;
    }

    @Override
    public @NotNull String toString() {
        return colorName;
    }
}
