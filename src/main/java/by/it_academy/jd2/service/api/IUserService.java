package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.User;

/**
 * Created by Vitali Tsvirko
 */
public interface IUserService {
    User userSignUp(String phoneNo, String password);

    User authentication(String login, String password);

    User getUserById(Long id);
}
