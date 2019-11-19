package database;

import exceptions.RoomException;
import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleDAO extends JdbcDAO<Schedule>{


    private static final String CREATE_TABLE = "CREATE TABLE schedule" +
            "(Id SERIAL PRIMARY KEY," +
            " TeacherId INTEGER, " +
            " SubjectId INTEGER, " +
            " NumberLesson INTEGER, " +
            " DayLesson CHARACTER  VARYING(20), " +
            " ClassId INTEGER, " +
            " RoomId INTEGER," +
            "UNIQUE(ClassId, NumberLesson,DayLesson), " +
            "UNIQUE(RoomId, NumberLesson,DayLesson), " +
            "UNIQUE(TeacherId, NumberLesson,DayLesson), " +
            "FOREIGN KEY (TeacherId) REFERENCES teacher(Id)," +
            "FOREIGN KEY (SubjectId) REFERENCES subject(Id)," +
            "FOREIGN KEY (ClassId) REFERENCES class (Id)," +
            "FOREIGN KEY (RoomId) REFERENCES room (Id)" +
            ");";
    private static final String INSERT = "INSERT INTO schedule(" +
            "NumberLesson,DayLesson,ClassId,RoomId,TeacherId,SubjectId) " +
            "VALUES (?,?,?,?,?,?) RETURNING id";
    private static final String SELECT_BY_NUMBER_DAY = "SELECT * FROM schedule WHERE number = ? AND day = ? ";
    private static final String SELECT_BY_ID = "SELECT * FROM schedule WHERE Id = ? ";
    private static final String SELECT_ALL = "SELECT * FROM NUMBER WHERE Id = ?";
    private static final String DELETE_BY_ID = "DELETE FROM SCHEDULE WHERE id = ? ";
    private static final String DROP_TABLE = "DROP TABLE SCHEDULE";



    @Override
    public List<Schedule> parseResultSet(ResultSet rs) throws SQLException {
        List<Schedule> schedule = new ArrayList<>();
        while (rs.next()) {
            Schedule scheduleLesson = new Schedule();
            scheduleLesson.setId(rs.getInt("id"));
            scheduleLesson.setNumberLesson(NumberLesson.values()[rs.getInt("NumberLesson")]);
            scheduleLesson.setDay(Day.valueOf(rs.getString("Day")));
            scheduleLesson.setGroup(new GroupDAO().setConnection(connection)
                    .selectById(rs.getInt("ClassId")));
            scheduleLesson.setRoom(new RoomDAO().setConnection(connection)
                    .selectById(rs.getInt("RoomId")));
            scheduleLesson.setTeacher(new TeacherDAO().setConnection(connection)
                    .selectById(rs.getInt("TeacherId")));
            scheduleLesson.setSubject(new SubjectDAO().setConnection(connection)
                    .selectById(rs.getInt("SubjectId")));
            schedule.add(scheduleLesson);
        }

        return schedule;
    }

    public void create() throws SQLException {

        try(PreparedStatement statement = connection
                .prepareStatement(CREATE_TABLE)){
            statement.execute();
        }
    }

    public int insert(Schedule schedule) throws SQLException {
        try(PreparedStatement statement = connection
                .prepareStatement(INSERT)) {

            int id;
            statement.setInt(1, schedule.getNumberLesson().ordinal());
            statement.setString(2, schedule.getDay().name());

            GroupDAO groupDAO = (GroupDAO) new GroupDAO().setConnection(connection);
            try {

                id = groupDAO.insert(schedule.getGroup());
            }
            catch (SQLException e){
                id = groupDAO.selectByNumber(schedule.getGroup().getNumber()).getId();
            }
            statement.setInt(3, id);
            schedule.getGroup().setId(id);

            RoomDAO roomDAO = (RoomDAO) new RoomDAO().setConnection(connection);
            try {

                id = roomDAO.insert(schedule.getRoom());
            }
            catch (SQLException e){
                id = roomDAO.selectByNumber(schedule.getRoom().getNumber()).getId();
            }
            statement.setInt(4, id);
            schedule.getRoom().setId(id);

            TeacherDAO teacherDAO= (TeacherDAO) new TeacherDAO().setConnection(connection);
            try {

                id = teacherDAO.insert(schedule.getTeacher());
            }
            catch (SQLException e){
                id = teacherDAO.selectByName(schedule.getTeacher().getFirstName(),
                        schedule.getTeacher().getLastName()).getId();
            }
            statement.setInt(5, id);
            schedule.getTeacher().setId(id);

            SubjectDAO subjectDAO = (SubjectDAO) new SubjectDAO().setConnection(connection);
            try {

                id = subjectDAO.insert(schedule.getSubject());
            }
            catch (SQLException e){
                id = subjectDAO.selectByName(schedule.getSubject().getName()).getId();
            }
            statement.setInt(6, id);
            schedule.getSubject().setId(id);

            ResultSet rs = statement.executeQuery();
            rs.next();

            return rs.getInt(1);

        }

    }

    public List<Schedule> selectByNumberLessonDay(NumberLesson number, Day day) throws SQLException {

        try(PreparedStatement statement = connection.
                prepareStatement(SELECT_BY_NUMBER_DAY)) {

            statement.setInt(1, number.ordinal());
            statement.setString(2, day.toString());

            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs);
        }

    }

    public Schedule selectById(int id) throws SQLException {

        try(PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            return parseResultSet(rs).get(0);
        }
    }

    public List<Schedule> selectAll() throws SQLException, RoomException {

        try(PreparedStatement statement = connection
                .prepareStatement(SELECT_ALL)){

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
