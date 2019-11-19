package service;

import exceptions.ScheduleServiceException;
import model.Day;
import model.Group;
import model.Lesson;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class ServiceLesson {
    private List<Lesson> lessons = new ArrayList<>();


    public void addLesson(Lesson lessonLesson) throws ScheduleServiceException {

        for (Lesson i : lessons) {
            if (lessonLesson.getDayLesson().equals(i.getDayLesson()) &&
                    lessonLesson.getNumberLesson().equals(i.getNumberLesson())) {

                if(lessonLesson.getGroup().equals(i.getGroup())){
                    throw  new ScheduleServiceException("Group is busy");
                }
                if(lessonLesson.getTeacher().equals(i.getTeacher())){
                    throw  new ScheduleServiceException("Teacher is busy");
                }
                if(lessonLesson.getRoom().equals(i.getRoom())){
                    throw  new ScheduleServiceException("Room is busy");
                }
            }
        }

        lessons.add(lessonLesson);
    }

    public List<Lesson> getByGroup(Group group) {
        List<Lesson> array = new ArrayList<>();

        for (Lesson i : lessons) {
            if (i.getGroup().equals(group)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Lesson> getByDay(Day day) {
        List<Lesson> array = new ArrayList<>();

        for (Lesson i : lessons) {
            if (i.getDayLesson().equals(day)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Lesson> getByTeacher(Teacher teacher) {
        List<Lesson> array = new ArrayList<>();

        for (Lesson i : lessons) {
            if (i.getTeacher().equals(teacher)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Lesson> getByDayGroup(Day day, Group group) {
        List<Lesson> array = new ArrayList<>();

        for (Lesson i : lessons) {
            if (i.getDayLesson().equals(day) && i.getGroup().equals(group)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Lesson> getSchedule() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }
}
