package database;

import exceptions.TeacherException;
import model.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO extends JdbcDAO<Teacher>{

    private final String INSERT = "INSERT INTO teacher(FirstName,LastName) VALUES (?,?) RETURNING id";
    private final String CREATE_TABLE = "CREATE TABLE teacher(Id SERIAL PRIMARY KEY,"+
                "FirstName CHARACTER VARYING(30),LastName CHARACTER VARYING(30)," +
                "UNIQUE(FirstName, LastName) );";
    private final String SELECT_BY_NAME = "SELECT * FROM Teacher WHERE FirstName = ? AND LastName = ?";
    private final String SELECT_BY_ID = "SELECT * FROM Teacher WHERE Id = ?";
    private final String SELECT_ALL= "SELECT * FROM TEACHER";
    private final String DELETE_BY_ID = "DELETE FROM TEACHER WHERE id = ? ";
    private final String DROP_TABLE = "DROP TABLE TEACHER ";



    @Override
    public List<Teacher> parseResultSet(ResultSet rs) throws SQLException {

        List<Teacher> teachers = new ArrayList<>();
        while (rs.next()) {
            Teacher teacher = new Teacher();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("firstName"));
            teacher.setLastName(rs.getString("lastName"));
            teachers.add(teacher);
        }
        return teachers;
    }

    public void create() throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(CREATE_TABLE)) {

            statement.execute();
        }
    }

    public int insert(Teacher teacher) throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(INSERT)) {

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            ResultSet rs = statement.executeQuery();
            rs.next();
            int id;
            id = rs.getInt(1);
            statement.close();
            return id;

        }
    }

    public Teacher selectByName(String firstName, String lastName) throws SQLException,  TeacherException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_NAME)) {

            statement.setString(1, firstName);
            statement.setString(2, lastName);

            ResultSet rs = statement.executeQuery();
            return parseResultSet(rs).get(0);
        }
    }

    public Teacher selectById(int id) throws SQLException, TeacherException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            return parseResultSet(rs).get(0);
        }
    }

    public List<Teacher> selectAll() throws SQLException,  TeacherException {

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
