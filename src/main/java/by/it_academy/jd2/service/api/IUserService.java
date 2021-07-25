package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.AddressDTO;
import by.it_academy.jd2.service.PassportDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
public interface IUserService {
    User createUser(String phoneNo, String password);

    User getUserById(Long id) throws UsernameNotFoundException;

    User getAuthorizedUser();

    void deleteAddress(User user);

    Address createAddress(User user, AddressDTO addressDTO);

    Address updateAddress(User user, AddressDTO addressDTO);


    Passport createPassport(User user, PassportDTO passportDTO);
    Passport updatePassport(User user, PassportDTO passportDTO);
    void deletePassport(User user);

    List<User> getAllUsers();

    User confirmPassportDataByUserId (Long userId);
}
