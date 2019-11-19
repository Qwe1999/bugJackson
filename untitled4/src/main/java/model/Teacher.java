package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import exceptions.TeacherException;


import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Teacher {
    private int id;
    private String firstName;
    private String lastName;



    public Teacher() {
    }

    public Teacher(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) throws TeacherException {
        if(firstName != null & !firstName.isEmpty()) {
            this.firstName = firstName;
        }
        else {
            throw new TeacherException("First Name is null or empty");
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) throws TeacherException{
        if(lastName != null & !lastName.isEmpty()) {
            this.lastName = lastName;
        }
        else {
            throw new TeacherException("Last Name is null or empty");
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return id == teacher.id &&
                Objects.equals(firstName, teacher.firstName) &&
                Objects.equals(lastName, teacher.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}