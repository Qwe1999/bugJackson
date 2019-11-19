package service;

import exceptions.ScheduleServiceException;
import model.Day;
import model.Group;
import model.Schedule;
import model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class ServiceSchedule {
    private List<Schedule> schedules = new ArrayList<>();

    public ServiceSchedule() {
    }


    public void addLesson(Schedule scheduleLesson) throws ScheduleServiceException {

        for (Schedule i : schedules) {
            if (scheduleLesson.getDay().equals(i.getDay()) &&
                    scheduleLesson.getNumberLesson().equals(i.getNumberLesson())) {

                if(scheduleLesson.getGroup().equals(i.getGroup())){
                    throw  new ScheduleServiceException("Group is busy");
                }
                if(scheduleLesson.getTeacher().equals(i.getTeacher())){
                    throw  new ScheduleServiceException("Teacher is busy");
                }
                if(scheduleLesson.getRoom().equals(i.getRoom())){
                    throw  new ScheduleServiceException("Room is busy");
                }
            }
        }

        schedules.add(scheduleLesson);
    }

    public List<Schedule> getByGroup(Group group) {
        List<Schedule> array = new ArrayList<>();

        for (Schedule i : schedules) {
            if (i.getGroup().equals(group)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Schedule> getByDay(Day day) {
        List<Schedule> array = new ArrayList<>();

        for (Schedule i : schedules) {
            if (i.getDay().equals(day)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Schedule> getByTeacher(Teacher teacher) {
        List<Schedule> array = new ArrayList<>();

        for (Schedule i : schedules) {
            if (i.getTeacher().equals(teacher)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Schedule> getByDayGroup(Day day, Group group) {
        List<Schedule> array = new ArrayList<>();

        for (Schedule i : schedules) {
            if (i.getDay().equals(day) && i.getGroup().equals(group)) {
                array.add(i);
            }
        }
        return array;
    }

    public List<Schedule> getSchedule() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }
}
