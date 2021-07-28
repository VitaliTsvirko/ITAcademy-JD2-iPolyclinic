package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.*;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.Sex;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IUsersRepositoryTest {

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ICountriesRepository countriesRepository;

    @Autowired
    private IMedicalCardRepository medicalCardRepository;

    @Autowired
    private IPassportsRepository passportsRepository;



    @Test
    void getExistUserByLogin(){
        String existPhone = "+375295148825";

        Optional<User> repositoryById = usersRepository.findById(1L);

        repositoryById.ifPresent(user -> {
            user.seteMail("123@mail.com");
            System.out.println(user);
        });

    }

    @Test
    void deleteUserAddress(){

        User byId = usersRepository.getById(1L);

        Address address = byId.getAddress();

        address.setCorpsNo(123);

        byId.setAddress(null);
        addressRepository.delete(address);

    }


    @Test
    void addUserAddress(){
        Address address = new Address();

        Optional<Countries> blr = countriesRepository.findById("BLR");

        address.setCountry(blr.get());
        address.setCity("MInsk");

        addressRepository.save(address);


        User user = usersRepository.findById(1L).get();

        user.setAddress(address);


    }


    @Test
    void createUser(){
        User user = new User();

        user.setPhoneNo("+37529111113");
        user.setPassword("doctor");
        user.setState(ApplicationUserState.SIGNUP);
        user.setUserRole(UserRoles.DOCTOR);

        /*MedicalCard medicalCard = new MedicalCard();
        medicalCard.setUser(user);

        user.setMedicalCard(medicalCard);
*/
        usersRepository.save(user);
//        medicalCardRepository.save(medicalCard);

    }

    @Test
    void initialCreateUsers(){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User admin = new User();

        admin.setPhoneNo("+37529111111");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setState(ApplicationUserState.SIGNUP);
        admin.setUserRole(UserRoles.ADMIN);

        usersRepository.save(admin);

        User doctor = new User();

        doctor.setPhoneNo("+37529111112");
        doctor.setPassword(passwordEncoder.encode("doctor"));
        doctor.setState(ApplicationUserState.SIGNUP);
        doctor.setUserRole(UserRoles.DOCTOR);

        usersRepository.save(doctor);

        User user = new User();

        user.setPhoneNo("+37529111113");
        user.setPassword(passwordEncoder.encode("user"));
        user.setState(ApplicationUserState.SIGNUP);
        user.setUserRole(UserRoles.USER);

        MedicalCard medicalCard = new MedicalCard();
        medicalCard.setUser(user);

        user.setMedicalCard(medicalCard);

        usersRepository.save(user);
        medicalCardRepository.save(medicalCard);
    }

    @Test
    void addUserPassport(){
        usersRepository.findByPhoneNo("+37529111113").ifPresent(user -> {
            Passport passport = new Passport();
            passport.setName("Иван");
            passport.setSurname("Иванов");
            passport.setPatronymic("Иванович");
            passport.setDateOfBirth(LocalDate.of(1986, 1, 1));
            passport.setIssueDate(LocalDate.of(2021, 1, 1));
            passport.setExpirationDate(LocalDate.of(2021, 1, 1));
            passport.setSex(Sex.MALE);
            passport.setPassportNo("123456");
            passport.setPersonalNo("010203040506E017PB6");

            passportsRepository.save(passport);

            user.setPassport(passport);

        });

            usersRepository.findByPhoneNo("+37529111112").ifPresent(user -> {
                Passport passport = new Passport();
                passport.setName("Сергей");
                passport.setSurname("Сергеевич");
                passport.setPatronymic("Сергеев");
                passport.setDateOfBirth(LocalDate.of(1989, 1, 1));
                passport.setIssueDate(LocalDate.of(2011, 1, 1));
                passport.setExpirationDate(LocalDate.of(2051, 1, 1));
                passport.setSex(Sex.MALE);
                passport.setPassportNo("786343");
                passport.setPersonalNo("010203040506E017PB7");

                passportsRepository.save(passport);

                user.setPassport(passport);

        });

    }




}