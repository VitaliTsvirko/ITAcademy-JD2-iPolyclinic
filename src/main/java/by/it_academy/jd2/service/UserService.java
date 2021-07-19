package by.it_academy.jd2.service;

import by.it_academy.jd2.core.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.IAddressRepository;
import by.it_academy.jd2.repository.ICountriesRepository;
import by.it_academy.jd2.repository.IUsersRepository;
import by.it_academy.jd2.security.SecurityUtils;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final IUsersRepository usersRepository;
    private final IAddressRepository addressRepository;

    private final ICountriesRepository countriesRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(IUsersRepository usersRepository, IAddressRepository addressRepository, ICountriesRepository countriesRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.addressRepository = addressRepository;
        this.countriesRepository = countriesRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String phoneNo, String password){
        usersRepository.findByPhoneNo(phoneNo).orElseThrow(
                () -> new UsernameAlreadyUsedException()
        );

        User user = new User();
        user.setPhoneNo(phoneNo);
        user.setPassword(passwordEncoder.encode(password));
        user.setState(ApplicationUserState.SIGNUP);
        user.setUserRole(UserRoles.USER);

        usersRepository.save(user);

        return user;
    }

    @Override
    public User getUserById(Long id) throws UsernameNotFoundException{
        return usersRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User with id" + id +"was not found"));
    }


    @Override
    public User getAuthorizedUser() {
        Optional<String> authorizedUserLogin = SecurityUtils.getCurrentUserLogin();

        authorizedUserLogin.orElseThrow(
                () -> new UsernameNotFoundException("Authorized user not found")
        );

        return usersRepository.findByPhoneNo(authorizedUserLogin.get()).orElseThrow(
                () -> new UsernameNotFoundException("Authorized user not found")
        );
    }


    @Override
    public User createAddress(User user, AddressDTO addressDTO){
        usersRepository.findById(user.getId())
                        .ifPresent( existingUser -> {
                            Countries countryDTO = countriesRepository
                                    .findById(addressDTO.getCountryCode())
                                    .orElseThrow(
                                            () -> new IllegalArgumentException("Bad country ID")
                                    );

                            Address newAddress  = new Address();
                            newAddress.setCountry(countryDTO);
                            newAddress.setCity(addressDTO.getCity());
                            newAddress.setStreet(addressDTO.getStreet());
                            newAddress.setHomeNo(addressDTO.getHomeNo());
                            newAddress.setCorpsNo(addressDTO.getCorpsNo());
                            newAddress.setFlatNo(addressDTO.getFlatNo());

                            addressRepository.save(newAddress);
                            existingUser.setAddress(newAddress);
                        }
        );

        return this.getUserById(user.getId());
    }


    @Override
    public void deleteAddress(User user) {
        usersRepository.findById(user.getId()).ifPresent(exitUser -> {
            Address address = exitUser.getAddress();
            exitUser.setAddress(null);
            addressRepository.delete(address);
        });
    }

    @Override
    public User updateAddress(User user, AddressDTO addressDTO) {
        usersRepository.findById(user.getId()).ifPresent(existUser -> {
            Countries countryDTO = countriesRepository
                    .findById(addressDTO.getCountryCode())
                    .orElseThrow(
                    () -> new IllegalArgumentException("Bad country ID")
                    );

            Address userAddress = existUser.getAddress();

            userAddress.setCountry(countryDTO);
            userAddress.setCountry(countryDTO);
            userAddress.setCity(addressDTO.getCity());
            userAddress.setStreet(addressDTO.getStreet());
            userAddress.setHomeNo(addressDTO.getHomeNo());
            userAddress.setCorpsNo(addressDTO.getCorpsNo());
            userAddress.setFlatNo(addressDTO.getFlatNo());
        });

        return usersRepository.getById(user.getId());
    }
}
