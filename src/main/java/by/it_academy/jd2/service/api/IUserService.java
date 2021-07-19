package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.service.AddressDTO;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
public interface IUserService {
    User createUser(String phoneNo, String password);

    User getUserById(Long id) throws UsernameNotFoundException;

    User getAuthorizedUser();

    void deleteAddress(User user);

    User createAddress(User user, AddressDTO addressDTO);

    User updateAddress(User user, AddressDTO addressDTO);
}
