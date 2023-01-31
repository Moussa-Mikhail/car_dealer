package library.sql.mybatis;

import library.models.Book;
import library.sql.IBookDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class BookDAO implements IBookDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public Book getEntityById(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void updateEntity(Book entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            mapper.updateEntity(entity);
            session.commit();
        }
    }

    @Override
    public void createEntity(Book entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            mapper.createEntity(entity);
            session.commit();
        }
    }

    @Override
    public void deleteEntity(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            mapper.deleteEntity(id);
            session.commit();
        }
    }

    @Override
    public List<Book> getAllEntities() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            return mapper.getAllEntities();
        }
    }

    @Override
    public Book getBookByISBN(String isbn) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IBookDAO mapper = session.getMapper(IBookDAO.class);
            return mapper.getBookByISBN(isbn);
        }
    }
}
