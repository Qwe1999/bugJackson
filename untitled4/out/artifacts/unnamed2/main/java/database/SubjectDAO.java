package database;

import exceptions.SubjectException;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDAO extends JdbcDAO<Subject> {


    private final String CREATE_TABLE = "CREATE TABLE subject(Id SERIAL PRIMARY KEY," +
            "                       Name CHARACTER VARYING(30) UNIQUE);";
    private final String INSERT = "INSERT INTO SUBJECT(Name) VALUES (?) RETURNING id";
    private final String SELECT_BY_NAME = "SELECT * FROM SUBJECT WHERE Name = ?";
    private final String SELECT_BY_ID = "SELECT * FROM SUBJECT WHERE Id =?";
    private final String SELECT_ALL = "SELECT * FROM SUBJECT";
    private final String DELETE_BY_ID = "DELETE SUBJECT WHERE ID = ?";
    private final String DROP_TABLE = "DROP TABLE SUBJECT";


    @Override
    public List<Subject> parseResultSet(ResultSet rs) throws SQLException {

        List<Subject> subjects = new ArrayList<>();
        while (rs.next()) {
            Subject subject = new Subject();
            subject.setId(rs.getInt("id"));
            subject.setName(rs.getString("name"));
            subjects.add(subject);
        }
        return subjects;
    }

    public void create() throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(CREATE_TABLE)) {

            statement.execute();
        }
    }

    public int insert(Subject subject) throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(INSERT)) {
            statement.setString(1, subject.getName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            return id;
        }
    }

    public Subject selectByName(String name) throws SQLException, SubjectException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_NAME)) {
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs).get(0);
        }
    }

    public Subject selectById(int id) throws SQLException {

       try(PreparedStatement statement = connection
                    .prepareStatement(DELETE_BY_ID)) {
           statement.setInt(1, id);

           ResultSet rs = statement.executeQuery();
           return parseResultSet(rs).get(0);

       }
    }

    public List<Subject> selectAll() throws SQLException, SubjectException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_ALL)) {

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
