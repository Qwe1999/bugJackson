package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import exceptions.GroupException;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Group {

    private int id;
    private  String number;

    public Group() {
    }

    public Group(String number) {
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) throws GroupException {
        if(number == null & number.isEmpty()) {
            throw new GroupException("number is empty or null");
        }
        else {
            this.number = number;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return id == group.id &&
                Objects.equals(number, group.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }

}
