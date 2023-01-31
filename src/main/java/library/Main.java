package library;

import library.models.Author;
import library.models.Book;
import library.models.Checkout;
import library.models.Member;
import library.sql.IAuthorDAO;
import library.sql.IBookDAO;
import library.sql.ICheckoutDAO;
import library.sql.IMemberDAO;
import library.sql.mybatis.AuthorDAO;
import library.sql.mybatis.BookDAO;
import library.sql.mybatis.CheckoutDAO;
import library.sql.mybatis.MemberDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Moussa
 */
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        try {
            IBookDAO bookDAO = new BookDAO();
            for (Book book : bookDAO.getAllEntities()) {
                System.out.println(book);
            }
            System.out.println();

            Book firstBook = bookDAO.getEntityById(1);

            if (firstBook == null) {
                LOGGER.error("Book not found");
                return;
            }

            System.out.println(firstBook);
            System.out.println();

            firstBook.setTitle("Artemis Fowl");
            bookDAO.updateEntity(firstBook);

            for (Book book : bookDAO.getAllEntities()) {
                System.out.println(book);
            }
            System.out.println();

            IMemberDAO memberDAO = new MemberDAO();
            for (Member member : memberDAO.getAllEntities()) {
                System.out.println(member);
            }
            System.out.println();

            IAuthorDAO authorDAO = new AuthorDAO();
            for (Author author : authorDAO.getAllEntities()) {
                System.out.println(author);
            }
            System.out.println();

            ICheckoutDAO checkoutDAO = new CheckoutDAO();
            for (Checkout checkout : checkoutDAO.getAllEntities()) {
                System.out.println(checkout);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
