package by.it_academy.jd2.core;

/**
 * Created by Vitali Tsvirko
 */
public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException(String message) {
        super(message);
    }

    public UsernameAlreadyUsedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyUsedException(Throwable cause) {
        super(cause);
    }

    protected UsernameAlreadyUsedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
