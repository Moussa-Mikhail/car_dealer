package library.sql.mybatis;

import library.models.Checkout;
import library.sql.ICheckoutDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class CheckoutDAO implements ICheckoutDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public Checkout getEntityById(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICheckoutDAO mapper = session.getMapper(ICheckoutDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void updateEntity(Checkout entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICheckoutDAO mapper = session.getMapper(ICheckoutDAO.class);
            mapper.updateEntity(entity);
            session.commit();
        }
    }

    @Override
    public void createEntity(Checkout entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICheckoutDAO mapper = session.getMapper(ICheckoutDAO.class);
            mapper.createEntity(entity);
            session.commit();
        }
    }

    @Override
    public void deleteEntity(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICheckoutDAO mapper = session.getMapper(ICheckoutDAO.class);
            mapper.deleteEntity(id);
            session.commit();
        }
    }

    @Override
    public List<Checkout> getAllEntities() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            ICheckoutDAO mapper = session.getMapper(ICheckoutDAO.class);
            return mapper.getAllEntities();
        }
    }
}
