package service;//import org.junit.jupiter.api.Test;

import exceptions.ScheduleServiceException;
import model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ServiceLessonTest {

    ServiceLesson serviceLesson1;

    @BeforeEach
    void setUp() {
        List<Lesson> lessons = new ArrayList<>();

        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Thursday);
        lesson.setGroup(new Group("443"));
        lesson.setNumberLesson(NumberLesson.three);
        lesson.setSubject(new Subject("Programing"));
        lesson.setTeacher(new Teacher("Ivan","Kovlov"));
        lesson.setRoom(new Room("123"));
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setDayLesson(Day.Friday);
        lesson.setGroup(new Group("123"));
        lesson.setNumberLesson(NumberLesson.eight);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("zczc","asdsad"));
        lesson.setRoom(new Room("111"));
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setDayLesson(Day.Friday);
        lesson.setGroup(new Group("333"));
        lesson.setNumberLesson(NumberLesson.seven);
        lesson.setSubject(new Subject("Physics"));
        lesson.setTeacher(new Teacher("ZXC","FVC"));
        lesson.setRoom(new Room("333"));
        lessons.add(lesson);

        lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));
        lessons.add(lesson);


        serviceLesson1 = new ServiceLesson();
        serviceLesson1.setLessons(lessons);
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void addLesson(Lesson lesson) throws ScheduleServiceException {
        int size = serviceLesson1.getSchedule().size();
        serviceLesson1.addLesson(lesson);
        assertEquals(size+1, serviceLesson1.getSchedule().size());
    }

    static Stream<Lesson> addLesson() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Friday);
        lesson.setGroup(new Group("131"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("121"));
        return Stream.of(
                lesson
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void addLessonBusy(Lesson lesson) throws ScheduleServiceException {
      assertThrows(ScheduleServiceException.class,() -> {
          serviceLesson1.addLesson(lesson);
      });

    }

    static Stream<Lesson> addLessonBusy() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));
        return Stream.of(
                lesson
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByGroup(List<Lesson> expected, Group group) {
        List<Lesson> actual = serviceLesson1.getByGroup(new Group("111"));

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByGroup() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(lesson),new Group("111"))
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByDay(List<Lesson> expected, Day day) {
        List<Lesson> actual = serviceLesson1.getByDay(day);

        System.out.println(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByDay() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(lesson),Day.Monday)
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByTeacher(List<Lesson> expected, Teacher teacher) {
        List<Lesson> actual = serviceLesson1.getByTeacher(teacher);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByTeacher() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(lesson),new Teacher("Vlad","Banar"))
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByDayGroup(List<Lesson> expected, Day day, Group group) {
       List<Lesson> actual = serviceLesson1.getByDayGroup(day, group);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByDayGroup() {
        Lesson lesson = new Lesson();
        lesson.setDayLesson(Day.Monday);
        lesson.setGroup(new Group("111"));
        lesson.setNumberLesson(NumberLesson.one);
        lesson.setSubject(new Subject("Math"));
        lesson.setTeacher(new Teacher("Vlad","Banar"));
        lesson.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(lesson),Day.Monday,new Group("111"))
        );
    }

}