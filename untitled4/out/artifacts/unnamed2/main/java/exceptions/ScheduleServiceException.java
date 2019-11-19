package exceptions;

public class ScheduleServiceException extends ModelException {
    private String message;

    public ScheduleServiceException(String message){
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
