package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import database.*;
import model.*;

import java.sql.SQLException;
import java.util.*;

public class ServiceSchedule {

    GroupDAO groupDAO;
    RoomDAO roomDAO;
    SubjectDAO subjectDAO;
    TeacherDAO teacherDAO;
    LessonDAO lessonDAO;

    public ServiceSchedule() {

        groupDAO = new GroupDAO();
        roomDAO = new RoomDAO();
        subjectDAO = new SubjectDAO();
        teacherDAO = new TeacherDAO();
        lessonDAO = new LessonDAO();
    }


    public void createTables() throws SQLException {
        groupDAO.create();
        roomDAO.create();
        subjectDAO.create();
        teacherDAO.create();
        lessonDAO.create();
    }

    public void dropTables() throws SQLException {
        lessonDAO.dropTable();
        groupDAO.dropTable();
        roomDAO.dropTable();
        subjectDAO.dropTable();
        teacherDAO.dropTable();
    }

    public List<Lesson> selectByNumberGroup(String numberGroup)
            throws SQLException {

        Optional<List<Lesson>> lessons =
                lessonDAO.selectByNumberGroup(numberGroup);
        if(lessons.isPresent()){
            return lessons.get();
        }
        return new ArrayList<>();
    }

    public void deleteGroup(String number) throws SQLException {
        groupDAO.deleteByNumber(number);
    }

    public void deleteSubject(String name) throws SQLException {
        subjectDAO.deleteByName(name);
    }

    public void deleteRoom(String number) throws SQLException {
        roomDAO.deleteByNumber(number);
    }

    public void deleteTeacher(String firstName,String lastName) throws SQLException {
        teacherDAO.deleteByName(firstName,lastName);
    }

    public void insertLesson(Lesson lesson) throws SQLException {
        int id ;

        Optional<Group> optionalGroup = groupDAO
                .selectByNumber(lesson.getGroup().getNumber());

        id = optionalGroup.isPresent() ?  optionalGroup.get().getId() :
                  groupDAO.insert(lesson.getGroup());
        lesson.getGroup().setId(id);

        Optional<Subject> optionalSubject = subjectDAO
                .selectByName(lesson.getSubject().getName());

        id = optionalSubject.isPresent() ?  optionalSubject.get().getId() :
                subjectDAO.insert(lesson.getSubject());
        lesson.getSubject().setId(id);

        Optional<Room> optionalRoom = roomDAO
                .selectByNumber(lesson.getRoom().getNumber());

        id = optionalRoom.isPresent() ? optionalRoom.get().getId() :
                roomDAO.insert(lesson.getRoom());
        lesson.getRoom().setId(id);


        Optional<Teacher> optionalTeacher = teacherDAO
                .selectByName(lesson.getTeacher().getFirstName(),
                 lesson.getTeacher().getLastName());

        id = optionalTeacher.isPresent() ? optionalTeacher.get().getId() :
                teacherDAO.insert(lesson.getTeacher());
        lesson.getTeacher().setId(id);

        lessonDAO.insert(lesson);
    }

    public void insertGroup(Group group) throws SQLException {
        groupDAO.insert(group);
    }

    public void insertTeacher(Teacher teacher) throws SQLException {
        teacherDAO.insert(teacher);
    }

    public void insertSubject(Subject subject) throws SQLException {
        subjectDAO.insert(subject);
    }

    public void insertRoom(Room room) throws SQLException {
        roomDAO.insert(room);
    }

    public List<Lesson> selectByTeacherName(String firstName, String lastName)
            throws SQLException {
       Optional<List<Lesson>> lessons =
               lessonDAO.selectByTeacherName(firstName,lastName);
       if(lessons.isPresent()){
           return lessons.get();
       }
        return new ArrayList<>();
    }

    public List<Lesson> selectBySubject(String name)
            throws SQLException {
        Optional<List<Lesson>> lessons =
                lessonDAO.selectByNameSubject(name);
        if(lessons.isPresent()){
            return lessons.get();
        }
        return new ArrayList<>();
    }

    public List<Lesson> selectByRoom(String number)
            throws SQLException {
        Optional<List<Lesson>> lessons =
                lessonDAO.selectByNameRoom(number);
        if(lessons.isPresent()){
            return lessons.get();
        }
        return new ArrayList<>();
    }

    public List<Lesson> selectAll() throws SQLException {
        Optional<List<Lesson>> lessons =
                lessonDAO.selectAll();
        if(lessons.isPresent()){
            return lessons.get();
        }
        return new ArrayList<>();
    }

    public Map<NumberLesson,Map<Day,Lesson>> getMapSchedule
            (List<Lesson> lessons){

        Map<NumberLesson, Map<Day, Lesson>> schedule =
                new EnumMap<>(NumberLesson.class);
            for (Lesson lesson : lessons) {
                Map<Day, Lesson> scheduleValue = new EnumMap<Day, Lesson>(Day.class);
                scheduleValue.put(lesson.getDayLesson(), lesson);
                schedule.put(lesson.getNumberLesson(), scheduleValue);
            }
            return schedule;
    }
}
