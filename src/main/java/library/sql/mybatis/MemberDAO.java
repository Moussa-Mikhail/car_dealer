package library.sql.mybatis;

import library.models.Member;
import library.sql.IMemberDAO;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class MemberDAO implements IMemberDAO {
    private SqlSessionFactory sqlSessionFactory = MyBatisSqlFactory.getSqlSessionFactory();

    @Override
    public Member getEntityById(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMemberDAO mapper = session.getMapper(IMemberDAO.class);
            return mapper.getEntityById(id);
        }
    }

    @Override
    public void updateEntity(Member entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMemberDAO mapper = session.getMapper(IMemberDAO.class);
            mapper.updateEntity(entity);
            session.commit();
        }
    }

    @Override
    public void createEntity(Member entity) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMemberDAO mapper = session.getMapper(IMemberDAO.class);
            mapper.createEntity(entity);
            session.commit();
        }
    }

    @Override
    public void deleteEntity(long id) throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMemberDAO mapper = session.getMapper(IMemberDAO.class);
            mapper.deleteEntity(id);
            session.commit();
        }
    }

    @Override
    public List<Member> getAllEntities() throws SQLException {
        try (SqlSession session = sqlSessionFactory.openSession()) {
            IMemberDAO mapper = session.getMapper(IMemberDAO.class);
            return mapper.getAllEntities();
        }
    }
}
