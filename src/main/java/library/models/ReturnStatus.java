package library.models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Moussa
 */
@XmlRootElement(name = "returnStatus")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "description"})
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
