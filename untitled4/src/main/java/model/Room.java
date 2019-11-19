package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import exceptions.RoomException;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
    private int id;
    private String number;

    public Room() {
    }

    public Room(String number) {
        this.number = number;
    }

    public Room(int id, String number) {
        this.id = id;
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

    public void setNumber(String number) throws RoomException{
        if(number!= null & !number.isEmpty()) {
            this.number = number;
        }
        else {
            throw new RoomException("Number is null or empty");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id &&
                number == room.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", number=" + number +
                '}';
    }
}
