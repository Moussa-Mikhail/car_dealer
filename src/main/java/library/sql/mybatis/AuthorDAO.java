package library.sql.mybatis;

import library.models.Author;
import library.sql.IAuthorDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class AuthorDAO implements IAuthorDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public Author getEntityById(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAuthorDAO mapper = session.getMapper(IAuthorDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void updateEntity(Author entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAuthorDAO mapper = session.getMapper(IAuthorDAO.class);
            mapper.updateEntity(entity);
            session.commit();
        }
    }

    @Override
    public void createEntity(Author entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAuthorDAO mapper = session.getMapper(IAuthorDAO.class);
            mapper.createEntity(entity);
            session.commit();
        }
    }

    @Override
    public void deleteEntity(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAuthorDAO mapper = session.getMapper(IAuthorDAO.class);
            mapper.deleteEntity(id);
            session.commit();
        }
    }

    @Override
    public List<Author> getAllEntities() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IAuthorDAO mapper = session.getMapper(IAuthorDAO.class);
            return mapper.getAllEntities();
        }
    }
}
