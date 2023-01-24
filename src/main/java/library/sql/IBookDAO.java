package library.sql;

import library.models.Book;

import java.util.Optional;

/**
 * @author Moussa
 */
public interface IBookDAO extends IBaseDAO<Book> {
    /**
     * @param isbn the isbn of the book
     * @return the book with the given isbn
     */
    Optional<Book> getBookByISBN(String isbn);
}
