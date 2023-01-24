package cardealer.library.models;

/**
 * @author Moussa
 */
public class FeeType implements IdGettable {
    private long id;
    private String description;

    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
