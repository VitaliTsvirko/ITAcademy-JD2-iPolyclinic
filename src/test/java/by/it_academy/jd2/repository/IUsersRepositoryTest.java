package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.*;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.GenderType;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.postgresql.util.PGInterval;
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
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

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

    @Autowired
    private IUserHealthMetricsRepository userHealthMetricsRepository;

    @Autowired
    private IAppointmentsRepository appointmentsRepository;

    @Autowired
    private IDiseasesRepository diseasesRepository;

    @Test
    void initialCreateUsers(){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.usersCreator(20, "+37529100000", "user",
                "Иван", "Иванов", "Иванович",
                UserRoles.USER, "100000", "317100000PB");

        this.usersCreator(10, "+37529200000", "123",
                "Петр", "Петров", "Петрович",
                UserRoles.DOCTOR, "200000", "317100000MD");

        this.usersCreator(5, "+37533100000", "123",
                "Сергей", "Сергеев", "Сегреевич",
                UserRoles.MANAGER, "200000", "317100000MN");
    }

    @Test
    void initialCreateBasicUsers(){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

/*        User admin = new User();

        admin.setPhoneNo("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setState(ApplicationUserState.SIGNUP);
        admin.setUserRole(UserRoles.ADMIN);

        usersRepository.save(admin);*/


        this.usersCreator(1, "user", "123",
                "Александр", "Пушкин", "Сергеевич",
                UserRoles.USER, "1000001", "317100002PB");

        this.usersCreator(1, "doctor", "123",
                "Евгений", "Костоправов", "Александрович",
                UserRoles.DOCTOR, "2000001", "317100002MD");

        this.usersCreator(1, "manager", "123",
                "Владимир", "Ленин", "Ильич",
                UserRoles.MANAGER, "2000001", "317100002MN");

        User user = usersRepository.findByPhoneNo("user").get();
        User doctor = usersRepository.findByPhoneNo("doctor").get();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            UserHealthMetrics metrics = new UserHealthMetrics();

            metrics.setCreatedBy(user);
            metrics.setMedicalCard(user.getMedicalCard());
            metrics.setTimestamp(LocalDateTime.now().minusDays(i));
            metrics.setWeight(random.nextInt(100));
            metrics.setHeight(random.nextInt(200));
            metrics.setDiastolicBloodPressure(random.nextInt(120));
            metrics.setSystolicBloodPressure(random.nextInt(200));
            metrics.setHeartRate(random.nextInt(150));

            userHealthMetricsRepository.save(metrics);

            Appointment appointment = new Appointment();
            appointment.setDateTime(LocalDateTime.now().minusDays(i));
            appointment.setDoctor(doctor);
            appointment.setDiagnosis(diseasesRepository.getById(i < 9 ? "A04." + (i + 1) : "A04.1"));
            appointment.setHealthStatus(HealthStatus.SICK);
            appointment.setComplaints("Complaints " + i);
            appointment.setTherapy("Принимать что-то и " + i);
            appointment.setMedicalCard(user.getMedicalCard());
            appointmentsRepository.save(appointment);
        }
    }



    private void usersCreator(int count, String phoneNoPrefix, String password, String namePrefix, String surnamePrefix, String patronymicPrefix, UserRoles userRole,
                              String passportNoPrefix, String personalNoPrefix){

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for (int i = 0; i < count; i++) {
            User user = new User();

            user.setPhoneNo(i == 0 ? phoneNoPrefix : phoneNoPrefix + i);
            user.setPassword(passwordEncoder.encode(password));
            user.setState(ApplicationUserState.SIGNUP);
            user.setUserRole(userRole);

            usersRepository.save(user);

            MedicalCard medicalCard = new MedicalCard();
            medicalCard.setUser(user);

            user.setMedicalCard(medicalCard);

            usersRepository.saveAndFlush(user);
            medicalCardRepository.saveAndFlush(medicalCard);
        }

        for (int i = 0; i < count; i++) {
            final int index = i;

            usersRepository.findByPhoneNo(i == 0 ? phoneNoPrefix : phoneNoPrefix + i).ifPresent(user -> {
                Passport passport = new Passport();
                passport.setName(namePrefix + index);
                passport.setSurname(surnamePrefix + index);
                passport.setPatronymic(patronymicPrefix + index);
                passport.setDateOfBirth(LocalDate.of(1986 + index, 1, index + 1));
                passport.setIssueDate(LocalDate.of(2021, 1, index + 1));
                passport.setExpirationDate(LocalDate.of(2021, 1, index + 1));
                passport.setGenderType(GenderType.MALE);
                passport.setPassportNo(passportNoPrefix + index);
                passport.setPersonalNo(personalNoPrefix + index);
                passport.setCountryOfIssue(countriesRepository.findById("BLR").get());

                passportsRepository.save(passport);

                user.setPassport(passport);

                user.setState(ApplicationUserState.PASSPORT_DATA_IS_INPUT);
            });
        }
    }

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
    void addUserPassport(){
        usersRepository.findByPhoneNo("+37529111113").ifPresent(user -> {
            Passport passport = new Passport();
            passport.setName("Иван");
            passport.setSurname("Иванов");
            passport.setPatronymic("Иванович");
            passport.setDateOfBirth(LocalDate.of(1986, 1, 1));
            passport.setIssueDate(LocalDate.of(2021, 1, 1));
            passport.setExpirationDate(LocalDate.of(2021, 1, 1));
            passport.setGenderType(GenderType.MALE);
            passport.setPassportNo("123456");
            passport.setPersonalNo("010203040506E017PB6");

            passportsRepository.save(passport);

            user.setPassport(passport);

        });
    }


    @Test
    void userMetrics(){

        long count = usersRepository.count();

        Long aLong = usersRepository.countByUserRoleEquals(UserRoles.USER);
        Long aLong1 = usersRepository.countByUserRoleEquals(UserRoles.DOCTOR);
        Long aLong2 = usersRepository.countByUserRoleEquals(UserRoles.ADMIN);

        System.out.println(count);
        System.out.println(aLong);
        System.out.println(aLong1);
        System.out.println(aLong2);


        Double aDouble = usersRepository.avgAgeByUserRole(UserRoles.DOCTOR.toString());

        System.out.println(aDouble);


    }

}