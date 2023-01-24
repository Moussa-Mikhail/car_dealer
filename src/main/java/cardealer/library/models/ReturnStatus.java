package cardealer.library.models;

import javax.xml.bind.annotation.XmlElement;

/**
 * @author Moussa
 */
public class ReturnStatus implements IdGettable {
    private long id;
    private String description;

    @Override
    public String toString() {
        return "ReturnStatus{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }

    @XmlElement(name = "id")
    @Override
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
