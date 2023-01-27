package library.sql;

import library.models.Book;

import java.sql.SQLException;

/**
 * @author Moussa
 */
public interface IBookDAO extends IBaseDAO<Book> {
    /**
     * @param isbn the isbn of the book
     * @return the book with the given isbn
     */
    Book getBookByISBN(String isbn) throws SQLException;
}
