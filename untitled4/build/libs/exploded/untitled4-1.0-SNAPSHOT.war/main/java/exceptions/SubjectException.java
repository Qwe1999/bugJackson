package exceptions;

public class SubjectException extends ModelException{
    private String message;

    public SubjectException(String message){
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
