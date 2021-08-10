package by.it_academy.jd2;


import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.config.RootConfig;
import by.it_academy.jd2.config.WebConfig;
import by.it_academy.jd2.core.healthmetrics.HealthMetricsCalculator;
import by.it_academy.jd2.core.healthmetrics.HealthMetricsHandler;
import by.it_academy.jd2.core.healthmetrics.HealthMetricsUtils;
import by.it_academy.jd2.core.healthmetrics.dto.HealthMetricEntityDTO;
import by.it_academy.jd2.core.healthmetrics.enumeration.HealthMetricsTypes;
import by.it_academy.jd2.domain.*;
import by.it_academy.jd2.domain.enumeration.ApplicationUserState;
import by.it_academy.jd2.domain.enumeration.GenderType;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import by.it_academy.jd2.domain.enumeration.UserRoles;
import by.it_academy.jd2.repository.*;
import by.it_academy.jd2.service.HealthMetricService;
import by.it_academy.jd2.service.api.IHealthMetricService;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
public class GenerateData {

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
    private IAppointmentsRepository appointmentsRepository;

    @Autowired
    private IDiseasesRepository diseasesRepository;

    @Autowired
    private  IHealthMetricsRepository healthMetricsRepository;

    private IHealthMetricService healthMetricService;

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test()
    void initialCreateBasicUsers(){

        User admin = new User();

        admin.setPhoneNo("admin");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setState(ApplicationUserState.SIGNUP);
        admin.setUserRole(UserRoles.ADMIN);

        usersRepository.save(admin);

        //Name login
        this.usersCreator(1, "user", "123",
                "Александр", "Пушкин", "Сергеевич",
                UserRoles.USER, "1000001", "317100002PB");

        this.usersCreator(1, "doctor", "123",
                "Евгений", "Костоправов", "Александрович",
                UserRoles.DOCTOR, "2000001", "317100002MD");

        this.usersCreator(1, "manager", "123",
                "Владимир", "Ленин", "Ильич",
                UserRoles.MANAGER, "2000001", "317100002MN");

        //Phone login
        this.usersCreator(20, "+37529100000", "user",
                                          "Иван", "Иванов", "Иванович",
                                  UserRoles.USER, "100000", "317100000PB");

        this.usersCreator(10, "+37529200000", "123",
                                  "Петр", "Петров", "Петрович",
                          UserRoles.DOCTOR, "200000", "317100000MD");

        this.usersCreator(5, "+37533100000", "123",
                                  "Сергей", "Сергеев", "Сегреевич",
                          UserRoles.MANAGER, "200000", "317100000MN");


        User user = usersRepository.findByPhoneNo("user").get();
        User doctor = usersRepository.findByPhoneNo("doctor").get();

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
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

    @Test()
    void generateUserMetrics(){

        this.healthMetricService = new HealthMetricService(usersRepository,
                                                            healthMetricsRepository,
                                                            new HealthMetricsHandler(new HealthMetricsCalculator()),
                                                            medicalCardRepository);

        User user = usersRepository.findByPhoneNo("user").get();
        int metricCount = 100;

        List<Double> heartRateData = new Random().ints(HealthMetricsUtils.HEART_RATE_MIN, HealthMetricsUtils.HEART_RATE_MAX)
                .limit(metricCount).boxed().collect(Collectors.toList())
                .stream().map(v -> v.doubleValue())
                .collect(Collectors.toList());

        saveMetric(heartRateData, HealthMetricsTypes.HEART_RATE, user);


        List<Double> weightData = new Random().ints(HealthMetricsUtils.WEIGHT_MIN.intValue(), HealthMetricsUtils.WEIGHT_MAX.intValue())
                .limit(metricCount).boxed().collect(Collectors.toList())
                .stream().map(v -> v.doubleValue())
                .collect(Collectors.toList());

        saveMetric(weightData, HealthMetricsTypes.WEIGHT, user);

        List<Double> heightData = new Random().ints(HealthMetricsUtils.HEIGHT_MIN.intValue(), HealthMetricsUtils.HEIGHT_MAX.intValue())
                .limit(metricCount / 10).boxed().collect(Collectors.toList())
                .stream().map(v -> v.doubleValue())
                .collect(Collectors.toList());

        saveMetric(heightData, HealthMetricsTypes.HEIGHT, user);

        List<Double> sysAD = new Random().ints(HealthMetricsUtils.SYS_AD_MIN, HealthMetricsUtils.SYS_AD_MAX)
                .limit(metricCount).boxed().collect(Collectors.toList())
                .stream().map(v -> v.doubleValue())
                .collect(Collectors.toList());

        saveMetric(sysAD, HealthMetricsTypes.AD_SYS, user);

        List<Double> diaAD = new Random().ints(HealthMetricsUtils.SYS_AD_MIN, HealthMetricsUtils.SYS_AD_MAX)
                .limit(metricCount).boxed().collect(Collectors.toList())
                .stream().map(v -> v.doubleValue())
                .collect(Collectors.toList());

        saveMetric(diaAD, HealthMetricsTypes.AD_DIA, user);


        System.out.println("all data save");

    }


    private void saveMetric(List<Double> data, HealthMetricsTypes type, User user){
        int dayoffset = 0;

        List<HealthMetricEntityDTO> metricEntityDTOS = new ArrayList<>();

        for (Double val : data) {
            HealthMetricEntityDTO metric = new HealthMetricEntityDTO();

            metric.setTimestamp(LocalDateTime.now().minusDays(++dayoffset));
            metric.setType(type);
            metric.setValue(val);

            metricEntityDTOS.add(metric);
        }

        this.healthMetricService.addMetricsByUserId(metricEntityDTOS, user.getId());

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





}
