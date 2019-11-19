package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Objects;
import  exceptions.SubjectException;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subject {
    private int id;
    private String name;

    public Subject() {
    }


    public Subject(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws SubjectException{
        if(name != null & !name.isEmpty()) {
            this.name = name;
        }
        else {
            throw new SecurityException("name is null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return id == subject.id &&
                Objects.equals(name, subject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
