package database;

import exceptions.RoomException;
import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO extends JdbcDAO<Room>{



    private static final String CREATE_TABLE = "CREATE TABLE room(Id SERIAL PRIMARY KEY," +
            "                               Number CHARACTER VARYING(20) UNIQUE);";
    private static final String INSERT = "INSERT INTO room(number) VALUES (?) RETURNING id";
    private static final String SELECT_BY_NUMBER = "SELECdsT * FROM CLASS WHERE Number = ?";
    private static final String SELECT_BY_ID = "SELECT * FROM NUMBER WHERE Id = ?";
    private static final String SELECT_ALL = "SELECT * FROM ROOM";
    private static final String DELETE_BY_ID = "DELETE FROM ROOM WHERE id = ?";
    private static final String DROP_TABLE = "DROP TABLE ROOM";



    @Override
    public List<Room> parseResultSet(ResultSet rs) throws SQLException {
        List<Room> rooms = new ArrayList<>();

        while (rs.next()) {
            Room room = new Room();
            room.setId(rs.getInt("id"));
            room.setNumber(rs.getString("number"));
            rooms.add(room);
        }
        return rooms;
    }


    public void create() throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(CREATE_TABLE)) {
            statement.execute();
        }
    }

    public int insert(Room room) throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(INSERT)) {

            statement.setString(1, room.getNumber());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            return id;
        }

    }

    public Room selectByNumber(String number) throws SQLException,  RoomException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_NUMBER)) {

            statement.setString(1, number);

            List<Room> rooms = new ArrayList<>();
            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs).get(0);
        }
    }

    public Room selectById(int id) throws SQLException, RoomException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs).get(0);
        }

    }

    public List<Room> selectAll() throws SQLException, RoomException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_ALL)) {

            List<Room> rooms = new ArrayList<>();
            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs);
        }
    }

    public void deleteById(int id) throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public void dropTable() throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(DROP_TABLE)) {

            statement.execute();
        }
    }
}
