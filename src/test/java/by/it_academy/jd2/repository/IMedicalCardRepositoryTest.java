package by.it_academy.jd2.repository;

import by.it_academy.jd2.config.PersistentConfig;
import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.MedicalCard;
import by.it_academy.jd2.domain.User;
import by.it_academy.jd2.domain.enumeration.HealthStatus;
import by.it_academy.jd2.domain.enumeration.UserRoles;
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

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(value = {SpringExtension.class})
@ContextConfiguration(
        classes = { PersistentConfig.class },
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Rollback(false)
class IMedicalCardRepositoryTest {

    @Autowired
    private IMedicalCardRepository medicalCardRepository;

    @Autowired
    private IUsersRepository usersRepository;

    @Autowired
    private IAppointmentsRepository appointmentsRepository;

    @Test
    void addAppointments(){
        User doctor = usersRepository.findByUserRoleEquals(UserRoles.DOCTOR).stream().findFirst().get();


        Optional<MedicalCard> medicalCard = medicalCardRepository.findByUserId(3L);
        medicalCard.ifPresent(md -> {
                    Appointment appointment = new Appointment();
                    appointment.setDateTime(LocalDateTime.now());
                    appointment.setDoctor(doctor);
                    appointment.setComplaints("Боль в животе_2");
                    appointment.setHealthStatus(HealthStatus.SICK);
                    appointment.setTemperature(36.6F);
                    appointment.setDiastolicBloodPressure(80);
                    appointment.setSystolicBloodPressure(120);
                    appointment.setMedicalCard(md);

                    appointmentsRepository.save(appointment);
                }
                );
    }

    @Test
    void getAllAppointmentsByMedicalCardId(){
        Optional<MedicalCard> byUserId = medicalCardRepository.findByUserId(3L);

        MedicalCard medicalCard = byUserId.get();

        Set<Appointment> appointments = medicalCard.getAppointments();

        int size = appointments.size();


    }





}