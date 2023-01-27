package library.sql.jdbc;

import library.models.RoomReservation;
import library.sql.IRoomReservationDAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Moussa
 */
public class RoomReservationDAO extends AbstractDAO<RoomReservation> implements IRoomReservationDAO {
    private static final String TABLE_NAME = "room_reservation";
    private static final List<String> COLUMN_NAMES = List.of("room_id", "reservation_id");

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected RoomReservation createEntityFromRow(ResultSet rs) throws SQLException {
        RoomReservation roomReservation = new RoomReservation();
        roomReservation.setId(rs.getLong("room_reservation_id"));
        roomReservation.setMemberId(rs.getLong("member_id"));
        roomReservation.setRoomId(rs.getLong("room_id"));
        roomReservation.setReservedDate(rs.getDate("reserved_date"));
        roomReservation.setStartTime(rs.getTimestamp("start_time"));
        roomReservation.setEndTime(rs.getTimestamp("end_time"));
        return roomReservation;
    }

    @Override
    protected List<String> getColumnNames() {
        return COLUMN_NAMES;
    }

    @Override
    protected void setCreatePreparedStatement(PreparedStatement ps, RoomReservation roomReservation) throws SQLException {
        ps.setLong(1, roomReservation.getMemberId());
        ps.setLong(2, roomReservation.getRoomId());
        ps.setDate(3, roomReservation.getReservedDate());
        ps.setTimestamp(4, roomReservation.getStartTime());
        ps.setTimestamp(5, roomReservation.getEndTime());
    }
}
