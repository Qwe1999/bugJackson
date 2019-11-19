package service;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;

class ServiceJsonTest {

    ServiceJson serviceJson = new ServiceJson();

    @org.junit.jupiter.api.Test
    public void read() throws IOException {
        ArrayList<Lesson> schedulesActual = (ArrayList<Lesson>)
                serviceJson.read("src/test/resources/schedule.json");

        assertFalse(schedulesActual.size() == 0);
    }

    @Test
    public void write() throws IOException {


        ArrayList<Lesson> schedulesExpected= new ArrayList<>();

        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Friday);
        lesson.setGroup(new Group("123"));
        lesson.setNumberLesson(NumberLesson.eight);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("ASD","FVC"));

        schedulesExpected.add(lesson);
        serviceJson.write(schedulesExpected,"src/test/resources/schedule1.json");

        File file = new File("src/test/resources/schedule1.json");
        assertFalse(file.length() == 0);
    }

}