package database;

import exceptions.GroupException;
import exceptions.TeacherException;
import model.Group;
import model.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GroupDAO extends JdbcDAO<Group> {


    private final String INSERT = "INSERT INTO class(number) VALUES (?) RETURNING id";
    private final String CREATE_TABLE = "CREATE TABLE class(Id SERIAL  PRIMARY KEY," +
                                        "Number CHARACTER VARYING(30) UNIQUE);";
    private final String SELECT_BY_NUMBER = "SELECT * FROM CLASS WHERE Number = ?";
    private final String SELECT_BY_ID = "SELECT * FROM CLASS WHERE Id = ?";
    private final String SELECT_ALL= "SELECT * FROM CLASS ";
    private final String DELETE_BY_ID = "DELETE FROM GROUP WHERE id = ? ";
    private final String DROP_TABLE = "DROP TABLE CLASS ";



    @Override
    public List<Group> parseResultSet(ResultSet rs) throws SQLException {
        List<Group> groups = new ArrayList<>();
        while (rs.next()) {
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setNumber(rs.getString("number"));
            groups.add(group);
        }

        return groups;
    }


    public void create() throws SQLException {
        PreparedStatement statement = connection
                .prepareStatement(CREATE_TABLE);

        statement.execute();
        statement.close();
    }

    @Override
    public int insert(Group group) throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(INSERT)) {

            statement.setString(1, group.getNumber());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            return id;
        }
    }

    public Group selectByNumber(String  number) throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_NUMBER)) {

            statement.setString(1, number);
            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs).get(0);
        }
    }

    @Override
    public Group selectById(int id) throws SQLException,  GroupException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return parseResultSet(rs).get(0);
        }
    }

    public List<Group> selectAll() throws SQLException,  GroupException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();
            return parseResultSet(rs);
        }
    }

    @Override
    public void deleteById(int id) throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.executeQuery();
        }
    }

    public void dropTable() throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(DROP_TABLE)) {

            statement.execute();
        }
    }
}
