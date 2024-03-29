package by.it_academy.jd2.core.exceptions;

public class AppointmentNotFoundException extends RuntimeException{
    public AppointmentNotFoundException() {
        super();
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }

    public AppointmentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppointmentNotFoundException(Throwable cause) {
        super(cause);
    }

    protected AppointmentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
