package library.sql.jdbc;

import library.models.Employee;
import library.sql.IEmployeeDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class EmployeeDAO extends AbstractDAO<Employee> implements IEmployeeDAO {
    private static final String TABLE_NAME = "employees";
    private static final List<String> COLUMN_NAMES = List.of("first_name", "last_name", "salary", "hired_date", "email");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Employee createEntityFromRow(ResultSet rs) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("employee_id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setSalary(rs.getInt("salary"));
        employee.setHiredDate(rs.getDate("hired_date"));
        return employee;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Employee employee) throws SQLException {
        ps.setString(1, employee.getFirstName());
        ps.setString(2, employee.getLastName());
        ps.setInt(3, employee.getSalary());
        ps.setDate(4, employee.getHiredDate());
    }
}
