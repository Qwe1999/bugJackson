package database;

import exceptions.SubjectException;
import model.Lesson;
import model.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SubjectDAO implements JdbcDAO<Subject> {


    private final String CREATE_TABLE = "CREATE TABLE subject(Id SERIAL PRIMARY KEY," +
            "                       Name CHARACTER VARYING(30) UNIQUE);";
    private final String INSERT = "INSERT INTO SUBJECT(Name) VALUES (?) RETURNING id";
    private final String SELECT_BY_NAME = "SELECT * FROM SUBJECT WHERE Name = ?";
    private final String SELECT_BY_ID = "SELECT * FROM SUBJECT WHERE Id =?";
    private final String SELECT_ALL = "SELECT * FROM SUBJECT";
    private final String DELETE_BY_ID = "DELETE SUBJECT WHERE ID = ?";
    private final String DELETE_BY_NAME = "DELETE SUBJECT WHERE NAME = ?";
    private final String DROP_TABLE = "DROP TABLE SUBJECT";




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
        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(CREATE_TABLE)) {
            statement.execute();
        }
    }

    public int insert(Subject subject) throws SQLException {
        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(INSERT)) {
            statement.setString(1, subject.getName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            return id;
        }
    }

    public Optional<Subject> selectByName(String name) throws SQLException, SubjectException {

        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(SELECT_BY_NAME)) {
            statement.setString(1, name);

            ResultSet rs = statement.executeQuery();

            List<Subject> subjects = parseResultSet(rs);
            if (subjects.size() == 0){
                return Optional.empty();
            }
            else {
                return Optional.of(subjects.get(0));
            }
        }
    }

    public Optional<Subject> selectById(int id) throws SQLException {

       try(PreparedStatement statement =
                   DBConnection.getPreparedStatement(SELECT_BY_ID)) {
           statement.setInt(1, id);

           ResultSet rs = statement.executeQuery();

           List<Subject> subjects = parseResultSet(rs);
           if (subjects.size() == 0){
               return Optional.empty();
           }
           else {
               return Optional.of(subjects.get(0));
           }

       }
    }

    public Optional<List<Subject>> selectAll() throws SQLException, SubjectException {

        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(SELECT_ALL)) {

            ResultSet rs = statement.executeQuery();

            List<Subject> subjects = parseResultSet(rs);
            if (subjects.size() == 0){
                return Optional.empty();
            }
            else {
                return Optional.of(subjects);
            }
        }
    }

    public void deleteById(int id) throws SQLException {

        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(DELETE_BY_ID)) {

            statement.setInt(1, id);
            statement.execute();
        }
    }

    public void deleteByName(String name) throws SQLException {

        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(DELETE_BY_NAME)) {

            statement.setString(1, name);
            statement.execute();
        }
    }

    public void dropTable() throws SQLException {
        try(PreparedStatement statement =
                    DBConnection.getPreparedStatement(DROP_TABLE)) {
            statement.execute();
        }
    }
}
