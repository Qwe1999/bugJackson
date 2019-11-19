package service;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

class ServiceJsonTest {

    ServiceJson serviceJson = new ServiceJson();

    @org.junit.jupiter.api.Test
    public void read() throws IOException {
        ArrayList<Schedule> schedulesActual = (ArrayList<Schedule>)
                serviceJson.read("src/test/resources/schedule.json");

        assertFalse(schedulesActual.size() == 0);
    }

    @Test
    public void write() throws IOException {


        ArrayList<Schedule> schedulesExpected= new ArrayList<>();

        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Friday);
        schedule.setGroup(new Group("123"));
        schedule.setNumberLesson(NumberLesson.eight);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("ASD","FVC"));

        schedulesExpected.add(schedule);
        serviceJson.write(schedulesExpected,"src/test/resources/schedule1.json");

        File file = new File("src/test/resources/schedule1.json");
        assertFalse(file.length() == 0);
    }

}