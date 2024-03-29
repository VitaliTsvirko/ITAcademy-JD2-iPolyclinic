package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.dto.AddressDTO;
import by.it_academy.jd2.service.dto.PassportDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
public interface IUserService {
    User createUser(String phoneNo, String password);
    User getUserById(Optional<Long> id) throws UsernameNotFoundException;
    User getUserById(Long id) throws UsernameNotFoundException;
    User getAuthorizedUser();

    List<User> getAllUsers();
    List<User> getAllPatients();

    Address createAddress(User user, AddressDTO addressDTO);
    Address updateAddress(User user, AddressDTO addressDTO);
    void deleteAddress(User user);

    Passport createPassport(User user, PassportDTO passportDTO);
    Passport updatePassport(User user, PassportDTO passportDTO);
    void deletePassport(User user);

    User confirmPassportDataByUserId (Long userId);
}
