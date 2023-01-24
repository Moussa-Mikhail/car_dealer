package cardealer.library.models;

import javax.xml.bind.annotation.*;

/**
 * @author Moussa
 */
@XmlRootElement(name = "book", namespace = "library.xsd")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = {"id", "title", "authorId", "genreId", "isbn", "quantity"})
public class Book implements IdGettable {
    private long id;
    private String title;
    private long authorId;
    private long genreId;
    private String isbn;
    private int quantity;

    @Override
    public long getId() {
        return id;
    }

    @XmlElement(name = "id")
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }

    public long getAuthorId() {
        return authorId;
    }

    @XmlElement(name = "authorId")
    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public long getGenreId() {
        return genreId;
    }

    @XmlElement(name = "genreId")
    public void setGenreId(long genreId) {
        this.genreId = genreId;
    }

    public String getIsbn() {
        return isbn;
    }

    @XmlElement(name = "isbn")
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getQuantity() {
        return quantity;
    }

    @XmlElement(name = "quantity")
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorId=" + authorId +
                ", genreId=" + genreId +
                ", isbn='" + isbn + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
