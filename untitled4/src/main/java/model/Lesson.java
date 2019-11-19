package model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Lesson {
    private int id;
    private NumberLesson numberLesson;
    private Group group;
    private Day dayLesson;
    private Room room;
    private Teacher teacher;
    private Subject subject;


    public Lesson() {
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
            this.numberLesson =numberLesson;
    }

    public Day getDayLesson() {
        return dayLesson;
    }

    public void setDayLesson(Day dayLesson) {
            this.dayLesson = dayLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return id == lesson.id &&
                numberLesson == lesson.numberLesson &&
                Objects.equals(group, lesson.group) &&
                dayLesson == lesson.dayLesson &&
                Objects.equals(room, lesson.room) &&
                Objects.equals(teacher, lesson.teacher) &&
                Objects.equals(subject, lesson.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberLesson, group, dayLesson, room, teacher, subject);
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "id=" + id +
                ", numberLesson=" + numberLesson +
                ", group=" + group +
                ", day=" + dayLesson +
                ", room=" + room +
                ", teacher=" + teacher +
                ", subject=" + subject +
                '}';
    }
}
