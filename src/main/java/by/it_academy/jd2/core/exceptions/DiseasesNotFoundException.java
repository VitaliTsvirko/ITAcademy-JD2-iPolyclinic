package by.it_academy.jd2.core.exceptions;

public class DiseasesNotFoundException extends RuntimeException{
    public DiseasesNotFoundException() {
        super();
    }

    public DiseasesNotFoundException(String message) {
        super(message);
    }

    public DiseasesNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DiseasesNotFoundException(Throwable cause) {
        super(cause);
    }

    protected DiseasesNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
