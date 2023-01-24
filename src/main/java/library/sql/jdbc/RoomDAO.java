package library.sql.jdbc;

import library.models.Room;
import library.sql.IRoomDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class RoomDAO extends AbstractDAO<Room> implements IRoomDAO {
    private static final String TABLE_NAME = "room";
    private static final List<String> COLUMN_NAMES = List.of("name");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected Room createEntityFromRow(ResultSet rs) throws SQLException {
        Room room = new Room();
        room.setId(rs.getLong("room_id"));
        room.setRoomNumber(rs.getInt("room_number"));
        room.setCapacity(rs.getInt("room_capacity"));
        return room;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, Room entity) throws SQLException {
        ps.setInt(1, entity.getRoomNumber());
        ps.setInt(2, entity.getCapacity());
    }
}
