package exceptions;

import model.Teacher;

public class TeacherException extends ModelException{
    private String message;

    public TeacherException(String message){
        super();
        this.message = message;
    }


    @Override
    public String toString() {
        return "exceptions.ScheduleException{" +
                "message='" + message + '\'' +
                '}';
    }
}
