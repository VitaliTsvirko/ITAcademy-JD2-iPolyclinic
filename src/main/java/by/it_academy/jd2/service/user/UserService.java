package by.it_academy.jd2.service.user;

import by.it_academy.jd2.core.exceptions.UsernameAlreadyUsedException;
import by.it_academy.jd2.domain.*;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.Sex;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.*;
import by.it_academy.jd2.security.SecurityUtils;
import by.it_academy.jd2.service.dto.AddressDTO;
import by.it_academy.jd2.service.dto.PassportDTO;
import by.it_academy.jd2.service.api.IUserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Created by Vitali Tsvirko
 */
@Service
@Transactional
public class UserService implements IUserService {

    private final IUsersRepository usersRepository;
    private final IAddressRepository addressRepository;
    private final IPassportsRepository passportsRepository;
    private final ICountriesRepository countriesRepository;
    private final IMedicalCardRepository medicalCardRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(IUsersRepository usersRepository, IAddressRepository addressRepository, IPassportsRepository passportsRepository, ICountriesRepository countriesRepository, IMedicalCardRepository medicalCardRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.addressRepository = addressRepository;
        this.passportsRepository = passportsRepository;
        this.countriesRepository = countriesRepository;
        this.medicalCardRepository = medicalCardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User createUser(String phoneNo, String password){
        usersRepository.findByPhoneNo(phoneNo).ifPresent(
                user -> { throw new UsernameAlreadyUsedException("User phone number " + user.getPhoneNo() + " already registered");
                }
        );

        User user = new User();
        user.setPhoneNo(phoneNo);
        user.setPassword(passwordEncoder.encode(password));
        user.setState(ApplicationUserState.SIGNUP);
        user.setUserRole(UserRoles.USER);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setUser(user);

        user.setMedicalCard(medicalCard);

        usersRepository.save(user);
        medicalCardRepository.save(medicalCard);

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
    public Address createAddress(User user, AddressDTO addressDTO){
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

        return this.getUserById(user.getId()).getAddress();
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
    public Address updateAddress(User user, AddressDTO addressDTO) {
        usersRepository.findById(user.getId()).ifPresent(existUser -> {
            Countries countryDTO = countriesRepository
                    .findById(addressDTO.getCountryCode())
                    .orElseThrow(
                    () -> new IllegalArgumentException("Bad country ID")
                    );

            Address userAddress = existUser.getAddress();

            userAddress.setCountry(countryDTO);
            userAddress.setCity(addressDTO.getCity());
            userAddress.setStreet(addressDTO.getStreet());
            userAddress.setHomeNo(addressDTO.getHomeNo());
            userAddress.setCorpsNo(addressDTO.getCorpsNo());
            userAddress.setFlatNo(addressDTO.getFlatNo());
        });

        return usersRepository.getById(user.getId()).getAddress();
    }





    @Override
    public Passport createPassport(User user, PassportDTO passportDTO){
        usersRepository.findById(user.getId())
                .ifPresent( existingUser -> {
                            Countries countryDTO = countriesRepository
                            .findById(passportDTO.getCountryOfIssueCode())
                            .orElseThrow(
                                    () -> new IllegalArgumentException("Bad country ID")
                            );

                            //проверка на уникальность персонального номера (пробросить ошибки базы)

                            Passport newPassport  = new Passport();
                            newPassport.setName(passportDTO.getName());
                            newPassport.setSurname(passportDTO.getSurname());
                            newPassport.setPatronymic(passportDTO.getPatronymic());
                            newPassport.setDateOfBirth(passportDTO.getDateOfBirth());
                            newPassport.setPlaceOfBirth(passportDTO.getPlaceOfBirth());
                            newPassport.setNationality(passportDTO.getNationality());
                            newPassport.setPersonalNo(passportDTO.getPersonalNo());
                            newPassport.setPassportNo(passportDTO.getPassportNo());
                            newPassport.setCountryOfIssue(countryDTO);
                            newPassport.setIssueDate(passportDTO.getIssueDate());
                            newPassport.setExpirationDate(passportDTO.getExpirationDate());

                            newPassport.setSex(Sex.MALE);

                            passportsRepository.save(newPassport);
                            existingUser.setPassport(newPassport);
                        }
                );

        return this.getUserById(user.getId()).getPassport();
    }


    @Override
    public void deletePassport(User user) {
        usersRepository.findById(user.getId()).ifPresent(exitUser -> {
            Passport passport = exitUser.getPassport();
            exitUser.setPassport(null);
            passportsRepository.delete(passport);
        });
    }

    @Override
    public Passport updatePassport(User user, PassportDTO passportDTO) {
        usersRepository.findById(user.getId()).ifPresent(existUser -> {
            Countries countryDTO = countriesRepository
                    .findById(passportDTO.getCountryOfIssueCode())
                    .orElseThrow(
                            () -> new IllegalArgumentException("Bad country ID")
                    );

            Passport userPassport = existUser.getPassport();

            userPassport.setName(passportDTO.getName());
            userPassport.setSurname(passportDTO.getSurname());
            userPassport.setPatronymic(passportDTO.getPatronymic());
            userPassport.setDateOfBirth(passportDTO.getDateOfBirth());
            userPassport.setPlaceOfBirth(passportDTO.getPlaceOfBirth());
            userPassport.setNationality(passportDTO.getNationality());
            userPassport.setPersonalNo(passportDTO.getPersonalNo());
            userPassport.setPassportNo(passportDTO.getPassportNo());
            userPassport.setCountryOfIssue(countryDTO);
            userPassport.setIssueDate(passportDTO.getIssueDate());
            userPassport.setExpirationDate(passportDTO.getExpirationDate());
        });

        return usersRepository.getById(user.getId()).getPassport();
    }


    @Override
    public User confirmPassportDataByUserId (Long userId){
        User user = usersRepository.findById(userId).orElseThrow(
                () -> new UsernameNotFoundException("User with id " + userId + "not found")
        );

        if (user.getPassport() == null) {
            throw new IllegalArgumentException("Passport data is not entered");
        }

        user.setState(ApplicationUserState.PASSPORT_DATA_VERIFIED);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setUser(user);
        medicalCardRepository.save(medicalCard);

        user.setMedicalCard(medicalCard);

        user.setState(ApplicationUserState.ACTIVATED);

        return user;
    }



    public List<User> getAllUsers(){
        return usersRepository.findAll();
    }

    @Override
    public List<User> getAllPatients() {
        return usersRepository.findByUserRoleEquals(UserRoles.USER);
    }


}
