package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Schedule {
    private int id;
    private NumberLesson numberLesson;
    private Group group;
    private Day day;
    private Room room;
    private Teacher teacher;
    private Subject subject;


    public Schedule() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public NumberLesson getNumberLesson() {
        return numberLesson;
    }

    public void setNumberLesson(NumberLesson numberLesson) {
            this.numberLesson = numberLesson;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
            this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Schedule schedule = (Schedule) o;
        return id == schedule.id &&
                numberLesson == schedule.numberLesson &&
                Objects.equals(group, schedule.group) &&
                day == schedule.day &&
                Objects.equals(room, schedule.room) &&
                Objects.equals(teacher, schedule.teacher) &&
                Objects.equals(subject, schedule.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberLesson, group, day, room, teacher, subject);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", numberLesson=" + numberLesson +
                ", group=" + group +
                ", day=" + day +
                ", room=" + room +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
