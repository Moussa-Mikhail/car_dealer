package library.patterns;

import library.models.Book;

/**
 * @author Moussa
 */
public class BookBuilder implements IBuilder<Book> {
    private long id;
    private String title;
    private long authorId;
    private long genreId;
    private String isbn;
    private int quantity;

    public BookBuilder setId(long id) {
        this.id = id;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setAuthorId(long authorId) {
        this.authorId = authorId;
        return this;
    }

    public BookBuilder setGenreId(long genreId) {
        this.genreId = genreId;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    @Override
    public Book build() {
        Book book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthorId(authorId);
        book.setGenreId(genreId);
        book.setIsbn(isbn);
        book.setQuantity(quantity);
        return book;
    }
}
