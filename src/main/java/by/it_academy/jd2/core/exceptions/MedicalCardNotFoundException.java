package by.it_academy.jd2.core.exceptions;

public class MedicalCardNotFoundException extends RuntimeException{
    public MedicalCardNotFoundException() {
        super();
    }

    public MedicalCardNotFoundException(String message) {
        super(message);
    }

    public MedicalCardNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicalCardNotFoundException(Throwable cause) {
        super(cause);
    }

    protected MedicalCardNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
