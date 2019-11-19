package service;//import org.junit.jupiter.api.Test;

import exceptions.ScheduleServiceException;
import model.*;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import service.ServiceSchedule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class ServiceScheduleTest {

    ServiceSchedule serviceSchedule;

    @BeforeEach
    void setUp() {
        List<Schedule> schedules = new ArrayList<>();

        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Thursday);
        schedule.setGroup(new Group("443"));
        schedule.setNumberLesson(NumberLesson.three);
        schedule.setSubject(new Subject("Programing"));
        schedule.setTeacher(new Teacher("Ivan","Kovlov"));
        schedule.setRoom(new Room("123"));
        schedules.add(schedule);

        schedule  = new Schedule();
        schedule.setDay(Day.Friday);
        schedule.setGroup(new Group("123"));
        schedule.setNumberLesson(NumberLesson.eight);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("zczc","asdsad"));
        schedule.setRoom(new Room("111"));
        schedules.add(schedule);

        schedule  = new Schedule();
        schedule.setDay(Day.Friday);
        schedule.setGroup(new Group("333"));
        schedule.setNumberLesson(NumberLesson.seven);
        schedule.setSubject(new Subject("Physics"));
        schedule.setTeacher(new Teacher("ZXC","FVC"));
        schedule.setRoom(new Room("333"));
        schedules.add(schedule);

        schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));
        schedules.add(schedule);


        serviceSchedule = new ServiceSchedule();
        serviceSchedule.setSchedules(schedules);
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void addLesson(Schedule schedule) throws ScheduleServiceException {
        int size = serviceSchedule.getSchedule().size();
        serviceSchedule.addLesson(schedule);
        assertEquals(size+1,serviceSchedule.getSchedule().size());
    }

    static Stream<Schedule> addLesson() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Friday);
        schedule.setGroup(new Group("131"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("121"));
        return Stream.of(
               schedule
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void addLessonBusy(Schedule schedule) throws ScheduleServiceException {
      assertThrows(ScheduleServiceException.class,() -> {
          serviceSchedule.addLesson(schedule);
      });

    }

    static Stream<Schedule> addLessonBusy() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));
        return Stream.of(
                schedule
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByGroup(List<Schedule> expected, Group group) {
        List<Schedule> actual = serviceSchedule.getByGroup(new Group("111"));

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByGroup() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(schedule),new Group("111"))
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByDay(List<Schedule> expected, Day day) {
        List<Schedule> actual = serviceSchedule.getByDay(day);

        System.out.println(actual);
        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByDay() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(schedule),Day.Monday)
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByTeacher(List<Schedule> expected, Teacher teacher) {
        List<Schedule> actual = serviceSchedule.getByTeacher(teacher);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByTeacher() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(schedule),new Teacher("Vlad","Banar"))
        );
    }

    @Test
    @ParameterizedTest
    @MethodSource
    void getByDayGroup(List<Schedule> expected, Day day, Group group) {
       List<Schedule> actual = serviceSchedule.getByDayGroup(day, group);

        assertArrayEquals(expected.toArray(), actual.toArray());
    }

    static Stream<Arguments> getByDayGroup() {
        Schedule schedule  = new Schedule();
        schedule.setDay(Day.Monday);
        schedule.setGroup(new Group("111"));
        schedule.setNumberLesson(NumberLesson.one);
        schedule.setSubject(new Subject("Math"));
        schedule.setTeacher(new Teacher("Vlad","Banar"));
        schedule.setRoom(new Room("111"));

        return Stream.of(
                arguments(Arrays.asList(schedule),Day.Monday,new Group("111"))
        );
    }

}