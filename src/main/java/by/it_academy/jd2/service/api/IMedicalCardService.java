package by.it_academy.jd2.service.api;

import by.it_academy.jd2.domain.Appointment;
import by.it_academy.jd2.domain.User;

import java.util.Set;

public interface IMedicalCardService {

    User getUserByMedicalCardId(Long id);
    Set<Appointment> getAllAppointments(Long id);
}
