package by.it_academy.jd2.core;

/**
 * Created by Vitali Tsvirko
 */
public class UsernameAlreadyUsedException extends RuntimeException {

    public UsernameAlreadyUsedException() {
        super("Login name already used!");
    }
}
