package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;

/**
 * Created by Vitali Tsvirko
 */
public interface IPassportService {
    Passport addPassport(Passport passport, User user);
}
