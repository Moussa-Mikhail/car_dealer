package cardealer.enums;

/**
 * @author Moussa
 */
@SuppressWarnings("NonFinalFieldInEnum")
public enum Make {
    AUDI("Audi"),
    BMW("BMW"),
    MERCEDES("Mercedes");
    private String makeName;

    Make(String makeName) {
        this.makeName = makeName;
    }

    @Override
    public String toString() {
        return makeName;
    }
}
