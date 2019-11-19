package database;

import exceptions.GroupException;
import model.Group;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GroupDAO implements JdbcDAO<Group>{

    private static final Logger log = Logger.getLogger(GroupDAO.class);
    private final String INSERT = "INSERT INTO class(number) VALUES (?) RETURNING id";
    private final String CREATE_TABLE = "CREATE TABLE class(Id SERIAL  PRIMARY KEY," +
                                        "Number CHARACTER VARYING(30) UNIQUE);";
    private final String SELECT_BY_NUMBER = "SELECT * FROM CLASS WHERE Number = ?";
    private final String SELECT_BY_ID = "SELECT * FROM CLASS WHERE Id = ?";
    private final String SELECT_ALL= "SELECT * FROM CLASS ";
    private final String DELETE_BY_ID = "DELETE FROM GROUP WHERE id = ? ";
    private final String DELETE_BY_NUMBER = "DELETE FROM CLASS WHERE number = ? ";
    private final String DROP_TABLE = "DROP TABLE CLASS ";


    public List<Group> parseResultSet(ResultSet rs) throws SQLException {
        List<Group> groups = new ArrayList<>();
        while (rs.next()) {
            Group group = new Group();
            group.setId(rs.getInt("id"));
            group.setNumber(rs.getString("number"));
            groups.add(group);
        }

        log.info("Return groups");
        return groups;
    }

    public void create() throws SQLException {
        try(PreparedStatement statement = DBConnection.getPreparedStatement(CREATE_TABLE)) {
            log.info("Call create");
            statement.execute();
        }
    }

    @Override
    public int insert(Group group) throws SQLException {
        try(PreparedStatement statement = DBConnection.getPreparedStatement(INSERT)) {

            statement.setString(1, group.getNumber());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            log.info("Inserted group");
            return id;
        }
    }


    public Optional<Group> selectByNumber(String  number) throws SQLException {

        try(PreparedStatement statement = DBConnection.getPreparedStatement(SELECT_BY_NUMBER)) {

            statement.setString(1, number);
            ResultSet rs = statement.executeQuery();

            List<Group> groups = parseResultSet(rs);
            if (groups.size() == 0){
                log.info("Group with number = " + number + " is not exist");
                return Optional.empty();
            }
            else {
                log.info("Return group");
                return Optional.of(groups.get(0));
            }
        }
    }

    @Override
    public Optional<Group> selectById(int id) throws SQLException,  GroupException {

        try(PreparedStatement statement =  DBConnection.getPreparedStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            List<Group> groups = parseResultSet(rs);
            if (groups.size() == 0){
                log.info("Group with id = " + id + " is not exist");
                return Optional.empty();
            }
            else {
                log.info("Return group");
                return Optional.of(groups.get(0));
            }
        }
    }

    public Optional<List<Group>> selectAll() throws SQLException,  GroupException {

        try(PreparedStatement statement = DBConnection.getPreparedStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();
            List<Group> groups = parseResultSet(rs);
            if (groups.size() == 0){
                log.info("Are not exist any groups");
                return Optional.empty();
            }
            else {
                log.info("Return groups");
                return Optional.of(groups);
            }

        }
    }

    @Override
    public void deleteById(int id) throws SQLException {

        try(PreparedStatement statement = DBConnection.getPreparedStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.executeQuery();
            log.info("Deleted group with id = " + id);
        }
    }

    public void deleteByNumber(String number) throws SQLException {

        try(PreparedStatement statement = DBConnection.getPreparedStatement(DELETE_BY_NUMBER)) {

            statement.setString(1, number);
            statement.executeQuery();
            log.info("Deleted group with number = " + number);
        }
    }

    public void dropTable() throws SQLException {
        try(PreparedStatement statement =  DBConnection.getPreparedStatement(DROP_TABLE)) {

            statement.execute();
            log.info("Drop table");
        }
    }
}
