package by.it_academy.jd2.repository;


import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.Address;
import by.it_academy.jd2.domain.Countries;
import by.it_academy.jd2.domain.Passport;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.GenderType;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class PassportsRepositoryTest {

    @Autowired
    private IPassportsRepository passportsRepository;

    @Autowired
    private IUsersRepository userRepository;

    @Autowired
    private ICountriesRepository countriesRepository;

    @Autowired
    private IAddressRepository addressRepository;

    private static final Logger LOG = Logger.getLogger(PassportsRepositoryTest.class.getName());

    @Before
    public void setUP(){
        LOG.info("Begin user DAO layer testing");
    }

    @Test
    void userSingUp(){
        User user = new User();

        user.setPhoneNo("+37529111111");
        user.setPassword("MyPassword");
        user.setState(ApplicationUserState.SIGNUP);

        userRepository.saveAndFlush(user);
    }


    @Test
    void addNewUser(){
        Countries country = countriesRepository.getById("BLR");

        Address address = new Address();
        address.setCity("Minsk");
        address.setCountry(country);
        address.setStreet("Goretskogo");

        Passport passport = new Passport();

        /*passport.setPassportNo("MP123456");
        passport.setCountryOfIssue(country);
        passport.setPersonalNo("NO123456789");
        passport.setDateOfBirth(LocalDate.of(1986,10,12));
        passport.setIssueDate(LocalDate.of(2000, 1, 1));
        passport.setExpirationDate(LocalDate.of(2030,1,1));
        passport.setSex(Sex.MALE);*/

        User user = new User();

        user.setPhoneNo("+37529111111");
        user.setPassword("MyPassword");
        user.setState(ApplicationUserState.SIGNUP);

        user.setPassport(passport);
        user.setAddress(address);

        addressRepository.save(address);
        passportsRepository.save(passport);
        userRepository.saveAndFlush(user);
    }


    @Test
    void addNewUserWithSameAddress(){
        Countries country = countriesRepository.getById("BLR");

        List<Address> all = addressRepository.findAll();



        Address address = new Address();
        address.setCity("Minsk");
        address.setCountry(country);
        address.setStreet("Goretskogo");

        boolean contains = all.contains(address);

        Address address1 = all.get(all.indexOf(address));



        Passport passport = new Passport();

        passport.setPassportNo("MP123456");
        passport.setCountryOfIssue(country);
        passport.setPersonalNo("NO123456789");
        passport.setDateOfBirth(LocalDate.of(1986,10,12));
        passport.setIssueDate(LocalDate.of(2000, 1, 1));
        passport.setExpirationDate(LocalDate.of(2030,1,1));
        passport.setGenderType(GenderType.MALE);

        User user = new User();

        user.setPhoneNo("+37529111111");
        user.setPassword("MyPassword");
        user.setState(ApplicationUserState.SIGNUP);

        user.setPassport(passport);
        user.setAddress(address);

        addressRepository.save(address);
        passportsRepository.save(passport);
        userRepository.saveAndFlush(user);
    }


    @Test
    void addPassportToExistUser(){
        User user = userRepository.findById(1L).get();

        Passport passport = new Passport();

        passport.setName("Vitali");
        passport.setSurname("Tsvirko");
        passport.setPassportNo("MP123456");
        passport.setPersonalNo("12345678PB7");
        passport.setDateOfBirth(LocalDate.of(1986,10,12));
        passport.setIssueDate(LocalDate.of(2021,1,1));
        passport.setExpirationDate(LocalDate.of(2031,1,1));
        passport.setCountryOfIssue(countriesRepository.findById("BLR").get());
        passport.setPlaceOfBirth("Новолукомль");


        user.setPassport(passport);

        passportsRepository.save(passport);
        userRepository.save(user);
    }

}