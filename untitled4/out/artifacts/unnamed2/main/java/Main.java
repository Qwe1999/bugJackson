
import database.*;
import exceptions.TeacherException;
import model.*;
import service.ServiceJson;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.Callable;

public class Main {

    static final String PATH_JSON = "src/main/resources/schedule.json";
    static final String PATH_PROPERTIES = "src/main/resources/config.properties";
    static class ServiceJsonThread implements Callable<String> {

        @Override
        public String call()  {
            try {
                ServiceJson serviceJson = new ServiceJson();
                ArrayList<Schedule> schedules =
                        (ArrayList<Schedule>) serviceJson.read(PATH_JSON);
                return "Successfully";
            } catch (IOException e) {
                return "Error";
            }
        }
    }

    public static void main(String[] args) throws SQLException {

        service.ServiceSchedule serviceSchedule = new service.ServiceSchedule();
        service.ServiceJson serviceJson = new service.ServiceJson();


        try (Connection connection = DBConnection.getConnection()){
            GroupDAO groupDAO = (GroupDAO) new GroupDAO().setConnection(connection);
            RoomDAO roomDAO = (RoomDAO) new RoomDAO().setConnection(connection);
            SubjectDAO subjectDAO = (SubjectDAO) new SubjectDAO().setConnection(connection);
            TeacherDAO teacherDAO = (TeacherDAO) new TeacherDAO().setConnection(connection);
            ScheduleDAO scheduleDAO = (ScheduleDAO) new ScheduleDAO().setConnection(connection);

            scheduleDAO.dropTable();
            groupDAO.dropTable();
            roomDAO.dropTable();
            subjectDAO.dropTable();
            teacherDAO.dropTable();

            groupDAO.create();
            roomDAO.create();
            subjectDAO.create();
            teacherDAO.create();
            scheduleDAO.create();

            ArrayList<Schedule> schedules = (ArrayList<Schedule>) serviceJson.read(PATH_JSON);

            for (Schedule schedule: schedules){

                groupDAO.insert(schedule.getGroup());

                roomDAO.insert(schedule.getRoom());

                subjectDAO.insert(schedule.getSubject());

                teacherDAO.insert(schedule.getTeacher());

                scheduleDAO.insert(schedule);

            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

}
