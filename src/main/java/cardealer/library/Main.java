package cardealer.library;

import cardealer.library.models.Book;
import cardealer.library.sql.IBookDAO;
import cardealer.library.sql.jdbc.BookDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * @author Moussa
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            IBookDAO bookDAO = new BookDAO();

            List<Book> books = bookDAO.getAllEntities();
            for (Book book : books) {
                System.out.println(book);
            }

            Book firstBook = bookDAO.getEntityById(1).orElseThrow();

            System.out.println(firstBook);

            firstBook.setTitle("New Title");
            bookDAO.updateEntity(firstBook);

            books = bookDAO.getAllEntities();
            for (Book book : books) {
                System.out.println(book);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
}
